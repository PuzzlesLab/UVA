import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			ArrayList<Integer> nums=new ArrayList<>();
			while (st.hasMoreTokens()) nums.add(Integer.parseInt(st.nextToken()));

			int maxSum=0;
			int currSum=0;
			for (int num: nums) {
				currSum+=num;
				maxSum=Math.max(maxSum, currSum);
				if (currSum<0) currSum=0;
			}
			
			System.out.println(maxSum);
		}
	}
}
