import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static long oneToN(long n) {
		return (n*(n+1))>>1;
	}

	private static long count(long n) { // Sum of digits from 0 to n.
		long ans=0;
		while (n>0) {
			ans+=(n/10)*45+oneToN(n%10);
			n/=10;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int p=Integer.parseInt(st.nextToken());
			int q=Integer.parseInt(st.nextToken());
			if (p<0 || q<0) break;

			System.out.println(count(q)-count(p-1));
		}
	}
}
