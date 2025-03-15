import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			int R=Integer.parseInt(br.readLine());
			int w=R*5;
			int h=w*6/10;

			int x1=-w*45/100;
			int x2=w*55/100;
			int y1=-h>>1;
			int y2=h>>1;

			System.out.printf("Case %d:\n%d %d\n%d %d\n%d %d\n%d %d\n",t,x1,y2,x2,y2,x2,y1,x1,y1);
		}
 	}

}
