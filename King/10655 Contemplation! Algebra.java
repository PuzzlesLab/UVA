import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static long [][] matMulti(long [][] a, long [][] b) {
		long [][] ans=new long[a.length][a.length];
		for (int i=0;i<a.length;i++) for (int k=0;k<a.length;k++) {
			if (a[i][k]==0) continue;
			for (int j=0;j<a.length;j++) {
				ans[i][j]+=a[i][k]*b[k][j];
			}
		}
		return ans;
	}

	private static long [][] matPow(long [][] base, long p) {
		long [][] ans= {{1,0},{0,1}};
		while (p>0) {
			if ((p&1)==1) ans=matMulti(ans,base);
			base=matMulti(base,base);
			p>>=1;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long p=Long.parseLong(st.nextToken());
			long q=Long.parseLong(st.nextToken());
			if (!st.hasMoreTokens()) break;
			long n=Long.parseLong(st.nextToken());
			if (p==0 && q==0 && n==0) break;
			
			if (n==0) {
				System.out.println(2); // 1+1
				continue;
			} else if (n==1) {
				System.out.println(p); // a+b
				continue;
			}
			
			long [][] ans=matPow(new long [][]{{p,-q},{1,0}},n-1);
			long pn=p*ans[0][0];
			long qn=ans[0][1]<<1;
			System.out.println(pn+qn);
		}
	}

}
