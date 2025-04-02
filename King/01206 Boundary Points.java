import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main {
	
	private static class Point implements Comparable<Point> {
		double x, y;
		
		public Point(double x, double y) {
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

	private static boolean ccw(Point p, Point q, Point r) {
		Point vecPQ=new Point(q.x-p.x,q.y-p.y);
		Point vecPR=new Point(r.x-p.x,r.y-p.y);
		return vecPQ.cross(vecPR)>0;
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
		String s;
		while ((s=br.readLine())!=null) {
			String [] parts=s.split(" ");
			ArrayList<Point> points=new ArrayList<>();
			for (int i=0;i<parts.length;i++) {
				String temp=parts[i];
				temp=temp.substring(1,temp.length()-1);
				String [] temp2=temp.split(",");
				points.add(new Point(Double.parseDouble(temp2[0]),Double.parseDouble(temp2[1])));
			}

			Collections.sort(points);
			ArrayList<Point> sol=convexHull(points);
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<sol.size();i++) {
				sb.append('(');
				sb.append(sol.get(i).x);
				sb.append(',');
				sb.append(sol.get(i).y);
				sb.append(") ");
			}
			if (sb.charAt(sb.length()-1)==' ') sb.setLength(sb.length()-1);
			System.out.println(sb);
		}
 	}

}