import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class zz {
	
	private static class Point implements Comparable<Point> {
		int x, y;
		
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}

		public double cross(Point p) {
			return this.x*p.y-this.y*p.x;
		}

		public int compareTo(Point p) {
			if (this.y!=p.y) return Double.compare(this.y,p.y);
			return Double.compare(this.x,p.x);
		}
	}

	private static boolean ccw(Point a, Point o, Point b) {
		Point vecOA=new Point(a.x-o.x,a.y-o.y);
		Point vecOB=new Point(b.x-o.x,b.y-o.y);
		return vecOA.cross(vecOB)>=0;
	}

	private static ArrayList<Point> convexHull(ArrayList<Point> points) {
		Collections.sort(points);
		// Ensure no duplicate points.
		ArrayList<Point> clean=new ArrayList<>();
		for (int i=0;i<points.size();i++) {
			Point p=points.get(i);
			if (clean.isEmpty() || clean.get(clean.size()-1).compareTo(p)!=0) clean.add(p);
		}
		points=clean;

		ArrayList<Point> result=new ArrayList<>();
		for (int i=0;i<points.size();i++) {
			Point p=points.get(i);
			while (result.size()>=2 && !ccw(result.get(result.size()-2),result.get(result.size()-1),p)) result.remove(result.size()-1);
			if (result.isEmpty() || result.get(result.size()-1)!=p) result.add(p);
		}
		int lim=result.size()+1;
		for (int i=points.size()-1;i>=0;i--) {
			Point p=points.get(i);
			while (result.size()>=lim && !ccw(result.get(result.size()-2),result.get(result.size()-1),p)) result.remove(result.size()-1);
			if (result.isEmpty() || result.get(result.size()-1)!=p) result.add(p);
		}
		return result;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Point> points=new ArrayList<>();
		String tcName="";
		while (true) {
			String s=br.readLine();
			if (s.equals("END") || s.charAt(0)=='S') {
				if (!points.isEmpty()) {
					ArrayList<Point> ch=convexHull(points);
					ch.remove(ch.size()-1);
					Collections.reverse(ch);

					// Find largest x and start with it.
					int startIdx=0;
					for (int i=1;i<ch.size();i++) if (ch.get(i).x>ch.get(startIdx).x) startIdx=i;

					StringBuilder sb=new StringBuilder();
					sb.append(tcName);
					sb.append(" convex hull:\n");
					for (int i=0;i<ch.size();i++) {
						Point p=ch.get((i+startIdx)%ch.size());
						sb.append(" (");
						sb.append(p.x);
						sb.append(',');
						sb.append(p.y);
						sb.append(')');
					}
					System.out.println(sb);

					points.clear();
				}
				if (s.equals("END")) break;
				tcName=s.substring(2);
			} else if (s.charAt(0)=='P') {
				StringTokenizer st=new StringTokenizer(s);
				st.nextToken();
				int N=Integer.parseInt(st.nextToken());
				for (int n=0;n<N;n++) {
					points.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
				}
			}
		}
 	}

}
