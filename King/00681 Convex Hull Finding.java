import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	private static class Point implements Comparable<Point> {
		int x, y;
		
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public int compareTo(Point p) {
			if (this.y!=p.y) return this.y-p.y;
			return this.x-p.x;
		}
	}

	private static boolean ccw(Point p, Point q, Point r) {
		Point vecPQ=new Point(q.x-p.x,q.y-p.y);
		Point vecPR=new Point(r.x-p.x,r.y-p.y);
		return (vecPQ.x*vecPR.y-vecPQ.y*vecPR.x)>0;
	}

	private static ArrayList<Point> convexHull(ArrayList<Point> points) {
		ArrayList<Point> result=new ArrayList<>();
		Collections.sort(points);

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
		int K=Integer.parseInt(br.readLine());
		System.out.println(K);
		for (int k=0;k<K;k++) {
			String s;
			while ((s=br.readLine())!=null) {
				if (s.isEmpty()) break;

				int N=Integer.parseInt(s);
				ArrayList<Point> points=new ArrayList<>();
				for (int n=0;n<N;n++) {
					StringTokenizer st=new StringTokenizer(br.readLine());
					points.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
				}
				ArrayList<Point> sol=convexHull(points);

				StringBuilder sb=new StringBuilder();
				sb.append(sol.size());
				sb.append('\n');
				for (int i=0;i<sol.size();i++) {
					sb.append(sol.get(i).x);
					sb.append(' ');
					sb.append(sol.get(i).y);
					sb.append('\n');
				}
				
				s=br.readLine();
				if (s!=null) {
					sb.append(s.trim()); // -1
					sb.append('\n');
				}
				System.out.print(sb);
			}
		}
 	}

}