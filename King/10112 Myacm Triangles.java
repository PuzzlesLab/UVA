import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static class Point {
		char c;
		double x, y;
		
		public Point(char c, double x, double y) {
			this.c=c;
			this.x=x;
			this.y=y;
		}
		
		public double cross(Point p) {
			return this.x*p.y-this.y*p.x;
		}

		public double area(Point p1, Point p2) {
			return 0.5*Math.abs(this.cross(p1)+p1.cross(p2)+p2.cross(this));
		}
	}
	
	private static class Triangle {
		Point p1, p2, p3;
		
		public Triangle(Point p1, Point p2, Point p3) {
			this.p1=p1;
			this.p2=p2;
			this.p3=p3;
		}
		
		public double area() {
			return this.p1.area(this.p2,this.p3);
		}

		public boolean out(Point p) {
			double a1=new Triangle(p,this.p1,this.p2).area();
			double a2=new Triangle(p,this.p1,this.p3).area();
			double a3=new Triangle(p,this.p2,this.p3).area();
			double a4=this.area();
			return Math.abs(a1+a2+a3-a4)>=1e-6;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Point [] points=new Point[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points[n]=new Point(st.nextToken().charAt(0),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			Triangle sol=null;
			for (int n=0;n<N;n++) for (int n2=n+1;n2<N;n2++) for (int n3=n2+1;n3<N;n3++) {
				Triangle t=new Triangle(points[n],points[n2],points[n3]);
				if (t.area()==0) continue;
				boolean outside=true;
				for (int i=0;i<N && outside;i++) {
					if (i==n || i==n2 || i==n3) continue;
					outside&=t.out(points[i]);
				}
				if (outside && (sol==null || (sol.area()<t.area()))) sol=t;
			}
			System.out.printf("%c%c%c\n",sol.p1.c,sol.p2.c,sol.p3.c);
		}
 	}

}