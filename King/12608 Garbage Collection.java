import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	private static class Point implements Comparable<Point> {
		int dist, weight;
		public Point (int d, int w) { this.dist=d; this.weight=w; }
		public int compareTo(Point p) {
			if (this.dist==p.dist) return this.weight-p.weight;
			return this.dist-p.dist;
		}
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int W=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			ArrayList<Point> points=new ArrayList<>();
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				points.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}
			
			int totalDist=0;
			int currW=0, currDist=0;
			for (Point p : points) {
				if (currW == W) {
					totalDist+=currDist;
					currDist=0;
					currW = 0;
				} else if (currW+p.weight>W) {
					totalDist+=p.dist+(p.dist-currDist);
					currW = 0;
					currDist = 0;
				}
				totalDist+=(p.dist-currDist);
				currW+=p.weight;
				currDist=p.dist;
			}
			totalDist+=currDist;
			
			System.out.println(totalDist);
		}
	}

}
