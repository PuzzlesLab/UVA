import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Line {
		double a, b, c;

		public Line(StringTokenizer st) {
			this.a=Double.parseDouble(st.nextToken());
			this.b=Double.parseDouble(st.nextToken());
			this.c=Double.parseDouble(st.nextToken());
		}

		public boolean isNull() {
			return this.a==0 && this.b==0 && this.c==0;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			Line l1=new Line(new StringTokenizer(br.readLine()));
			Line l2=new Line(new StringTokenizer(br.readLine()));
			if (l1.isNull() && l2.isNull()) break;
			if (l1.a*l2.b==l1.b*l2.a) System.out.println("No fixed point exists.");
			else {
				double dem=(l1.a*l2.b-l2.a*l1.b);
				double ix=(l2.b*l1.c-l1.b*l2.c)/dem;
				double iy=(l1.a*l2.c-l2.a*l1.c)/dem;
				if (ix==-0) ix=0;
				if (iy==-0) iy=0;
				System.out.printf("The fixed point is at %.2f %.2f.\n",ix,iy);
			}
		}
 	}

}
