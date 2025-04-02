import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	private static class Point implements Comparable<Point> {
		long x, y;
		
		public Point(long x, long y) {
			this.x=x;
			this.y=y;
		}

		public long cross(Point p) {
			return this.x*p.y-this.y*p.x;
		}

		public int compareTo(Point p) {
			if (this.x!=p.x) return Long.compare(this.x,p.x);
			return Long.compare(this.y,p.y);
		}
	}

	private static boolean ccw(Point p, Point q, Point r) {
		Point vecPQ=new Point(q.x-p.x,q.y-p.y);
		Point vecPR=new Point(r.x-p.x,r.y-p.y);
		return vecPQ.cross(vecPR)>=0;
	}

	private static ArrayList<Point> convexHull(ArrayList<Point> points) {
		Collections.sort(points);
		ArrayList<Point> result=new ArrayList<>();

		for (int i=0;i<points.size();i++) {
			int rSize=result.size();
			while ((rSize>=2) && !ccw(result.get(rSize-2),result.get(rSize-1),points.get(i))) {
				result.remove(result.size()-1);
				rSize--;
			}
			result.add(points.get(i));
		}
		int t=result.size()+1;
		for (int i=points.size()-2;i>=0;i--) {
			int rSize=result.size();
			while ((rSize>=t) && !ccw(result.get(rSize-2),result.get(rSize-1),points.get(i))) {
				result.remove(result.size()-1);
				rSize--;
			}
			result.add(points.get(i));
		}
		return result;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			
			ArrayList<Point> points=new ArrayList<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Point p=new Point(Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()));
				if (st.nextToken().charAt(0) == 'N') continue;
				points.add(p);
			}

			points=convexHull(points);
			StringBuilder sb=new StringBuilder();
			sb.append(points.size()-1);
			sb.append('\n');
			for (int i=0;i<points.size()-1;i++) {
				sb.append(points.get(i).x);
				sb.append(' ');
				sb.append(points.get(i).y);
				sb.append('\n');
			}
			System.out.print(sb);
		}
 	}

}