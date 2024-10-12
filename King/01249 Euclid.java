import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		double x, y;

		public Tuple(StringTokenizer st) {
			this.x=Double.parseDouble(st.nextToken());
			this.y=Double.parseDouble(st.nextToken());
		}
		
		public Tuple(double x, double y) {
			this.x=x;
			this.y=y;
		}
		
		public boolean isOrigin() {
			return this.x==0 && this.y==0;
		}
	}

	private static double triangleArea(Tuple p1, Tuple p2, Tuple p3) {
		double r=0.5*(p1.x*p2.y+p2.x*p3.y+p3.x*p1.y-p1.y*p2.x-p2.y*p3.x-p3.y*p1.x);
		return Math.abs(r);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			Tuple A=new Tuple(st);
			Tuple B=new Tuple(st);
			Tuple C=new Tuple(st);
			Tuple D=new Tuple(st);
			Tuple E=new Tuple(st);
			Tuple F=new Tuple(st);
			
			if (A.isOrigin() && B.isOrigin() && C.isOrigin() && D.isOrigin() && E.isOrigin() && F.isOrigin()) break;
			
			double area=triangleArea(D,E,F);
			Tuple AB=new Tuple(B.x-A.x,B.y-A.y);
			Tuple ACUnit=new Tuple(C.x-A.x,C.y-A.y);
			double AClength=Math.hypot(ACUnit.x,ACUnit.y);
			ACUnit.x/=AClength;
			ACUnit.y/=AClength;

			double areaUnit=(triangleArea(A,B,C)*2)/AClength;
			double AHlength=area/areaUnit;

			Tuple H=new Tuple(A.x+ACUnit.x*AHlength,A.y+ACUnit.y*AHlength);
			Tuple G=new Tuple(H.x+AB.x,H.y+AB.y);

			System.out.printf("%.3f %.3f %.3f %.3f\n",G.x,G.y,H.x,H.y);
		}
 	}
}