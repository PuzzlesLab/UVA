import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Cherry {
		int x, y;
		
		public Cherry(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Cherry [] cherries=new Cherry[N<<1];
			for (int n=0;n<cherries.length;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				cherries[n]=new Cherry(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			boolean found=false;
			int solA=0;
			int solB=0;
			for (int a=-500;a<=500 && !found;a++) for (int b=-500;b<=500 && !found;b++) {
				// Line = Ax+By=0, y=-A/B x;
				int up=0;
				int down=0;
				for (int n=0;n<cherries.length;n++) {
					int excess=a*cherries[n].x+b*cherries[n].y;
					if (excess>0) up++;
					else if (excess==0) break;
					else down++;
				}
				if (up==N && down==N) {
					found=true;
					solA=a;
					solB=b;
				}
			}
			System.out.printf("%d %d\n",solA,solB);
		}
	}

}