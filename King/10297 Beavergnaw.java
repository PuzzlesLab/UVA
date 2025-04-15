import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			double D=Integer.parseInt(st.nextToken());
			double V=Integer.parseInt(st.nextToken());
			System.out.printf("%.3f\n",Math.cbrt(D*D*D-6*V/Math.PI));
		}
	}

}