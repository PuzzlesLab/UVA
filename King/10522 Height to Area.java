import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		final double EPS=1e-6;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double Ha=Double.parseDouble(st.nextToken());
			double Hb=Double.parseDouble(st.nextToken());
			double Hc=Double.parseDouble(st.nextToken());

			/*
			 * Problem didn't state that the areas the same. :/
			 * 
			 * AC = A / 0.5 * Ha
			 * BC = A / 0.5 * Hb
			 * AB = A / 0.5 * Hc
			 * 
			 * s = 0.5 * AC+BC+AB
			 * 
			 * A^A = s * (s-AC) * (s-BC) * (s-AB)
			 * 
			 * Solve A.
			 * 
			 */

			boolean invalid=false;
			if (Ha>=EPS && Hb>=EPS && Hc>=EPS) {
				Ha=1/Ha;
				Hb=1/Hb;
				Hc=1/Hc;
				
				double p1=Ha+Hb+Hc;
				double p2=-Ha+Hb+Hc;
				double p3=Ha-Hb+Hc;
				double p4=Ha+Hb-Hc;
				
				if (p1<EPS || p2<EPS || p3<EPS || p4<EPS) invalid=true;
				else System.out.printf("%.3f\n",Math.sqrt(1/(p1*p2*p3*p4)));
			} else invalid=true;
			
			if (invalid) {
				System.out.println("These are invalid inputs!");
				N--;
				if (N==0) break;
			}
		}
 	}

}
