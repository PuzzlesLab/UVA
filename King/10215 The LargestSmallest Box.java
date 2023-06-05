import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			double L=Double.parseDouble(st.nextToken());
			double W=Double.parseDouble(st.nextToken());
			/*
			 * Area = (L-2x)*(W-2x)*x
			 *      = 2x^3 - 2(W+L)x^2 + WLX
			 *  dA
			 * ---- = 12x^2 - 4(W+L)x + WL
			 *  dx
			 *  
			 *  When dA/dx = 0, x is min / max.
			 *  Min => - variant of quadratic solution.
			 *  
			 */
			double b=-4*(L+W);
			double min=(-b-Math.sqrt(b*b-4*12*W*L))/24;
			double max=Math.min(L/2,W/2);

			System.out.printf("%.3f %.3f %.3f\n",min,0.0,max);
		}
	}

}
