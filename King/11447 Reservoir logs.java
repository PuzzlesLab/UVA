import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
			if (this.y!=p.y) return Double.compare(this.y,p.y);
			return Double.compare(this.x,p.x);
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine();
			int N=Integer.parseInt(br.readLine());
			
			ArrayList<Point> points=new ArrayList<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points.add(new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())));
			}
			double W=Double.parseDouble(br.readLine());
			double full=0.0;
			for (int n=0;n<N;n++) full+=points.get(n).cross(points.get((n+1)%N));
			full=Math.abs(full)*0.5*W;

			StringTokenizer st=new StringTokenizer(br.readLine());
			double curr=(Double.parseDouble(st.nextToken())/100)*full;
			double use=Double.parseDouble(st.nextToken());
			double add=Double.parseDouble(st.nextToken());
			
			StringBuilder sb=new StringBuilder();
			if (use>curr) sb.append("Lack of water. ");
			curr=Math.max(0.0,curr-use);
			if (curr+add>full) sb.append("Excess of water. ");
			curr=Math.min(curr+add,full);
			sb.append("Final percentage: ");
			sb.append((int)(curr*100/full));
			sb.append('%');
			System.out.println(sb);
		}
 	}

}