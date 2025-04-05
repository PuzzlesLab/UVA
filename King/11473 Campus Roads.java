import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	private static class Point {
		double x, y;
		
		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}

		public double dist(Point p) {
			double dx=p.x-this.x;
			double dy=p.y-this.y;
			return Math.sqrt(dx*dx+dy*dy);
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=1;n<=N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int K=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			
			ArrayList<Point> points=new ArrayList<>();
			for (int k=0;k<K;k++) {
				st=new StringTokenizer(br.readLine());
				points.add(new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())));
			}

			double gap=0.0;
			for (int k=0;k<K-1;k++) gap+=points.get(k).dist(points.get(k+1));
			gap/=(T-1);

			ArrayList<Point> sol=new ArrayList<>();
			sol.add(points.get(0));
			double distToCover=0.0;
			for (int k=1;k<K;k++) {
				Point p1=points.get(k-1);
				Point p2=points.get(k);
				double ang=Math.atan2(p2.y-p1.y,p2.x-p1.x);
				distToCover+=p1.dist(p2);
				while (distToCover>=gap) {
					distToCover-=gap;
					sol.add(new Point(p2.x-distToCover*Math.cos(ang),p2.y-distToCover*Math.sin(ang)));
				}
			}
			if (sol.size()<T) sol.add(points.get(points.size()-1));

			StringBuilder sb=new StringBuilder();
			sb.append("Road #");
			sb.append(n);
			sb.append(":\n");
			for (int k=0;k<sol.size();k++) {
				sb.append(String.format("%.2f %.2f", sol.get(k).x, sol.get(k).y));
				sb.append('\n');
			}
			System.out.println(sb);
		}
 	}

}
