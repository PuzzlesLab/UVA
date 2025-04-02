import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

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
			if (this.x!=p.x) return Double.compare(this.x,p.x);
			return Double.compare(this.y,p.y);
		}
		
		public double dist(Point p) {
			double dx=this.x-p.x;
			double dy=this.y-p.y;
			return Math.sqrt(dx*dx+dy*dy);
		}
	}

	private static boolean ccw(Point p, Point q, Point r) {
		Point vecPQ=new Point(q.x-p.x,q.y-p.y);
		Point vecPR=new Point(r.x-p.x,r.y-p.y);
		return vecPQ.cross(vecPR)>0;
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
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double init=Double.parseDouble(st.nextToken());
			int C=Integer.parseInt(st.nextToken());

			ArrayList<Point> nails=new ArrayList<>();
			for (int c=0;c<C;c++) {
				st=new StringTokenizer(br.readLine());
				nails.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}

			nails=convexHull(nails);
			double expanded=0.0;
			for (int i=0;i<nails.size()-1;i++) expanded+=nails.get(i).dist(nails.get(i+1));
			System.out.printf("%.5f\n",Math.max(init,expanded));
			
			br.readLine();
		}
 	}

}
