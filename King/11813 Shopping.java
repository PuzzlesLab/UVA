import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Tuple implements Comparable<Tuple> {
		int n, d;
		
		public Tuple(int n, int d) {
			this.n=n;
			this.d=d;
		}
		
		public int compareTo(Tuple t) {
			return this.d-t.d;
		}
	}

	private static final int MAX_DIST=1000000;
	private static ArrayList<Tuple> [] AdjList;
	private static int N;
	private static int S;
	private static int [][] Dp;
	private static ArrayList<Integer> ToVisits;
	private static int [][] SDist;

	private static int tsp(int currS, int visitedMask) {
		if (visitedMask==(1<<S)-1) return SDist[currS][0];

		if (Dp[currS][visitedMask]==-1) {
			int d=MAX_DIST;
			for (int s=0;s<S;s++) if ((visitedMask&(1<<s))==0) {
				d=Math.min(d,SDist[currS][s]+tsp(s,visitedMask|(1<<s)));
			}
			Dp[currS][visitedMask]=d;
		}
		return Dp[currS][visitedMask];
	}

	private static int getDist(int s1, int s2) {
		PriorityQueue<Tuple> q=new PriorityQueue<>();
		q.offer(new Tuple(ToVisits.get(s1),0));
		int [] minDist=new int [N];
		Arrays.fill(minDist,MAX_DIST);
		minDist[ToVisits.get(s1)]=0;

		while (!q.isEmpty()) {
			Tuple c=q.poll();
			if (ToVisits.get(s2)==c.n) return c.d;
			for (int i=0;i<AdjList[c.n].size();i++) {
				int n=AdjList[c.n].get(i).n;
				int nd=c.d+AdjList[c.n].get(i).d;
				if (nd<minDist[n]) {
					minDist[n]=nd;
					q.offer(new Tuple(n,nd));
				}
			}
		}
		return MAX_DIST;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			AdjList=new ArrayList [N];
			for (int n=0;n<N;n++) AdjList[n]=new ArrayList<>();

			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken());
				AdjList[n1].add(new Tuple(n2,d));
				AdjList[n2].add(new Tuple(n1,d));
			}

			S=Integer.parseInt(br.readLine());
			ToVisits=new ArrayList<>();
			ToVisits.add(0);
			for (int i=0;i<S;i++) ToVisits.add(Integer.parseInt(br.readLine()));
			S++;

			SDist=new int[S][S];
			for (int n=0;n<S;n++) {
				Arrays.fill(SDist[n],MAX_DIST);
				SDist[n][n]=0;
			}
			for (int i=0;i<S;i++) for (int i2=i+1;i2<S;i2++) SDist[i][i2]=SDist[i2][i]=getDist(i,i2);

			Dp=new int [S][1<<S];
			for (int i=0;i<S;i++) Arrays.fill(Dp[i],-1);
			System.out.println(tsp(0,1));
		}
	}

}