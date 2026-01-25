import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Student {
		double x;
		double y;
		
		public Student(double x, double y) {
			this.x=x;
			this.y=y;
		}
		
		public double dist(Student s) {
			double dx=this.x-s.x;
			double dy=this.y-s.y;
			return Math.sqrt(dx*dx+dy*dy);
		}
	}

	private static Student [] Stds;
	private static double [][] Dist;
	private static double [] Dp;
	private static final double UNSET=1000000.0;

	private static int setBit(int mask, int n) {
		return mask | 1<<n;
	}
	
	private static boolean isBitSet(int mask, int n) {
		return (mask & 1<<n) != 0;
	}

	private static double count(int mask, int remTeam) {
		if (remTeam==0) return 0.0;

		if (Dp[mask]==UNSET) {
			double minSum=1000000.0;
			for (int n=0;n<Stds.length;n++) if (!isBitSet(mask,n)) {
				int maskN=setBit(mask,n);
				for (int n2=n+1;n2<Stds.length;n2++) if (!isBitSet(maskN,n2)) {
					minSum=Math.min(minSum,Dist[n][n2]+count(setBit(maskN,n2),remTeam-1));
				}
			}
			Dp[mask]=minSum;
		}
		return Dp[mask];
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s)*2;
			Stds=new Student[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				st.nextToken();
				Stds[n]=new Student(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			}
			Dist=new double[N][N];
			for (int n=0;n<N;n++) for (int n2=n+1;n2<N;n2++) Dist[n][n2]=Dist[n2][n]=Stds[n].dist(Stds[n2]);
			int mask=0;
			Dp=new double[1<<N];
			Arrays.fill(Dp,UNSET);
			System.out.printf("Case %d: %.2f\n",tc++,count(mask,N>>1));
		}
	}

}
