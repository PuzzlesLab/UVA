import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			long N=Integer.parseInt(br.readLine());
			if (N<=0) break;
			
			N*=(N==1 ? 0 : 25);
			System.out.printf("%d%%\n", N);
		}
	}

}