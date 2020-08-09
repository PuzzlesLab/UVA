import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int [] nums=new int [16];
			int numsPos=0;
			for (char c : br.readLine().toCharArray()) if (c!=' ') nums[numsPos++]=c-'0';
			
			int sum=0;
			for (int i=0;i<nums.length;i+=2) {
				int doubled=nums[i]<<1;
				while (doubled>0) {
					sum+=doubled%10;
					doubled/=10;
				}
				
			}
			for (int i=1;i<nums.length;i+=2) sum+=nums[i];
			System.out.println((sum%10==0) ? "Valid" : "Invalid");
		}
		
	}

}