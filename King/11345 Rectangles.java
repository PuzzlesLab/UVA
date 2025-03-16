import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Rectangle {
		int x1, y1, x2, y2;
		
		public Rectangle(int x1, int y1, int x2, int y2) {
			this.x1=x1;
			this.y1=y1;
			this.x2=x2;
			this.y2=y2;
		}
	}
	
	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=1;n<=N;n++) {
			int M=Integer.parseInt(br.readLine());
			
			Rectangle [] rects=new Rectangle[M];
			for (int m=0;m<M;m++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				rects[m]=new Rectangle(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}

			Rectangle r=new Rectangle(-10001,-10001,10001,10001);
			for (int m=0;m<M;m++) r=new Rectangle(Math.max(r.x1,rects[m].x1),Math.max(r.y1,rects[m].y1),Math.min(r.x2,rects[m].x2),Math.min(r.y2,rects[m].y2));
			r.x1=Math.min(r.x1,r.x2);
			r.y1=Math.min(r.y1,r.y2);
			
			System.out.printf("Case %d: %d\n",n,(r.x2-r.x1)*(r.y2-r.y1));
		}
 	}

}
