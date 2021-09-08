import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int [] dp=new int [30001];

	private static int calc(int X) {
		if (dp[X]==0) {
			int currPos=0;
			for (int x=2;x<=X;x++) currPos=(currPos+2)%x;
			dp[X]=currPos+1;
		}
		return dp[X];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int curr=Integer.parseInt(br.readLine());
			
			int loopCount=0;
			while (true) {
				int next=calc(curr);
				boolean flag=next==curr;
				curr=next;
				if (flag) break;
				loopCount++;
			}
			
			System.out.printf("Case %d: %d %d\n",testCase,loopCount,curr);
		}
	}

}
