import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static long count(long num) {
		if (num<0) return 0;

		long sum=1;
		long temp=0;
		int zeroMulti=1;
		while (num>=10) {
			long digit=num%10;
			num/=10;
			sum+=digit==0 ? (num-1)*zeroMulti+(temp+1) : num*zeroMulti;
			temp+=digit*zeroMulti;
			zeroMulti*=10;
		}
		return sum;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long m=Long.parseLong(st.nextToken());
			long n=Long.parseLong(st.nextToken());
			if (m<0) break;

			System.out.println(count(n)-count(m-1));
		}
	}
}
