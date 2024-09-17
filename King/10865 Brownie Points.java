import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Point {
		int x, y;
		
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Point [] points=new Point[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points[n]=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			int cutX=points[N>>1].x;
			int cutY=points[N>>1].y;
			int stan=0;
			int ollie=0;
			for (int n=0;n<N;n++) {
				if (points[n].x==cutX || points[n].y==cutY) continue;

				if ((points[n].x>cutX && points[n].y>cutY) || (points[n].x<cutX && points[n].y<cutY)) stan++;
				else ollie++;
			}
			
			System.out.printf("%d %d\n",stan,ollie);
		}
 	}

}
