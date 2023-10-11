import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [][] matMulti(int [][] a, int [][] b, int m) {
		int [][] ans=new int [a.length][a.length];
		for (int i=0;i<a.length;i++) for (int k=0;k<a.length;k++) {
			if (a[i][k]==0) continue;
			for (int j=0;j<a.length;j++) {
				ans[i][j]+=(a[i][k]%m)*(b[k][j]%m);
				ans[i][j]%=m;
			}
		}
		return ans;
	}

	private static int [][] matPow(int [][] mat, int p, int m) {
		int [][] ans=new int [mat.length][mat.length];
		for (int i=0;i<mat.length;i++) ans[i][i]=1;

		while (p>0) {
			if ((p&1)==1) ans=matMulti(ans,mat,m);
			mat=matMulti(mat,mat,m);
			p>>=1;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int D=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] coef=new int [D];
			for (int d=0;d<D;d++) coef[d]=Integer.parseInt(st.nextToken())%M;
					
			st=new StringTokenizer(br.readLine());
			int [] f=new int [D];
			for (int d=0;d<D;d++) f[d]=Integer.parseInt(st.nextToken())%M;
			
			int [][] mat=new int [D][D];
			for (int d=0;d<D;d++) {
				mat[0][d]=coef[d];
				if (d>0) mat[d][d-1]=1;
			}

			int [][] result=matPow(mat,N-D,M);
			int ans=0;
			for (int d=0;d<D;d++) ans=(ans+result[0][d]*f[D-1-d])%M;
			System.out.println(ans);
			
			br.readLine(); // empty line
		}
	}

}
