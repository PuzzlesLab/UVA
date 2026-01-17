import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double D=Double.parseDouble(st.nextToken());
			double N=Double.parseDouble(st.nextToken());
			
			/*
			 * 2 pi (r + D) = N * 2 * pi * r
			 * 
			 *      D
			 * r = ----
			 *    (N-1)
			 * 
			 */
			double r=D/(N-1);
			System.out.printf("Case %d: %.3f\n",t,2*Math.PI*(r+D));
		}
	}

}