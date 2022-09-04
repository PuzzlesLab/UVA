import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [][] Dp;

	private static int cut(int [] sticks, int left, int right) {
		if (left+1==right) return 0;
		if (Dp[left][right]==-1) {
			int currLength=sticks[right]-sticks[left];
			int min=Integer.MAX_VALUE;
			for (int i=left+1;i<right;i++) min=Math.min(min,cut(sticks,left,i)+cut(sticks,i,right)+currLength);
			Dp[left][right]=min;
		}
		return Dp[left][right];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int L=Integer.parseInt(s);
			int N=Integer.parseInt(br.readLine());

			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] sticks=new int [N+2];
			for (int i=1;i<sticks.length-1;i++) sticks[i]=Integer.parseInt(st.nextToken());
			sticks[N+1]=L;
			
			Dp=new int [sticks.length][sticks.length];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
			System.out.printf("The minimum cutting is %d.\n", cut(sticks,0,N+1));
		}
	}

}
