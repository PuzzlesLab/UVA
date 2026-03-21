import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static class Tuple implements Comparable<Tuple> {
		int n, cost;
		
		public Tuple(int n, int c) {
			this.n=n;
			this.cost=c;
		}
		
		public int compareTo(Tuple e) {
			return this.cost-e.cost;
		}
	}

	private static final int MAX_DIST=1000000;
	private static int N;
	private static int P;
	private static int [][] Dp;
	private static ArrayList<Tuple> ToVisits;
	private static int [][] Cost;

	private static int tsp(int currP, int visitedMask) {
		int currN=ToVisits.get(currP).n;
		if (visitedMask==(1<<P)-1) return -Cost[currN][0];

		if (Dp[currP][visitedMask]==-1) {
			int d=-Cost[currN][0];
			for (int p=0;p<P;p++) if ((visitedMask&(1<<p))==0) {
				int nextN=ToVisits.get(p).n;
				d=Math.max(d,ToVisits.get(p).cost-Cost[currN][nextN]+tsp(p,visitedMask|(1<<p)));
			}
			Dp[currP][visitedMask]=d;
		}
		return Dp[currP][visitedMask];
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine();
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken())+1;
			int M=Integer.parseInt(st.nextToken());
			
			Cost=new int[N][N];
			for (int n=0;n<N;n++) {
				Arrays.fill(Cost[n],MAX_DIST);
				Cost[n][n]=0;
			}

			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());
				Cost[n1][n2]=Cost[n2][n1]=Math.min(Cost[n1][n2],(int)(Double.parseDouble(st.nextToken())*100+0.5));
			}
			for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++) Cost[i][j]=Math.min(Cost[i][j],Cost[i][k]+Cost[k][j]);

			P=Integer.parseInt(br.readLine());
			ToVisits=new ArrayList<>();
			ToVisits.add(new Tuple(0,0));
			for (int p=0;p<P;p++) {
				st=new StringTokenizer(br.readLine());
				ToVisits.add(new Tuple(Integer.parseInt(st.nextToken()),(int)(Double.parseDouble(st.nextToken())*100+0.5)));
			}
			P++;

			Dp=new int [P][1<<P];
			for (int p=0;p<P;p++) Arrays.fill(Dp[p],-1);
			int ans=tsp(0,1);
			if (ans<=0.0) System.out.println("Don't leave the house");
			else System.out.printf("Daniel can save $%d.%02d\n",ans/100,ans%100);
		}
	}

}