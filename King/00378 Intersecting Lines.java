import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static class Line {
		double a, b, c;
		
		public Line(double x1, double y1, double x2, double y2) {
			if (x1==x2) {
				this.a=1;
				this.b=0;
				this.c=-x1;
			} else {
				this.a=-(y1-y2)/(x1-x2);
				this.b=1.0;
				this.c=-(this.a*x1)-y1;
			}
		}
		
		public boolean areParallel(Line l) {
			return this.a==l.a && this.b==l.b;
		}
		
		public boolean areSame(Line l) {
			return areParallel(l) && this.c==l.c;
		}
		
		public double [] intersect(Line l) {
			if (areParallel(l)) return null;
			double [] ans=new double [2];
			ans[0]=(l.b*this.c - this.b*l.c)/(l.a*this.b-this.a*l.b);
			ans[1]=(this.b!=0.0) ? -(this.a*ans[0]+this.c) : -(l.a*ans[0]+l.c);
			return ans;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		System.out.println("INTERSECTING LINES OUTPUT");
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			Line l1=new Line(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			Line l2=new Line(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			
			if (l1.areSame(l2)) System.out.println("LINE");
			else if (l1.intersect(l2)!=null)  {
				double [] ans=l1.intersect(l2);
				System.out.printf("POINT %.2f %.2f\n",ans[0],ans[1]);
			} else System.out.println("NONE");
		}
		System.out.println("END OF OUTPUT");
 	}

}
