import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int Ans;

	private static boolean isPalindrome(int [] nums) {
		for (int i=0;i<nums.length/2;i++) if (nums[i]!=nums[nums.length-1-i]) return false;
		return true;
	}

	private static void tryInsert(int [] nums, int left, int right, int addCount, int max) {
		if (left>=right) {
			Ans=Math.min(Ans,addCount);
			return;
		}

		if (nums[left]!=nums[right] && addCount<Ans && addCount<max) {
			tryInsert(nums,left,right-1,addCount+1,max); // Add left
			tryInsert(nums,left+1,right,addCount+1,max); // Add right
		} else if (nums[left]==nums[right]) {
			tryInsert(nums,left+1,right-1,addCount,max);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] nums=new int [N];
			for (int n=0;n<N;n++) nums[n]=Integer.parseInt(st.nextToken());
			Ans=Integer.MAX_VALUE;

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(": ");
			if (isPalindrome(nums)) sb.append("Too easy");
			else {
				tryInsert(nums,0,nums.length-1,0,K);
				sb.append(Ans==Integer.MAX_VALUE ? "Too difficult" : Ans);
			}
			
			System.out.println(sb.toString());
		}
	}

}
