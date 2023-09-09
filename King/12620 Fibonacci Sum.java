import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		// Pisano period (last 2 digits repeats every 300)
		final int PERIOD=300;
		int [] fib=new int [PERIOD];
		fib[0]=1;
		fib[1]=1;
		for (int i=2;i<fib.length;i++) fib[i]=(fib[i-2]+fib[i-1])%100;
		int [] sum=new int [PERIOD];
		sum[0]=1;
		for (int i=1;i<sum.length;i++) sum[i]=sum[i-1]+fib[i];

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long N=Long.parseLong(st.nextToken())-1;
			long M=Long.parseLong(st.nextToken())-1;

			long p1=N/sum.length;
			int r1=(int)(N%sum.length);
			long sum1=p1*sum[sum.length-1]+(r1>0 ? sum[r1-1] : 0);

			long p2=M/sum.length;
			int r2=(int)(M%sum.length);
			long sum2=p2*sum[sum.length-1]+sum[r2];

			System.out.println(sum2-sum1);
		}
	}

}
