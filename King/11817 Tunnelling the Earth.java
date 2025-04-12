import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final double R=6371009;

	private static class Point {
		double la, lo;
		
		public Point(double la, double lo) {
			this.la=la;
			this.lo=lo;
		}
	}

	private static class Point3D {
		double x, y, z;
		
		public Point3D(Point p) {
			double laR=Math.toRadians(p.la);
			double loR=Math.toRadians(p.lo);
			this.x=R*Math.cos(laR)*Math.cos(loR);
			this.y=R*Math.cos(laR)*Math.sin(loR);
			this.z=R*Math.sin(laR);
		}

		public double dist(Point3D p) {
			double dx=this.x-p.x;
			double dy=this.y-p.y;
			double dz=this.z-p.z;
			return Math.sqrt(dx*dx+dy*dy+dz*dz);
		}
	}

	private static double gcDist(Point a, Point b, double r) {
		// https://en.wikipedia.org/wiki/Haversine_formula
		double f=Math.PI/180;
		double aLa=a.la*f;
		double aLo=a.lo*f;
		double bLa=b.la*f;
		double bLo=b.lo*f;

		double va=Math.cos(aLa)*Math.cos(aLo)*Math.cos(bLa)*Math.cos(bLo);
		double vb=Math.cos(aLa)*Math.sin(aLo)*Math.cos(bLa)*Math.sin(bLo);
		double vc=Math.sin(aLa)*Math.sin(bLa);
		return r*Math.acos(va+vb+vc);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			Point a=new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			Point b=new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			
			double gcDist=gcDist(a,b,R);
			double tunnelDist=new Point3D(a).dist(new Point3D(b));
			System.out.println((int)(gcDist-tunnelDist+0.5));
		}
	}

}