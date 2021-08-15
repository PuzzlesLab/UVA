import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			/* f(1) = n-m+1, f(2) = n-2m+2, ... f(k) = n+k(1-m)
			 * 1 = n+k(1-m), find k (integer)
			 */
			int up=Math.abs(1-n), down=Math.abs(1-m);
			int d=up/down, r=up%down;
			System.out.println(r!=0?"cannot do this":d);
		}
	}
}
