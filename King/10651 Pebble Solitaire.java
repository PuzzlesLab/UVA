import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static final int MAX_BITS=12;
	private static int [] Dp;

	private static int countBit(int state) {
		int count=0;
		for (int i=0;i<MAX_BITS;i++) if ((state&(1<<i))!=0) count++;
		return count;
	}

	public static int compute(int state) {
		if (Dp[state]==-1) {
			int min=countBit(state); // Don't need to apply dp on countBit, already covered by this method.
			for (int i=1;i<MAX_BITS-1;i++) {
				boolean leftSet=(state&(1<<(i-1)))!=0;
				boolean middleSet=(state&(1<<(i)))!=0;
				boolean rightSet=(state&(1<<(i+1)))!=0;
				if (leftSet && middleSet && !rightSet) {
					int temp=state;
					temp=temp&~(1<<(i-1)); // Unset left
					temp=temp&~(1<<i); // Unset curr
					temp=temp|(1<<(i+1)); // Set right
					min=Math.min(min,compute(temp));
				} else if (!leftSet && middleSet && rightSet) {
					int temp=state;
					temp=temp&~(1<<(i+1)); // Unset right
					temp=temp&~(1<<i); // Unset curr
					temp=temp|(1<<(i-1)); // Set left
					min=Math.min(min,compute(temp));
				}
			}
			Dp[state]=min;
		}
		
		return Dp[state];
	}

	public static void main(String[] args) throws Exception {
		Dp=new int [(1<<MAX_BITS)+1];
		Arrays.fill(Dp,-1);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			String s=br.readLine();
			int state=0;
			for (int i=0;i<s.length();i++) if (s.charAt(i)=='o') state|=(1<<i);
			
			// Don't reset DP here, can be shared among all test cases. :)
			System.out.println(compute(state));
		}
	}

}