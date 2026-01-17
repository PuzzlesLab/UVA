import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Pos {
		double x, y;
		
		public Pos(double x, double y) {
			this.x=x;
			this.y=y;
		}
		
		public double distSq(Pos p) {
			double dx=this.x-p.x;
			double dy=this.y-p.y;
			return dx*dx+dy*dy;
		}
	}

	private static Pos [] Gophers;
	private static Pos [] Holes;
	private static double [][] DistSq;
	private static double MaxDistSq;
	private static boolean [] Visited;
	private static int [] Pair;
	
	private static boolean match(int m) {
		for (int n=0;n<Holes.length;n++) {
			if (DistSq[m][n]<=MaxDistSq && !Visited[n]) {
				Visited[n]=true;
				if (Pair[n]==-1 || match(Pair[n])) {
					Pair[n]=m;
					return true;
				}
			}
		}
		return false;
	}

	private static int compute() {
		if (Visited==null || Visited.length!=Holes.length) {
			Visited=new boolean [Holes.length];
			Pair=new int[Holes.length];
		}
		
		Arrays.fill(Pair,-1);
		int count=0;
		for (int m=0;m<Gophers.length;m++) {
			Arrays.fill(Visited,false);
			if (match(m)) count++;
		}
		return count;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			int K=M-Integer.parseInt(st.nextToken());
			Gophers=new Pos[M];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				Gophers[m]=new Pos(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			}
			Holes=new Pos[N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				Holes[n]=new Pos(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			}
			DistSq=new double[M][N];
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(t);
			sb.append(":\n");
			if (K<=N) {
				double min=10000000.0;
				double max=0.0;
				for (int m=0;m<M;m++) for (int n=0;n<N;n++) {
					DistSq[m][n]=Gophers[m].distSq(Holes[n]);
					min=Math.min(min,DistSq[m][n]);
					max=Math.max(max,DistSq[m][n]);
				}

				double ans=0.0;
				int loop=(int)(Math.log(max-min)+20);
				while (max-min>10e-6 && --loop>0) {
					ans=(min+max)/2;
					MaxDistSq=ans;
					if (compute()>=K) max=ans;
					else min=ans;
				}
				sb.append(String.format("%.3f\n",Math.sqrt(ans)));
			} else sb.append("Too bad.\n");				

			System.out.println(sb);
		}
	}

}
