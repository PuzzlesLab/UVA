import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static boolean [] NotPrime=new boolean [2000001];
	
	private static void eSieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		
		NotPrime[0]=true;
		NotPrime[1]=true;
	}
	
	private static boolean isPalindrome(int n) {
		if (n<10) return true;
		String s=String.valueOf(n);
		for (int i=0;i<s.length()>>1;i++) {
			char c1=s.charAt(i);
			char c2=s.charAt(s.length()-1-i);
			if (c1!=c2) return false;
		}
		return true;
	}

	public static void main(String [] args) throws Exception {
		eSieve();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean ended=false;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			if (ended) continue;

			System.out.println(N<<1);
			if (!NotPrime[N] && isPalindrome(N)) ended=true;
		}
	}

}