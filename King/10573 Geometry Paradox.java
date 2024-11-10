import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		final double PI=2*Math.acos(0);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double r1=0.0,r2=0.0,r3=0.0;
			if (st.countTokens()==1) {
				// Tangent - Assume r1=r2, so it will cut through the origin of large circle.
				// r3=t/2 and r1=r2=r3/2.
				double t=Double.parseDouble(st.nextToken());
				r3=t/2;
				r1=r3/2;
				r2=r3/2;
			} else {
				// 2 Values
				r1=Double.parseDouble(st.nextToken());
				r2=Double.parseDouble(st.nextToken());
				r3=r1+r2;
			}
			
			double A=PI*(r3*r3-r2*r2-r1*r1);
			System.out.printf("%.4f\n",A);
		}
 	}
}
