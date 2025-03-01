import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	private static class Tuple implements Comparable<Tuple> {
		double x, y;
		public Tuple(double x, double y) {
			this.x=x;
			this.y=y;
		}
		
		public int compareTo(Tuple t) {
			if (this.x!=t.x) return Double.compare(this.x,t.x);
			return Double.compare(this.y,t.y);
		}
	}

	private static class Bulb {
		double x, y;
		public Bulb(String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.x=Integer.parseInt(st.nextToken());
			this.y=Integer.parseInt(st.nextToken());
		}
	}
	
	public static class Pipe {
		double x, y, r;
		public Pipe(String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.x=Integer.parseInt(st.nextToken());
			this.y=Integer.parseInt(st.nextToken());
			this.r=Integer.parseInt(st.nextToken());
		}
	}

	private static Tuple projectRay(Bulb light, Pipe pipe) {
		/*
		 * x0, y0 = Circle origin
		 * x1, y1 = Light point
		 * 
		 * Line to circle origin equation:
		 * y-y0     y1-y0
		 * ----- = ------
		 * x-x0     x1-x0
		 * 
		 * y-y0 = m(x-x0)
		 * y-y0 = mx - mx0
		 * mx - y - mx0 + y0 = 0
		 * A = m
		 * B = -1
		 * C = -mx0 + y0
		 *
		 * -----------------------------------
		 *  https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_line
		 * 
		 * |Ax+By+C|
		 * ----------   =  r
		 * sqrt(A*A+B*B)
		 * 
		 * We get the 2 points on circle.
		 * 
		 * 
		 * Put in the earlier equation:
		 *   |m(x0-x) - (y0-y) - mx0 + y0|
		 * --------------------------------------- = r
		 *         sqrt(m*m + 1)
		 *
		 *  |mx0 - mx - y0 + y - mx0 + y0|
		 * ------------------------------ = r
		 *         sqrt(m*m + 1)
		 *
		 *    |y - mx|
		 * -------------- = r
		 *  sqrt(m*m + 1)
		 *  
		 *  y^2 - 2xym + x^2m^2 = r^2m^2 + r^2
		 *  
		 *  (r^2-x^2)m^2 + 2xym + r^2 - y^2 = 0
		 *
		 *  Solve m.
		 *  
		 */
		
		double dx=light.x-pipe.x;
		double dy=light.y-pipe.y;
		double a=pipe.r*pipe.r-dx*dx;
		double b=2*dx*dy;
		double c=pipe.r*pipe.r-dy*dy;
		
		double discri=b*b-4*a*c;
		if (discri<0) return null;

		if (Math.abs(a)<=0.000001) { // Pipe's edge is at same x with bulb.
			double m=c/b;
			double x1=(light.y+m*light.x)/m;
			return new Tuple(Math.min(x1,light.x),Math.max(x1,light.x));
		}

		double m1=(-b+Math.sqrt(b*b-4*a*c))/(2*a);
		double m2=(-b-Math.sqrt(b*b-4*a*c))/(2*a);
		
		/*     y - y0
		 * m = -------
		 *     x - x0
		 *     
		 * y - y0 = m(x-x0)
		 * mx = y - y0 + mx0
		 * 
		 * x0, y0 = light position.
		 * We find x when y=0.
		 * 
		 * x = (-y0+mx0)/m
		 * 
		 */
		double x1=(-light.y+m1*light.x)/m1;
		double x2=(-light.y+m2*light.x)/m2;
		return new Tuple(Math.min(x1,x2),Math.max(x1,x2));
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;
			
			Bulb bulb=new Bulb(br.readLine());
			Pipe [] pipes=new Pipe [N];
			for (int n=0;n<pipes.length;n++) pipes[n]=new Pipe(br.readLine());

			ArrayList<Tuple> sol=new ArrayList<>();
			for (int n=0;n<pipes.length;n++) {
				Pipe p=pipes[n];
				Tuple r=projectRay(bulb,p);
				if (r!=null) sol.add(projectRay(bulb, p));
			}
			Collections.sort(sol);

			for (int i=1;i<sol.size();i++) {
				Tuple last=sol.get(i-1);
				if (last.y>=sol.get(i).x) {
					last.y=Math.max(last.y,sol.get(i).y);
					sol.remove(i);
					i--;
				}
			}

			StringBuilder sb=new StringBuilder();
			for (int i=0;i<sol.size();i++) {
				sb.append(String.format("%.2f %.2f\n",sol.get(i).x,sol.get(i).y));
			}
			System.out.println(sb.toString());
		}
 	}

}