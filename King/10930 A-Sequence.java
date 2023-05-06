import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [] Nums;
	private static int [][] Dp;
	private static final int NO_VALUE=-1000;

	private static int getCount(int idx, int currSum) {
		if (currSum<0) return 0;
		else if (currSum==0) return 1;
		if (idx<0) return 0;

		if (Dp[idx][currSum]==NO_VALUE) {
			Dp[idx][currSum]=getCount(idx-1,currSum)+getCount(idx-1,currSum-Nums[idx]);
		}
		return Dp[idx][currSum];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			Nums=new int [N];
			for (int n=0;n<N;n++) Nums[n]=Integer.parseInt(st.nextToken());

			boolean seqOK=true;
			for (int n=0;n<N;n++) {
				if (Nums[n]<1) seqOK=false;
				if (n>0 && Nums[n]<=Nums[n-1]) seqOK=false;
			}

			if (seqOK) {
				Dp=new int [N][Nums[N-1]+1];
				for (int n=0;n<N;n++) Arrays.fill(Dp[n],NO_VALUE);

				for (int n=0;n<N;n++) {
					if (getCount(n-1,Nums[n])>0) {
						seqOK=false;
						break;
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc++);
			sb.append(':');
			for (int n=0;n<N;n++) {
				sb.append(' ');
				sb.append(Nums[n]);
			}
			sb.append("\nThis is ");
			if (!seqOK) sb.append("not ");
			sb.append("an A-sequence.");
			System.out.println(sb.toString());
		}
	}

}