import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine().trim());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int n=Integer.parseInt(br.readLine().trim());
			int factor=9;
			
			StringBuilder sb=new StringBuilder();
			if (n<10) sb.append(n);
			else {
				ArrayList<Integer> nums=new ArrayList<>();
				while (n>1 && factor>1) {
					if (n%factor==0) {
						n/=factor;
						nums.add(factor);
					} else factor--;
				}
				if (n>1) sb.append(-1);
				else {
					Collections.sort(nums);
					for (int i : nums) sb.append(i);
				}
			}

			System.out.println(sb.toString());
		}
	}

}