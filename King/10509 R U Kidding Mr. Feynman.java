import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			double n=Double.parseDouble(s);
			double a=Math.floor(Math.cbrt(n)); // Find nearest a.
			double dx=(n-Math.pow(a,3))/(3*a*a);
			System.out.printf("%.4f\n",a+dx);
		}
	}
}
