import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			boolean isSquare=((int)Math.sqrt(N)*(int)Math.sqrt(N)) == N;
			boolean isCube=((int)Math.cbrt(N)*(int)Math.cbrt(N)*(int)Math.cbrt(N)) == N;
			System.out.println((isSquare && isCube) ? "Special" : "Ordinary");
		}
	}

}