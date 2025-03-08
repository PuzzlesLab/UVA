import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static class Point {
		double x, y;
		
		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}
	}
	
	private static class Line {
		double a, b, c;
		
		public Line(Point p1, Point p2) {
			if (p1.x==p2.x) {
				this.a=1.0;
				this.b=0.0;
				this.c=-p1.x;
			} else {
				this.a=(p2.y-p1.y)/(p1.x-p2.x);
				this.b=1.0;
				this.c=-(this.a*p1.x)-p1.y;
			}
		}
		
		public Point intersect(Line l) {
			/*
			 * a1x+b1y+c1=0
			 * 
			 * x=(-b1y-c1)/a1
			 * 
			 * a2(-b1y-c1)
			 * ----------- + b2y + c2 = 0
			 *     a1
			 * 
			 * 
			 * a2(-b1y-c1) + a1b2y + a1c2  = 0
			 * 
			 * -a2b1y - a2c1 + a1b2y + a1c2 = 0
			 * 
			 * (a1b2-a2b1)y = a2c1 - a1c2
			 * 
			 *      a2c1 - a1c2
			 * y = ---------------
			 *       a1b2 - a2b1
			 *     
			 */
			double y=(l.a*this.c-this.a*l.c)/(this.a*l.b-l.a*this.b);
			double x=(-this.b*y-this.c)/this.a;
			return new Point(x,y);
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			Point A=new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			Point B=new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			Point C=new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			
			Point D=new Point((2*B.x+C.x)/3,(2*B.y+C.y)/3);
			Point E=new Point((2*C.x+A.x)/3,(2*C.y+A.y)/3);
			Point F=new Point((2*A.x+B.x)/3,(2*A.y+B.y)/3);

			Line AD=new Line(A,D);
			Line BE=new Line(B,E);
			Line CF=new Line(C,F);

			Point P=AD.intersect(BE);
			Point Q=BE.intersect(CF);
			Point R=AD.intersect(CF);

			double area=0.5*Math.abs(P.x*Q.y-Q.x*P.y+Q.x*R.y-R.x*Q.y+R.x*P.y-P.x*R.y);
			System.out.println((int)(area+0.5));
		}
 	}

}