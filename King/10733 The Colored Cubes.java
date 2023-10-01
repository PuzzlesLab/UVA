import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			long N=Integer.parseInt(br.readLine());
			if (N==0) break;

			// https://en.wikipedia.org/wiki/Burnside%27s_lemma
			long ans=((N*N*N*N*N*N)+(3*N*N*N*N)+(12*N*N*N)+(8*N*N))/24;
			System.out.println(ans);
		}
	}

}
