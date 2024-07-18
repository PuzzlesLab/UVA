import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Point implements Comparable<Point> {
		int x, y;
		
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public int compareTo(Point pos) {
			if (this.x!=pos.x) return this.x-pos.x;
			return this.y-pos.y;
		}
	}

	private static Point [] Points;
	private static int Sol;

	private static void find(int s, int e) {
		if (s>=e) return;
		int mid=(s+e)>>1;
		find(s,mid);
		find(mid+1,e);
		for (int i=s;i<=mid;i++) {
			if (Points[mid].x-Points[i].x>=Sol) continue;
			// There can be maximum 7 points in an area of (2*Sol x Sol + Self)
			for (int i2=mid+1;i2<=Math.min(mid+7,e);i2++) {
				if (Points[i2].x-Points[i].x>=Sol) break;
				Sol=Math.min(Sol,Math.max(Math.abs(Points[i2].x-Points[i].x),Math.abs(Points[i2].y-Points[i].y)));
			}
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			Points=new Point[N];

			for (int i=0;i<N;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Points[i]=new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(Points);

			Sol=Integer.MAX_VALUE;
			find(0,Points.length-1);
			System.out.println(Sol);
		}
	}

}