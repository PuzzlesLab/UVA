import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

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
	}

	private static Tuple getCenter(Tuple p1, Tuple p2, Tuple p3) {
		/*
		 * 
		 * Formula
		 * 
		 * x1 = dist p1-p2
		 * x2 = dist p1-p3
		 * x3 = dist p2-p3
		 * 
		 *   x1 sin 2A + x2 sin 2B + x3 sin 2C     y1 sin 2A + y2 sin 2B + y3 sin 2C
		 * -------------------------------------, -------------------------------------
		 *    sin 2A + sin 2B + sin 2C                 sin 2A + sin 2B + sin 2C
		 * 
		 * A = angle opposite to x1
		 * B = angle opposite to x2
		 * C = angle opposite to x3
		 * 
		 */
		double d12=p1.dist(p2);
		double d13=p1.dist(p3);
		double d23=p2.dist(p3);
		
		double a1=Math.acos((d23*d23-d12*d12-d13*d13)/(-2*d12*d13));
		double a2=Math.acos((d13*d13-d12*d12-d23*d23)/(-2*d12*d23));
		double a3=Math.acos((d12*d12-d13*d13-d23*d23)/(-2*d13*d23));

		double x=(p1.x*Math.sin(2*a1)+p2.x*Math.sin(2*a2)+p3.x*Math.sin(2*a3))/(Math.sin(2*a1)+Math.sin(2*a2)+Math.sin(2*a3));
		double y=(p1.y*Math.sin(2*a1)+p2.y*Math.sin(2*a2)+p3.y*Math.sin(2*a3))/(Math.sin(2*a1)+Math.sin(2*a2)+Math.sin(2*a3));
		return new Tuple(x,y);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			StringTokenizer st=new StringTokenizer(br.readLine());
			Tuple p1=new Tuple(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			st=new StringTokenizer(br.readLine());
			Tuple p2=new Tuple(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			st=new StringTokenizer(br.readLine());
			Tuple p3=new Tuple(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			Tuple cc=getCenter(p1,p2,p3);

			double minx=Integer.MAX_VALUE;
			double maxx=Integer.MIN_VALUE;
			double miny=Integer.MAX_VALUE;
			double maxy=Integer.MIN_VALUE;
			
			double ang=2*Math.PI/N;
			for (int n=0;n<N;n++) {
				double dx=p1.x-cc.x;
				double dy=p1.y-cc.y;
				double nx=cc.x+dx*Math.cos(ang*n)-dy*Math.sin(ang*n);
				double ny=cc.y+dx*Math.sin(ang*n)+dy*Math.cos(ang*n);

				minx=Math.min(minx,nx);
				maxx=Math.max(maxx,nx);
				miny=Math.min(miny,ny);
				maxy=Math.max(maxy,ny);
			}

			double area=(maxx-minx)*(maxy-miny);
			System.out.printf("Polygon %d: %.3f\n",tc,area);
			tc++;
		}
 	}

}