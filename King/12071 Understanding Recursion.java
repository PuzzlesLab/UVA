import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0")) {
			//For every value, count number of value less than it.
			int N=Integer.parseInt(s);
			int [] nums=new int [N];
			for (int n=0;n<N;n++) nums[n]=Integer.parseInt(br.readLine());
			Arrays.sort(nums);

			int ans=0;
			int lastValue=Integer.MIN_VALUE;
			int lastValueIndex=-1;
			for (int i=0;i<N;i++) {
				if (nums[i]!=lastValue) { //Mark the index of first encountered value
					ans+=i;
					lastValue=nums[i];
					lastValueIndex=i;
				} else ans+=lastValueIndex; //Same value, only add 0 to index of first encountered value
			}
			
			System.out.printf("Case %d: %d\n", testCase++, ans);
		}
	}
}