import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int MAX=100001;
	private static boolean [] NotPrime;

	private static int getSign(int n) {
		if (n==2) return 1;
		if (!NotPrime[n] && (n+1)%4==0) return 1; // IsPrime & 4m-1
		if (!NotPrime[n] && (n-1)%4==0) return -1; // IsPrime & 4m+1

		int sign=1;
		for (int i=2;i<MAX;i++) if (!NotPrime[i] && n%i==0) {
			while (n%i==0) {
				sign*=getSign(i);
				n/=i;
			}
			if (n==1) break;
		}
		return sign;
	}

	public static void main(String[] args) throws Exception {
		NotPrime=new boolean [MAX];
		for (int i=2;i*i<NotPrime.length;i++) {
			if (NotPrime[i]) continue;
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int I=Integer.parseInt(br.readLine());
			System.out.println(getSign(I)==-1?'-':'+');
		}
	}

}
