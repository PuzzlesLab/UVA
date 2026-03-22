import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static boolean [] NotPrime=new boolean [1000001];
	private static int [] Dp=new int [NotPrime.length];

	private static void eSieve() {
		NotPrime[0]=true;
		NotPrime[1]=true;
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i])
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		
		for (int i=2;i<Dp.length;i++) {
			Dp[i]=Dp[i-1];
			if (!NotPrime[i]) {
				int digitSum=0;
				int temp=i;
				while (temp>0) {
					digitSum+=temp%10;
					temp/=10;
				}
				if (!NotPrime[digitSum]) Dp[i]++;
			}
		}
	}
	
	public static void main(String [] args) throws Exception {
		eSieve();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int from=Math.max(Integer.parseInt(st.nextToken())-1,0);
			int to=Integer.parseInt(st.nextToken());
			sb.append(Dp[to]-Dp[from]);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}

}