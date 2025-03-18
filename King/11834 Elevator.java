import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int L=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int R1=Integer.parseInt(st.nextToken());
			int R2=Integer.parseInt(st.nextToken());

			boolean ok=false;
			if (R1+R1<=Math.min(L,C) && R2+R2<=Math.min(L,C)) { // Make sure at least one ball can go inside.
				// Place first circle
				int x1=R1;
				int y1=R1;
				// Place second circle as close to opposite side as possible to form diagonal.
				int x2=L-R2;
				int y2=C-R2;
				// Check if distance between 2 circle center >= R1+R2.
				int x=x1-x2;
				int y=y1-y2;
				int r=R1+R2;
				ok=x*x+y*y>=r*r;
			}

			System.out.println(ok?'S':'N');
		}
 	}

}