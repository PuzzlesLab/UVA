import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
	private static int N;
	private static ArrayList<Tuple> [] AdjList;
	
	private static int [] countDist(int start) {
		PriorityQueue<Tuple> q=new PriorityQueue<>();
		int [] minDist=new int [N];
		Arrays.fill(minDist,MAX_DIST);
		minDist[start]=0;

		q.offer(new Tuple(start,0));
		while (!q.isEmpty()) {
			Tuple c=q.poll();
			for (int i=0;i<AdjList[c.n].size();i++) {
				int n=AdjList[c.n].get(i).n;
				int nd=c.d+AdjList[c.n].get(i).d;
				if (nd<minDist[n]) {
					minDist[n]=nd;
					q.offer(new Tuple(n,nd));
				}
			}
		}
		return minDist;
	}
	
	private static boolean isPathClear(int start, int end, boolean [] ok) {
		LinkedList<Integer> q=new LinkedList<>();
		q.add(start);
		boolean [] visited=new boolean [N];
		visited[start]=true;
		
		while (!q.isEmpty()) {
			int curr=q.removeFirst();
			if (curr==end) return true;
			for (int i=0;i<AdjList[curr].size();i++) {
				int next=AdjList[curr].get(i).n;
				if (visited[next]) continue;
				if (ok[next]) {
					visited[next]=true;
					q.addLast(next);
				}
			}
		}
		return false;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int E=Integer.parseInt(st.nextToken());
			
			AdjList=new ArrayList [N];
			for (int n=0;n<N;n++) AdjList[n]=new ArrayList<>();

			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				int d=Integer.parseInt(st.nextToken());
				AdjList[n1].add(new Tuple(n2,d));
				AdjList[n2].add(new Tuple(n1,d));
			}

			int [] highway=new int [E];
			st=new StringTokenizer(br.readLine());
			for (int e=0;e<E;e++) highway[e]=Integer.parseInt(st.nextToken())-1;
			
			st=new StringTokenizer(br.readLine());
			int B=Integer.parseInt(st.nextToken())-1;
			int P=Integer.parseInt(st.nextToken())-1;

			int [] distFromB=countDist(B);
			int [] distFromP=countDist(P);

			final double MAX=1000000000.0;
			double minSpd=0;
			double maxSpd=MAX;
			for (int i=0;i<100;i++) {
				double spd=(minSpd+maxSpd)/2;

				boolean [] okN=new boolean [N];
				for (int n=0;n<N;n++) okN[n]=distFromB[n]/spd<distFromP[n]/160.0;

				boolean pathClear=false;
				for (int e=0;e<E && !pathClear;e++) {
					pathClear|=highway[e]==B;
					pathClear|=isPathClear(B,highway[e],okN);
				}

				if (pathClear) maxSpd=spd;
				else minSpd=spd;
			}

			if (Math.abs(MAX-maxSpd)<1e-8) System.out.println("IMPOSSIBLE");
			else System.out.printf("%.8f\n",maxSpd);
		}
	}

}