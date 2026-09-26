import java.util.Arrays;
import java.util.Scanner;

class Main {
	
	private static class Point implements Comparable<Point> {
		double x, y;
		
		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}
		
		public int compareTo(Point p) {
			return (p.x!=this.x)?Double.compare(this.x,p.x):Double.compare(this.y,p.y);
		}
		
		public double dist(Point p) {
			double dx=this.x-p.x;
			double dy=this.y-p.y;
			return Math.sqrt(dx*dx+dy*dy);
		}
	}
	
	private static Point [] Points;
	private static double [][] Dp;

	private static double compute(int l, int r) {
		int n=Math.max(l,r)+1;
		
		if (Dp[l][r]<0) {
			double ans=0.0;
			if (n==Points.length-1) ans=Points[l].dist(Points[n])+Points[n].dist(Points[r]);
			else ans=Math.min(Points[l].dist(Points[n])+compute(n,r),Points[n].dist(Points[r])+compute(l,n));
			Dp[l][r]=ans;
		}
		return Dp[l][r];
	}

	public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNextInt()) {
        	int N=sc.nextInt();
        	
        	Points=new Point [N];
        	for (int n=0;n<N;n++) Points[n]=new Point(sc.nextInt(),sc.nextInt());
        	
        	Dp=new double [N][N];
        	for (int n=0;n<N;n++) Arrays.fill(Dp[n],-100000.0);

        	System.out.printf("%.2f\n",compute(0,0));
        }
	}

}