import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double L=Integer.parseInt(st.nextToken());
			double W=Integer.parseInt(st.nextToken());
			double rad=Math.toRadians(Integer.parseInt(st.nextToken()));
			
			// We now should get a taller(extended) triangle here.
			double A=L/Math.cos(rad); // Hypot of triangle / Distance without reflection
			double W2=A*Math.sin(rad); // Taller W.

			int bounces=(int)(W2/W);
			W2%=W; // Extra after reflections
			if ((bounces&1)!=0) W2=W-W2; // Bounce count is odd, bounce from top.
			W2=Math.hypot(W2,L);
			System.out.printf("%.3f\n", A/W2);
		}
 	}
}