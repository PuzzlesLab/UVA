import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [][] matMulti(int [][] a, int [][] b, int mod) {
		int [][] result=new int [a.length][a.length];
		for (int i=0;i<a.length;i++) for (int k=0;k<a.length;k++) {
			if (a[i][k]==0) continue;
			for (int j=0;j<a.length;j++) {
				result[i][j]+=((a[i][k]%mod)*(b[k][j]%mod))%mod;
				result[i][j]%=mod;
			}
		}
		return result;
	}

	private static int [][] matPow(int [][] base, int p, int mod) {
		int [][] ans=new int [base.length][base.length];
		for (int i=0;i<base.length;i++) ans[i][i]=1;
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
			int L=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			int S=Integer.parseInt(st.nextToken())-1;
			int T=Integer.parseInt(st.nextToken())-1;
			
			int [][] adjMat=new int [N][N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				while (st.hasMoreTokens()) adjMat[n][Integer.parseInt(st.nextToken())-1]++;
			}
			int [][] result=matPow(adjMat,L,10000);

			System.out.println(result[S][T]);
		}
	}

}
