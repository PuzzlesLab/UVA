import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final double MAX_TIME=10000000;
	private static final double EPS=1e-9;

	private static class Point {
		double x, y;
		
		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}
		
		public double distSq(Point p) {
			double dx=this.x-p.x;
			double dy=this.y-p.y;
			return dx*dx+dy*dy;
		}
	}

	private static class Boat {
		Point p, v;
		double d, s;
		
		public Boat(double x, double y, double d, double s) {
			this.p=new Point(x,y);
			this.d=Math.toRadians(d);
			this.s=s;
			this.v=new Point(this.s*Math.sin(this.d),this.s*Math.cos(this.d));
		}

		public double getCollisionTime(Boat b, double size) {
			if (this.p.distSq(b.p)<=size) return 0.0;

			/*
			 * D(t)^2 = (dx + dxvt)^2 + (dy + dyvt)^2
			 * (dxv^2 + dyv^2)t^2 + 2(dxdxv + dy+dyv)t + dx^2 + dy^2 - r^2 = 0
			 * 
			 * Solve t.
			 * 
			 */
			double dx=this.p.x-b.p.x;
			double dy=this.p.y-b.p.y;
			double dxv=this.v.x-b.v.x;
			double dyv=this.v.y-b.v.y;

			double A=dxv*dxv+dyv*dyv;
			double B=2*(dx*dxv+dy*dyv);
			double C=dx*dx+dy*dy-size;
			if (A<EPS) return MAX_TIME;

			double discriminant=B*B-4*A*C;
			if (discriminant<0) return MAX_TIME;
			discriminant=Math.sqrt(discriminant);
			double t1=(-B+discriminant)/(2*A);
			double t2=(-B-discriminant)/(2*A);
			if (t1>=0 && t2>=0) return Math.min(t1,t2);
			if (t1>=0) return t1;
			if (t2>=0) return t2;
			return MAX_TIME;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			double R=Double.parseDouble(st.nextToken());
			R*=R;
			Boat [] boats=new Boat[N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				boats[n]=new Boat(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			}
			
			double min=MAX_TIME;
			for (int n=0;n<N;n++) for (int n2=n+1;n2<N;n2++) min=Math.min(min,boats[n].getCollisionTime(boats[n2],R));
			if (min==MAX_TIME) System.out.println("No collision.");
			else System.out.printf("%.0f\n",min);
		}
	}

}
