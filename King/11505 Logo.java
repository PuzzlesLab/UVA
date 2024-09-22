import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			double x=0.0;
			double y=0.0;
			double angle=0.0;
			
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				char c=st.nextToken().charAt(0);
				double move=Double.parseDouble(st.nextToken());
				if (c=='f') {
					x+=move*Math.cos(Math.toRadians(angle));
					y+=move*Math.sin(Math.toRadians(angle));
				} else if (c=='b') {
					x-=move*Math.cos(Math.toRadians(angle));
					y-=move*Math.sin(Math.toRadians(angle));
				} else if (c=='l') angle=(angle+move)%360;
				else if (c=='r') angle=(angle-move)%360;
			}
			System.out.println((int)Math.round(Math.hypot(x,y)));
		}
 	}

}