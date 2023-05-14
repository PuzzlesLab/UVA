import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			int N=Integer.parseInt(br.readLine());
			int sqrt=(int)Math.sqrt(N);
			sqrt*=sqrt;

			long res=0;
			for (long i=1;i*i<=N;i++) res+=(N/i);
			System.out.println((res<<1)-sqrt);
		}
	}

}
