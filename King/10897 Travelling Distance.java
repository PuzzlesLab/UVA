import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final double R=6371.01;

	private static class Point {
		double la, lo;
		
		public Point(double la, double lo) {
			this.la=la;
			this.lo=lo;
		}
	}

	private static double gcDist(Point a, Point b) {
		// https://en.wikipedia.org/wiki/Haversine_formula
		double f=Math.PI/180;
		double aLa=a.la*f;
		double aLo=a.lo*f;
		double bLa=b.la*f;
		double bLo=b.lo*f;

		double va=Math.cos(aLa)*Math.cos(aLo)*Math.cos(bLa)*Math.cos(bLo);
		double vb=Math.cos(aLa)*Math.sin(aLo)*Math.cos(bLa)*Math.sin(bLo);
		double vc=Math.sin(aLa)*Math.sin(bLa);
		return R*Math.acos(va+vb+vc);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double la1d=Double.parseDouble(st.nextToken());
			double la1m=Double.parseDouble(st.nextToken());
			double la1s=Double.parseDouble(st.nextToken());
			la1d+=(la1m*60+la1s)/3600;
			char la1C=st.nextToken().charAt(0);
			if (la1C=='S') la1d=-la1d;

			double lo1d=Double.parseDouble(st.nextToken());
			double lo1m=Double.parseDouble(st.nextToken());
			double lo1s=Double.parseDouble(st.nextToken());
			lo1d+=(lo1m*60+lo1s)/3600;
			char lo1C=st.nextToken().charAt(0);
			if (lo1C=='E') lo1d=-lo1d;

			double la2d=Double.parseDouble(st.nextToken());
			double la2m=Double.parseDouble(st.nextToken());
			double la2s=Double.parseDouble(st.nextToken());
			la2d+=(la2m*60+la2s)/3600;
			char la2C=st.nextToken().charAt(0);
			if (la2C=='S') la2d=-la2d;

			double lo2d=Double.parseDouble(st.nextToken());
			double lo2m=Double.parseDouble(st.nextToken());
			double lo2s=Double.parseDouble(st.nextToken());
			lo2d+=(lo2m*60+lo2s)/3600;
			char lo2C=st.nextToken().charAt(0);
			if (lo2C=='E') lo2d=-lo2d;

			Point p1=new Point(la1d,lo1d);
			Point p2=new Point(la2d,lo2d);
			System.out.printf("%.2f\n", gcDist(p1,p2));
		}
	}

}
