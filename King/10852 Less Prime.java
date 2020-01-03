import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static boolean [] notPrime=new boolean [10001];

	public static void main (String [] args) throws Exception {
		notPrime[0]=true;
		notPrime[1]=true;
		for (int i=2;i*i<notPrime.length;i++) if (!notPrime[i]) for (int i2=i*i;i2<notPrime.length;i2+=i) notPrime[i2]=true;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int M=Integer.parseInt(br.readLine());
		for (int m=0;m<M;m++) {
			int n=Integer.parseInt(br.readLine());
			
			int maxValue=Integer.MIN_VALUE;
			int ans=-1;
			for (int x=2;x<=n;x++) if (!notPrime[x]) {
				int p=n/x;
				int test=n-p*x;
				if (test>maxValue) {
					maxValue=test;
					ans=x;
				}
			}
			System.out.println(ans);
		}
	}

}