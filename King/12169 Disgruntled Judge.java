import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	/*
	 * Simplified explanation :
	 * 	Given T, there are numbers Xi -> X1 to X2T with relation Xi=(a*Xi-1 + b)%10001
	 * 	For i=1,3,5,....,2T-1, Xi are given in the following lines
	 * 	Find X2, X4, X6... X2T, where a, b are not known.
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		int [] nums=new int [2*T+1];
		for (int i=1;i<=2*T-1;i+=2) nums[i]=Integer.parseInt(br.readLine());

		boolean found=false;
		for (int a=0;a<=10000 && !found;a++) {
			for (int b=0;b<=10000 && !found;b++) {
				boolean currFound=true;
				for (int i=2;i<nums.length && currFound;i++) {
					int currValue=(a*nums[i-1]+b)%10001;
					if (i%2==0) nums[i]=currValue;
					else currFound&=currValue==nums[i];
				}
				
				if (currFound) found=true;
			}
		}
		
		StringBuilder sb=new StringBuilder();
		for (int i=2;i<nums.length;i+=2) {
			sb.append(nums[i]);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}

}
