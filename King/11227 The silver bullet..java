import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

	private static class Point implements Comparable<Point> {
		double x, y;
		
		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}
		
		public int compareTo(Point p) {
			return (this.x!=p.x) ? Double.compare(this.x,p.x) : Double.compare(this.y,p.y);
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			ArrayList<Point> points=new ArrayList<>();
			TreeSet<Point> pointSet=new TreeSet<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Point p=new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
				if (!pointSet.contains(p)) {
					pointSet.add(p);
					points.add(p);
				}
			}
			
			int ans=0;
			if (pointSet.size()<=2) ans=N;
			else {
				for (int n=0;n<points.size();n++) for (int n2=n+1;n2<points.size();n2++) {
					Point p1=points.get(n);
					Point p2=points.get(n2);
					double a=p1.y-p2.y;
					double b=p2.x-p1.x;
					double c=p1.x*p2.y-p2.x*p1.y;
					
					int count=0;
					for (int n3=0;n3<points.size();n3++) {
						Point p3=points.get(n3);
						double eq=a*p3.x+b*p3.y+c;
						if (Math.abs(eq)<1e-7) count++;
					}
					ans=Math.max(ans, count);
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Data set #");
			sb.append(tc);
			sb.append(" contains ");
			if (points.size()==1) sb.append("a single gnu.");
			else {
				sb.append(points.size());
				sb.append(" gnus, out of which a maximum of ");
				sb.append(ans);
				sb.append(" are aligned.");
			}
			System.out.println(sb.toString());
		}
	}

}
