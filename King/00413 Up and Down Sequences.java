import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int [] nums=new int [st.countTokens()-1];
			for (int n=0;n<nums.length;n++) nums[n]=Integer.parseInt(st.nextToken());

			double upEvent=0;
			int upLength=0;
			double downEvent=0;
			int downLength=0;

			int coveredMin=0; // Prevent recalculating previously counted lengths.
			for (int n=1;n<nums.length;n++) {
				if (nums[n]>nums[n-1]) { // Up
					int min=n;
					while (min>coveredMin) {
						if (nums[min-1]>nums[min]) break;
						min--;
					}

					int max=n;
					while (max+1<nums.length) {
						if (nums[max]>nums[max+1]) break;
						max++;
					}
					
					upEvent++;
					upLength+=max-min;
					coveredMin=max;
					n=max;
				} else if (nums[n]<nums[n-1]) {
					int min=n;
					while (min>coveredMin) {
						if (nums[min-1]<nums[min]) break;
						min--;
					}

					int max=n;
					while (max+1<nums.length) {
						if (nums[max]<nums[max+1]) break;
						max++;
					}
					
					downEvent++;
					downLength+=max-min;
					coveredMin=max;
					n=max;
				}
			}
			
			System.out.printf("Nr values = %d:  %.6f %.6f\n",nums.length,upEvent>0?upLength/upEvent:0,downEvent>0?downLength/downEvent:0);
		}
	}

}
