import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [] Left;
	private static int [] Right;
	private static int [][] Dp;
	private static final int NULL=-100000;
	
	private static int lcs(int l, int r) {
		if (l>=Left.length || r>=Right.length) return 0;
		if (Dp[l][r]==NULL) {
			int count=0;
			count=Math.max(count, lcs(l+1,r));
			count=Math.max(count, lcs(l,r+1));
			count=Math.max(count, (Left[l]==Right[r]?1:0)+lcs(l+1,r+1));
			Dp[l][r]=count;
		}
		return Dp[l][r];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N1=Integer.parseInt(st.nextToken());
			int N2=Integer.parseInt(st.nextToken());
			if (N1==0 && N2==0) break;
			
			Left=new int [N1];
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N1;n++) Left[n]=Integer.parseInt(st.nextToken());

			Right=new int [N2];
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N2;n++) Right[n]=Integer.parseInt(st.nextToken());
			
			Dp=new int [N1][N2];
			for (int n=0;n<N1;n++) Arrays.fill(Dp[n], NULL);
			
			System.out.printf("Twin Towers #%d\nNumber of Tiles : %d\n\n",tc++,lcs(0,0));
		}
	}
}