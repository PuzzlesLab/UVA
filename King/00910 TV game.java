import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [][] To;
	private static boolean [] IsSpecial;
	private static int M;
	private static int [][] Dp;

	private static int count(int n, int m) {
		if (m==M) return IsSpecial[n] ? 1 : 0;
		if (Dp[n][m]==-1) Dp[n][m]=count(To[n][0],m+1)+count(To[n][1],m+1);
		return Dp[n][m];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);

			To=new int [N][2];
			IsSpecial=new boolean [N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int from=st.nextToken().charAt(0)-'A';
				To[from][0]=st.nextToken().charAt(0)-'A';
				To[from][1]=st.nextToken().charAt(0)-'A';
				IsSpecial[from]=st.nextToken().charAt(0)=='x';
			}
			M=Integer.parseInt(br.readLine());

			Dp=new int [N][M+1];
			for (int n=0;n<N;n++) Arrays.fill(Dp[n],-1);
			System.out.println(count(0,0));
		}
	}

}
