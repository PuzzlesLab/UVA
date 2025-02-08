import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			double F=Double.parseDouble(s);
			// Figure: https://imgur.com/a/ajNyLD9
			System.out.printf("%.10f\n", F*Math.sin(Math.toRadians(108))/Math.sin(Math.toRadians(63)));
		}
 	}

}
