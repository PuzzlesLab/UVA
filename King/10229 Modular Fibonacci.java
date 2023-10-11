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

	private static long [][] matPow(long [][] base, int p, int mod) {
		long [][] ans= {{1,0},{0,1}};
		while (p>0) {
			if ((p&1)==1) ans=matMulti(ans,base,mod);
			base=matMulti(base,base,mod);
			p>>=1;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=1<<Integer.parseInt(st.nextToken());

			long [][] result=matPow(new long [][]{{1,1},{1,0}},N,M);
			System.out.println(result[0][1]);
		}
	}

}
