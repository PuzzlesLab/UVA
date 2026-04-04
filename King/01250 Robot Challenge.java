import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Point {
		int x,y,p;
		
		public Point(int x, int y, int p) {
			this.x=x;
			this.y=y;
			this.p=p;
		}
		
		public double dist(Point p) {
			int dx=this.x-p.x;
			int dy=this.y-p.y;
			return Math.sqrt(dx*dx+dy*dy);
		}
	}

	private static double [][] Dp;
	private static Point [] Points;
	private static int N;

	private static double compute(int prev, int curr) {
		if (curr==N+1) return Points[prev].dist(Points[curr])+1;
		if (Dp[prev][curr]<0) {
			double s1=Points[prev].dist(Points[curr])+1+compute(curr,curr+1);
			double s2=Points[curr].p+compute(prev,curr+1);
			Dp[prev][curr]=Math.min(s1,s2);
		}
		return Dp[prev][curr];
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			N=Integer.parseInt(s);
			Points=new Point[N+2];
			Points[0]=new Point(0,0,0);
			for (int n=1;n<=N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Points[n]=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			Points[N+1]=new Point(100,100,0);
			Dp=new double [Points.length][Points.length];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);

			System.out.printf("%.3f\n",compute(0,1));
		}
	}

}