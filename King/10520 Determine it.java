import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int AN1;
	private static int N;
	private static long [][] Dp;
	
	private static long compute(int i, int j) {
		if (i==N && j==1) return AN1;
		
		if (Dp[i][j]==-1) {
			if (i>=j) {
				long p1=0;
				if (i<N) for (int k=i+1;k<=N;k++) p1=Math.max(p1,compute(k,1)+compute(k,j));
				long p2=0;
				if (j>0) for (int k=1;k<j;k++) p2=Math.max(p2,compute(i,k)+compute(N,k));
				Dp[i][j]=p1+p2;
			} else {
				long max=0;
				for (int k=i;k<j;k++) max=Math.max(max,compute(i,k)+compute(k+1,j));
				Dp[i][j]=max;
			}
		}
		return Dp[i][j];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			AN1=Integer.parseInt(st.nextToken());
			
			Dp=new long [20][20];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
			System.out.println(compute(1,N));
		}
	}

}
