import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final double EPS=0.000001;

	private static class Tuple {
		double x, y;
		
		public Tuple(double x, double y) {
			this.x=x;
			this.y=y;
		}
		
		public double dist(Tuple t) {
			double dx=this.x-t.x;
			double dy=this.y-t.y;
			return Math.sqrt(dx*dx+dy*dy);
		}
		
		public double dot(Tuple t) {
			return this.x*t.x+this.y*t.y;
		}
		
		public double norm_sq() {
			return this.x*this.x+this.y*this.y;
		}
		
		public Tuple translate(Tuple t) {
			return new Tuple(this.x+t.x,this.y+t.y);
		}
		
		public Tuple scale(double s) {
			return new Tuple(this.x*s,this.y*s);
		}
	}
	
	public static class Line {
		double a, b, c;
		
		public Line(Tuple p1, Tuple p2) {
			if (p1.x==p2.x) {
				this.a=1.0;
				this.b=0.0;
				this.c=-p1.x;
			} else {
				this.a=-(p1.y-p2.y)/(p1.x-p2.x);
				this.b=1.0;
				this.c=-(this.a*p1.x)-p1.y;
			}
		}
		
		public boolean pointExists(Tuple t) {
			return Math.abs(this.a*t.x+this.b*t.y+this.c)<EPS;
		}
	}

	private static double distToLine(Tuple p, Tuple a, Tuple b) {
		Tuple ap=new Tuple(p.x-a.x,p.y-a.y);
		Tuple ab=new Tuple(b.x-a.x,b.y-a.y);
		double u=ap.dot(ab)/ab.norm_sq();
		Tuple c=a.translate(ab.scale(u));
		return c.dist(p);
	}

	private static double distToLineSegment(Tuple p, Tuple a, Tuple b) {
		Tuple ap=new Tuple(p.x-a.x,p.y-a.y);
		Tuple ab=new Tuple(b.x-a.x,b.y-a.y);
		double u=ap.dot(ab)/ab.norm_sq();
		if (u<0) return p.dist(a);
		if (u>1) return p.dist(b);
		return distToLine(p,a,b);
	}
	
	private static double angle(Tuple p1, Tuple p2) {
		double angle=Math.acos(p1.dot(p2)/Math.sqrt(p1.norm_sq()*p2.norm_sq()));
		return angle;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			Tuple center=new Tuple(0,0);
			Tuple p1=new Tuple(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			Tuple p2=new Tuple(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			double r=Double.parseDouble(st.nextToken());

			// We check the distance of line segment to center of circle.
			// Intersection exists if distance is <= r.
			if (distToLineSegment(center,p1,p2)-r>=EPS) {
				sb.append(String.format("%.3f\n",p1.dist(p2)));
				continue;
			}

			/*
			 * Let the points of intersection be p3 and p4.
			 * p3 is nearer to p1, p4 is nearer to p4.
			 * p1-p3-origin will form a right angle, same applies to p2-p4-origin
			 * With a^2+b^2+c^2, we can get distance(p1,p3) & distance(p2,p4)
			 * 
			 * Let:
			 * - a be angle of p1-origin-p3 -> Can be gotten with arccos(r / p1-origin)
			 * - b be angle of p2-origin-p4 -> Can be gotten with arccos(r / p2-origin)
			 * - c be angle of p1-origin-p2 -> Can be gotten with angle between points method above.
			 * - d be angle between p3-origin-p4 -> d=c-a-b
			 * 
			 * distance(p3,p4) = d/360 * 2*pi*r
			 */
			double p1c=p1.dist(center);
			double p2c=p2.dist(center);
			
			double angle1=Math.acos(r/p1c);
			double angle2=Math.acos(r/p2c);
			double angle3=angle(p1,p2)-angle1-angle2;

			double ans=p1.dist(p2);
			if (angle3>0) {
				double d1=Math.sqrt(p1c*p1c-r*r);
				double d3=Math.sqrt(p2c*p2c-r*r);
				ans=d1+(angle3*r)+d3; //(angle3/2*PI)*2*PI*r;
			}
			sb.append(String.format("%.3f\n",ans));
		}
		System.out.print(sb);
 	}
}
