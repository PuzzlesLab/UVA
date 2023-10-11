import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static long last(long n, int k) {
		final int M=1000;
		n%=M;
		long ans=1;
		while (k>0) {
			if ((k&1)==1) ans=(ans*n)%M;
			n=(n*n)%M;
			k>>=1;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long N=Long.parseLong(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			double lgP=K*Math.log10(N);
			int d=(int)(Math.pow(10,lgP-Math.floor(lgP))*100);
			System.out.printf("%03d...%03d\n",d,last(N,K));
		}
	}

}
