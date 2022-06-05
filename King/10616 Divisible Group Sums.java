import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	private static int D;
	private static int [] Nums;
	private static BigInteger [][][] Dp;
	
	private static BigInteger countExists(int remUsed, int start, int currSum) {
		if (remUsed==0 && currSum==0) return BigInteger.ONE;
		if (start<0) return BigInteger.ZERO;

		if (Dp[remUsed][start][currSum]==null) {
			BigInteger count=BigInteger.ZERO;
			if (remUsed>0) {
				int temp=(currSum+Nums[start])%D;
				if (temp<0) temp+=D;
				count=count.add(countExists(remUsed-1,start-1,temp));
			}
			count=count.add(countExists(remUsed,start-1,currSum));
			Dp[remUsed][start][currSum]=count;
		}

		return Dp[remUsed][start][currSum];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int setCount=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());
			Nums=new int [N];
			for (int n=0;n<N;n++) Nums[n]=Integer.parseInt(br.readLine());
			
			StringBuilder sb=new StringBuilder();
			sb.append("SET ");
			sb.append(setCount++);
			sb.append(":\n");
			for (int q=1;q<=Q;q++) {
				st=new StringTokenizer(br.readLine());
				D=Integer.parseInt(st.nextToken());
				int M=Integer.parseInt(st.nextToken());
				
				BigInteger ans=BigInteger.ZERO;
				Dp=new BigInteger [M+1][N][D];
				ans=ans.add(countExists(M,N-1,0));

				sb.append("QUERY ");
				sb.append(q);
				sb.append(": ");
				sb.append(ans);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}
