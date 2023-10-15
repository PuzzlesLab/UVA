import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static long [][] matMulti(long [][] a, long [][] b, long mod) {
		long [][] result=new long [a.length][a.length];
		for (int i=0;i<a.length;i++) for (int k=0;k<a.length;k++) {
			if (a[i][k]==0) continue;
			for (int j=0;j<a.length;j++) {
				result[i][j]+=(a[i][k]%mod)*(b[k][j]%mod);
				result[i][j]%=mod;
			}
		}
		return result;
	}

	private static long [][] matPow(long [][] base, long p, long mod) {
		long [][] ans= {{1,0,0},{0,1,0},{0,0,1}};
		while (p>0) {
			if ((p&1)==1) ans=matMulti(ans,base,mod);
			base=matMulti(base,base,mod);
			p>>=1;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		final long MOD=1000000009L;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long N=Long.parseLong(st.nextToken());
			if (N==0) break;

			long [][] ans=matPow(new long [][]{{1,1,1},{1,0,0},{0,1,0}},N,MOD);
			System.out.println(ans[1][1]);
		}
	}

}
