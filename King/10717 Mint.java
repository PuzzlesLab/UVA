import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static long gcd(long a, long b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			 StringTokenizer st=new StringTokenizer(s);
			 int N=Integer.parseInt(st.nextToken());
			 int T=Integer.parseInt(st.nextToken());
			 
			 long [] coins=new long[N];
			 for (int n=0;n<N;n++) coins[n]=Integer.parseInt(br.readLine());
			 
			 long gcd=coins[0];
			 for (int n=1;n<N;n++) gcd=gcd(gcd,coins[n]);

			 for (int t=0;t<T;t++) {
				 long base=(Integer.parseInt(br.readLine())/gcd)*gcd;
				 
				 long low=-1;
				 long temp=base;
				 while (low==-1) {
					 int okCount=0;
					 for (int n=0;n<N && okCount<4;n++) if (temp%coins[n]==0) okCount++;
					 if (okCount==4) {
						 low=temp;
						 break;
					 }
					 temp-=gcd;
				 }
				 
				 long high=-1;
				 temp=base;
				 while (high==-1) {
					 int okCount=0;
					 for (int n=0;n<N && okCount<4;n++) if (temp%coins[n]==0) okCount++;
					 if (okCount==4) {
						 high=temp;
						 break;
					 }
					 temp+=gcd;
				 }
				 
				 System.out.printf("%d %d\n",low,high);
			 }
		}
	}

}