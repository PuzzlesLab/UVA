import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			Point [] points=new Point[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points[n]=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(points);
			
			int lastMaxN=-1;
			int startN=0;
			double ans=0;
			while (true) {
				int currMaxN=-1;
				for (int n=startN;n<N;n++) if (currMaxN==-1 || points[n].y>points[currMaxN].y) {
					currMaxN=n;
				}
				if (lastMaxN==-1) {
					startN=currMaxN;
				} else {
					startN=currMaxN+1;
					// We get the slope for lastMaxN & lastMaxN+1;
					double m=(points[lastMaxN+1].y-points[lastMaxN].y)/(points[lastMaxN+1].x-points[lastMaxN].x);
					// y=mx+c, c=y-mx.
					double c=points[lastMaxN].y-m*points[lastMaxN].x;
					// We get the sunny end's x at the slope, with currMax.y. x=(y-c)/m.
					double x1=(points[currMaxN].y-c)/m;
					
					double dx=points[lastMaxN].x-x1;
					double dy=points[lastMaxN].y-points[currMaxN].y;
					ans+=Math.sqrt(dx*dx+dy*dy);
				}
				lastMaxN=currMaxN;
				if (startN==N) break;
			}
			
			System.out.printf("%.2f\n",ans);
		}
 	}

}
