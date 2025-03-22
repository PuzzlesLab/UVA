import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static double solveQuadratic(double a, double b, double c) {
		return (-b+Math.sqrt(b*b-4*a*c))/(2*a);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double AB=Integer.parseInt(st.nextToken());
			double CD=Integer.parseInt(st.nextToken());
			double AD=Integer.parseInt(st.nextToken());
			double BC=Integer.parseInt(st.nextToken());
			
			/*
			 * 
			 * x+y=AB-CD
			 * x^2 + h^2 = AD^2
			 * y^2 + h^2 = BC^2
			 * 
			 * x^2 - y^2 = AD^2 - BC^2
			 * x^2 - (AB-CD-x)^2 = AD^2 - BC^2
			 * x^2 - [(AB-CD)^2-(2AB+2CD)x+x^2] = AD^2 - BC^2
			 * 2(AB+CD)x - (AB-CD)^2 = AD^2 - BC^2
			 *      (AB-CD)^2+AD^2-BC^2
			 * x = -----------------------
			 *            2(AB-CD)
			 * 
			 * Then we find x, y, h.
			 */
			double x=((AB-CD)*(AB-CD)+AD*AD-BC*BC)/(2*(AB-CD));
			double h=Math.sqrt(AD*AD-x*x);
			/*
			 * 
			 * NOTE: For some reason, solving h1 will cause precision to go off a lot and solution will not be tally with sample output.
			 * 
			 * (AB+CD)h = 2*(AB+EF)*h1
			 * (AB+CD)h = 2*(CD+EF)*h2
			 * 
			 *  AE = (h1/h)*AD;
			 *  BF = (h2/h)*BC;
			 *             AB-CD
			 *  EF = CD + -------- h2
			 *               h
			 * 
			 *                            AB-CD
			 *  (AB+CD)h = 2*(CD + CD + --------- h2)*h2
			 *                             h
			 *
			 *                         AB-CD
			 *  (AB+CD)h = 4CD h1 + 2 ------- * h2^2
			 *                           h
			 *
			 *  (AB+CD)h^2 = 4CDh h2 + 2(AB-CD)h2^2
			 *  
			 *  2(AB-CD) h2^2 + 4CDh h2 - (AB+CD)h^2 = 0
			 *  
			 *  Solve quadratic:
			 *  a=2(AB-CD)
			 *  b=4CDh
			 *  c=-(AB+CD)h^2
			 *  
			 */
			double a=2*(AB-CD);
			double b=4*CD*h;
			double c=-(AB+CD)*h*h;
			double h2=solveQuadratic(a,b,c);
			System.out.printf("Land #%d: %.6f %.6f\n", tc, ((h-h2)/h)*AD, ((h-h2)/h)*BC);
			
		}
 	}

}