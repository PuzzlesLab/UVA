import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		int MOD = 1000000007;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());

		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			String A=br.readLine();
			
			int [] nums=new int [A.length()];
			for (int i=0;i<A.length();i++) nums[i]=A.charAt(i)-'0';

			long ans = 0;
			
			int p1e=(nums.length-1)/2;
			int p2s=nums.length/2;
			
			if (p1e==p2s) {
				ans=nums[p1e];
				p1e--;
				p2s++;
			}

			while (p1e>=0 && p2s<nums.length) {
				if (nums[p1e]>nums[p2s]) {
					ans=((ans<<1)+nums[p1e])%MOD;
					ans=((ans<<1)+nums[p2s])%MOD;
				} else {
					ans=((ans<<1)+nums[p2s])%MOD;
					ans=((ans<<1)+nums[p1e])%MOD;
				}
				p1e--;
				p2s++;
			}

			System.out.printf("Case #%d: %d\n", testCase, ans);
		}
	}
}