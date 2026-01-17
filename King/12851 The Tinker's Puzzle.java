import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double D=Integer.parseInt(st.nextToken());
			double V=Integer.parseInt(st.nextToken())*282;
			
			/*
			 *     1
			 * V = - pi h (4r^2 + 2r^2 + r^2)
			 *     3
			 *
			 *     7
			 *   = - pi h r^2
			 *     3
			 *     
			 *         3 * V
			 *  r^2 = --------
			 *         7 pi h
			 */
			
			double r=Math.sqrt((3*V)/(7*Math.PI*D));
			System.out.printf("Case %d: %.3f\n",t,r*4);
		}
	}

}