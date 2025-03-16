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
		
		public int area() {
			return (this.x2-this.x1)*(this.y2-this.y1);
		}
	}
	
	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=1;n<=N;n++) {
			Rectangle [] weaks=new Rectangle[2];
			for (int i=0;i<weaks.length;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				weaks[i]=new Rectangle(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			Rectangle strong=new Rectangle(Math.max(weaks[0].x1,weaks[1].x1),Math.max(weaks[0].y1,weaks[1].y1),Math.min(weaks[0].x2,weaks[1].x2),Math.min(weaks[0].y2,weaks[1].y2));
			strong.x1=Math.min(strong.x1,strong.x2);
			strong.y1=Math.min(strong.y1,strong.y2);

			int weakArea=weaks[0].area()+weaks[1].area()-strong.area()-strong.area();
			int unsecured=100*100-weakArea-strong.area();

			System.out.printf("Night %d: %d %d %d\n",n,strong.area(),weakArea,unsecured);
		}
 	}

}
