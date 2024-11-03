import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		final double pi=2*Math.acos(0);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double d=Double.parseDouble(st.nextToken())/2;
			double l=Double.parseDouble(st.nextToken())/2;
			double h=Math.sqrt(l*l-d*d);
			
			System.out.printf("%.3f\n", pi*l*h);
		}
 	}
}
