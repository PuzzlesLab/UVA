import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Point {
		double la, lo;
		
		public Point(double la, double lo) {
			this.la=la;
			this.lo=lo;
		}
	}

	private static double gcDist(Point a, Point b) {
		// https://en.wikipedia.org/wiki/Haversine_formula (R=1)
		double f=Math.PI/180;
		double aLa=a.la*f;
		double aLo=a.lo*f;
		double bLa=b.la*f;
		double bLo=b.lo*f;

		double va=Math.cos(aLa)*Math.cos(aLo)*Math.cos(bLa)*Math.cos(bLo);
		double vb=Math.cos(aLa)*Math.sin(aLo)*Math.cos(bLa)*Math.sin(bLo);
		double vc=Math.sin(aLa)*Math.sin(bLa);
		return Math.acos(va+vb+vc);
	}

	public static void main(String [] args) throws Exception {
		final double EPS=1e-6;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			Point [] points=new Point[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points[n]=new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			}
			
			double [][] dp=new double[N][N];
			for (int n=0;n<N;n++) for (int n2=n+1;n2<N;n2++) {
				double v=gcDist(points[n],points[n2]);
				dp[n][n2]=v;
				dp[n2][n]=v;
			}
			double minDist=99999999.9;
			int ans=0;
			for (int n=0;n<N;n++) {
				double maxDist=0.0;
				for (int n2=0;n2<N;n2++) if (n!=n2) maxDist=Math.max(maxDist,dp[n][n2]);
				if (maxDist-EPS<minDist) {
					minDist=maxDist;
					ans=n;
				}
			}
			System.out.printf("%.2f %.2f\n",points[ans].la,points[ans].lo);
		}
	}

}