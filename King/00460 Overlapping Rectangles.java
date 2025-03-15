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

		public boolean inRangeX(int x) {
			return x>=this.x1 && x<=this.x2;
		}
		
		public boolean inRangeY(int y) {
			return y>=this.y1 && y<=this.y2;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine(); // Empty
			StringTokenizer st=new StringTokenizer(br.readLine());
			Rectangle r1=new Rectangle(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			st=new StringTokenizer(br.readLine());
			Rectangle r2=new Rectangle(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			Rectangle sol=new Rectangle(0,0,0,0);
			boolean flag=true;

			if (!r1.inRangeX(r2.x1) && !r2.inRangeX(r1.x1)) flag=false;
			if (!r1.inRangeY(r2.y1) && !r2.inRangeY(r1.y1)) flag=false;

			if (flag) {
				// Solve X
				if (r2.x1>=r1.x1 && r2.x2<=r1.x2) { // r2 contained entirely in r1
					sol.x1=r2.x1;
					sol.x2=r2.x2;
				} else if (r1.x1>=r2.x1 && r1.x2<=r2.x2) { // r1 contained entirely in r2
					sol.x1=r1.x1;
					sol.x2=r1.x2;
				} else if (r2.x1>=r1.x1) { // r2 right to r1
					sol.x1=r2.x1;
					sol.x2=r1.x2;
				} else if (r2.x2>=r1.x1){ // r2 left to r1
					sol.x1=r1.x1;
					sol.x2=r2.x2;
				} else flag &=false;
				if (sol.x1==sol.x2) flag&=false;
	
				// Solve Y
				if (r2.y1>=r1.y1 && r2.y2<=r1.y2) { // r2 contained entirely in r1
					sol.y1=r2.y1;
					sol.y2=r2.y2;
				} else if (r1.y1>=r2.y1 && r1.y2<=r2.y2) { // r1 contained entirely in r2
					sol.y1=r1.y1;
					sol.y2=r1.y2;
				} else if (r2.y1>=r1.y1) { // r2 above r1
					sol.y1=r2.y1;
					sol.y2=r1.y2;
				} else if (r2.y2>=r1.y1){ // r2 below r1
					sol.y1=r1.y1;
					sol.y2=r2.y2;
				} else flag &=false;
				if (sol.y1==sol.y2) flag&=false;
			}

			if (tc>0) System.out.println();
			if (flag) System.out.printf("%d %d %d %d\n",sol.x1,sol.y1,sol.x2,sol.y2);
			else System.out.println("No Overlap");
		}
 	}

}
