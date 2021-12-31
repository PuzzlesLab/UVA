import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static long calc(long n) {
		if (n==0) return 0;
		return calc(n/2)+(n%2);
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			System.out.println(calc(Long.parseLong(br.readLine())));
		}
	}

}
