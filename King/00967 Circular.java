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
		
		for (int i=100;i<NotPrime.length;i++) {
			Dp[i]=Dp[i-1];
			if (!NotPrime[i]) {
				int len=0;
				int temp=i;
				int tenth=1;
				while (temp>0) {
					temp/=10;
					len++;
					tenth*=10;
				}
				tenth/=10;

				temp=i;
				boolean flag=true;
				for (int i2=0;i2<len;i2++) {
					int last=temp%10;
					temp=temp/10+last*tenth;
					flag&=!NotPrime[temp];
				}

				if (flag) Dp[i]++;
			}
		}
	}
	
	public static void main(String [] args) throws Exception {
		eSieve();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb=new StringBuilder();
		while (!(s=br.readLine()).equals("-1")) {
			StringTokenizer st=new StringTokenizer(s);
			int from=Math.max(Integer.parseInt(st.nextToken())-1,0);
			int to=Integer.parseInt(st.nextToken());
			int ans=Dp[to]-Dp[from];
			
			if (ans==0) sb.append("No Circular Primes.\n");
			else if (ans==1) sb.append("1 Circular Prime.\n");
			else {
				sb.append(ans);
				sb.append(" Circular Primes.\n");
			}
		}
		System.out.print(sb);
	}

}