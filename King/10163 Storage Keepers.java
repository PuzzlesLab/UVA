import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static int N;
	private static int [] P;
	private static int AnsL;
	private static int [][] DpL;
	private static int AnsY;
	private static int [][] DpY;

	private static int findL(int m, int n) {
		if (n==N) return 1000000;
		if (m==P.length) return 0;

		if (DpL[m][n]==-1) {
			int ans=findL(m+1,n);
			for (int k=1;k<=P[m] && k+n<=N;k++) ans=Math.max(ans,Math.min(P[m]/k,findL(m+1,n+k)));
			DpL[m][n]=ans;
		}

		return DpL[m][n];
	}

	private static int findY(int m, int n) {
		if (n==N) return 0;
		if (m==P.length) return 1000000;

		if (DpY[m][n]==-1) {
			int ans=findY(m+1,n);
			for (int k=1;k+n<=N && P[m]/k>=AnsL;k++) {
				ans=Math.min(ans,P[m]+findY(m+1,n+k));
			}
			DpY[m][n]=ans;
		}

		return DpY[m][n];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			P=new int [M];
			for (int m=0;m<M;m++) P[m]=Integer.parseInt(st.nextToken());

			DpL=new int[M][N];
			for (int m=0;m<M;m++) Arrays.fill(DpL[m],-1);
			AnsL=findL(0,0);
			
			if (AnsL==0) AnsY=0;
			else {
				DpY=new int[M][N];
				for (int m=0;m<M;m++) Arrays.fill(DpY[m],-1);
				AnsY=findY(0,0);
			}

			System.out.printf("%d %d\n",AnsL,AnsY);
		}
	}

}