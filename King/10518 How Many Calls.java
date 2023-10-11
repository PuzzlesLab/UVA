import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static long [][] matMulti(long [][] a, long [][] b, long mod) {
		long [][] ans=new long [a.length][a.length];
		for (int i=0;i<a.length;i++) for (int k=0;k<a.length;k++) {
			if (a[i][k]==0) continue;
			for (int j=0;j<a.length;j++) {
				ans[i][j]+=(a[i][k]%mod)*(b[k][j]%mod);
				ans[i][j]%=mod;
			}
		}
		return ans;
	}
	
	private static long [][] matPow(long [][] mat, long p, long mod) {
		long [][] ans= {{1,0,0},{0,1,0},{0,0,1}};
		while (p>0) {
			if ((p&1)==1) ans=matMulti(ans,mat,mod);
			mat=matMulti(mat,mat,mod);
			p>>=1;
		}
		return ans;
	}

	/*
	 * Fn     = Fn-1 + Fn-2 + 1
	 * Fn-1   = Fn-1
	 * 1      = 1
	 * 
	 *      = (1 1 1)   (Fn-1)
	 *        (1 0 0) x (Fn-2)
	 *        (0 0 1)   (1)
	 * 
	 *      = (1 1 1)^(n-2)    (F1)
	 *        (1 0 0)        x (F0)
 	 *        (0 0 1)          (1)
 	 *        
 	 *      Where F0 = F1 = 1
	 */
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for (int tc=1;true;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long N=Long.parseLong(st.nextToken());
			long B=Integer.parseInt(st.nextToken());
			if (N==0 && B==0) break;
			
			long [][] result=matPow(new long [][]{{1,1,1},{1,0,0},{0,0,1}},N-1,B);
			long ans=0;
			for (int i=0;i<3;i++) ans=(ans+result[0][i])%B;
			System.out.printf("Case %d: %d %d %d\n",tc,N,B,ans);
		}
	}

}
