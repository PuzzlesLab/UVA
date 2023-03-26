import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX_N=301;

		final int [] diag=new int [MAX_N];
		for (int n=3;n<diag.length;n++) diag[n]=((n-2)*(2+((n-3)<<1)))>>1;
		// Max diagonal count = sum of arithmetic progression, where a=1, n=N-2, d=2

		final double [] ans=new double[MAX_N];
		for (int n=2;n<ans.length;n++) ans[n]=(n*n-diag[n])+diag[n]*Math.sqrt(2);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine(); // empty
			int N=Integer.parseInt(br.readLine());

			if (tc>0) System.out.println();
			System.out.printf("%.3f\n", ans[N]);
		}
	}
}
