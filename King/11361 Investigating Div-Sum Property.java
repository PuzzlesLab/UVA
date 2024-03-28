import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [][][][] Dp;
	private static int [] Digits;
	private static int K;

	private static int countHelper(int i, int flag, int sumMod, int valMod) {
		if (i<0) return (sumMod==0 && valMod==0) ? 1 : 0;
		if (Dp[i][flag][sumMod][valMod]==-1) {
			int max=flag==1?Digits[i]:9;
			int ans=0;
			for (int n=0;n<=max;n++) ans+=countHelper(i-1,(flag==1&&n==Digits[i])?1:0,(sumMod+n)%K,(valMod*10+n)%K);
			Dp[i][flag][sumMod][valMod]=ans;
		}
		return Dp[i][flag][sumMod][valMod];
	}
	
	private static int [] getDigits(int n) {
		if (n==0) return new int [] {0};
		int [] nums=new int [(int)(Math.log10(n)+1)];
		int i=0;
		while (n>0) {
			nums[i]=n%10;
			n/=10;
			i++;
		}
		return nums;
	}

	private static int count(int n) {
		Digits=getDigits(n);
		Dp=new int [Digits.length+1][2][K+1][K+1];
		for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++) for (int i3=0;i3<Dp[i][i2].length;i3++) {
			Arrays.fill(Dp[i][i2][i3],-1);
		}
		return countHelper(Digits.length-1,1,0,0);
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int A=Integer.parseInt(st.nextToken())-1;
			int B=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());

			System.out.println(K<=90?(count(B)-count(A)):0);
		}
	}
}