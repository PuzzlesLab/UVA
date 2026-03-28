import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_N=10000001;
	private static int [] SoDDP=new int [MAX_N];
	private static int [] Fun1Dp=new int [MAX_N+65]; // 9999999 + 9*7
	private static int [] Fun2Dp=new int [MAX_N];

	private static int sod(int n) {
		if (n<10) return n;
		if (SoDDP[n]==0) SoDDP[n]=n%10+sod(n/10);
		return SoDDP[n];
	}
	
	private static void makeFun1Dp() {
		Arrays.fill(Fun1Dp,-1);
		for (int i=0;i<MAX_N;i++) {
			int ans=i+sod(i);
			if (Fun1Dp[ans]==-1) Fun1Dp[ans]=i;
		}
	}
	
	private static void makeFun2Dp() {
		for (int i=1;i<Fun2Dp.length;i++) {
			Fun2Dp[i]=Fun2Dp[i-1];
			if (Fun1Dp[i]==-1 || Fun1Dp[i]>i) Fun2Dp[i]++;
		}
	}

	public static void main(String [] args) throws Exception {
		makeFun1Dp();
		makeFun2Dp();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int paramsCount=st.countTokens();
			int ans=0;
			if (paramsCount==1) ans=Fun1Dp[Integer.parseInt(st.nextToken())];
			else if (paramsCount==2) {
				int a=Integer.parseInt(st.nextToken())-1;
				int b=Integer.parseInt(st.nextToken());
				ans=Fun2Dp[b]-Fun2Dp[a];
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(ans);
			System.out.println(sb);
		}
	}

}