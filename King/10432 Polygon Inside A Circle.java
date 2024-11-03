import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			double r=Double.parseDouble(st.nextToken());
			double n=Double.parseDouble(st.nextToken());
			double h=r*Math.sin((2*Math.PI)/n);
			System.out.printf("%.3f\n", (0.5*r*h)*n);
		}
 	}
}
