import java.util.Scanner;

class Main {
	
	private static class Point implements Comparable<Point> {
		double x, y;
		
		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}
		
		public int compareTo(Point p) {
			if (this.x!=p.x) return Double.compare(this.x,p.x);
			return Double.compare(this.y,p.y);
		}
		
		public boolean equals(Point p) {
			return this.compareTo(p)==0;
		}
	}

	public static void main(String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (sc.hasNextDouble()) {
			Point p1=new Point(sc.nextDouble(),sc.nextDouble());
			Point p2=new Point(sc.nextDouble(),sc.nextDouble());
			Point p3=new Point(sc.nextDouble(),sc.nextDouble());
			Point p4=new Point(sc.nextDouble(),sc.nextDouble());
			
			// Make sure p2=p3
			if (p1.equals(p3)) {
				Point t=p1;
				p1=p2;
				p2=t;
			} else if (p1.equals(p4)) {
				Point t=p1;
				p1=p2;
				p2=t;
				
				t=p3;
				p3=p4;
				p4=t;
			} else if (p2.equals(p4)) {
				Point t=p3;
				p3=p4;
				p4=t;
			}

			double dx=p4.x-p3.x;
			double dy=p4.y-p3.y;
			System.out.printf("%.3f %.3f\n",p1.x+dx,p1.y+dy);
			
		}
 	}

}
