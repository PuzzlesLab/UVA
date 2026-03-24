import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_N=1000001;
	private static boolean [] NotPrime=new boolean[MAX_N];
	private static int [][] Dp;
	private static int [] Sums;
	private static int AnsL;
	private static int AnsR;
	private static int AnsLen;
	
	private static void eSieve() {
		NotPrime[0]=NotPrime[1]=true;
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i])
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
	}
	
	private static int getAns(int l, int r) {
		if (r-l<1) return MAX_N;
		if (l>=Dp.length || r<0) return MAX_N;

		if (Dp[l][r]==0) {
			int ans=MAX_N;
			int currSum=Sums[r]-(l>0?Sums[l-1]:0);
			if (!NotPrime[currSum]) {
				ans=r-l+1;
				if (ans<AnsLen) {
					AnsLen=ans;
					AnsL=l;
					AnsR=r;
				}
			}
			ans=Math.min(ans,getAns(l,r-1));
			ans=Math.min(ans,getAns(l+1,r));
			Dp[l][r]=ans;
		}
		return Dp[l][r];
	}

	public static void main(String [] args) throws Exception {
		eSieve();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int [] nums=new int [N];
			for (int n=0;n<N;n++) nums[n]=Integer.parseInt(st.nextToken());
			
			Sums=new int [N];
			for (int n=0;n<N;n++) Sums[n]=(n>0?Sums[n-1]:0)+nums[n];

			Dp=new int [N][N];
			AnsLen=MAX_N;
			int ans=getAns(0,N-1);
			if (ans==MAX_N) System.out.println("This sequence is anti-primed.");
			else {
				StringBuilder sb=new StringBuilder();
				sb.append("Shortest primed subsequence is length ");
				sb.append(ans);
				sb.append(':');
				for (int i=AnsL;i<=AnsR;i++) {
					sb.append(' ');
					sb.append(nums[i]);
				}
				System.out.println(sb);
			}
		}
	}

}