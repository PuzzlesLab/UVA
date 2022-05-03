import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int Ans;

	private static void swap(int [] nums, int idx1, int idx2) {
		int temp=nums[idx1];
		nums[idx1]=nums[idx2];
		nums[idx2]=temp;
	}

	private static void find(int [] nums) {
		boolean existsSwap=false;
		for (int n=0;n<nums.length-1;n++) if (nums[n]>nums[n+1]) {
			existsSwap=true;
			swap(nums,n,n+1);
			find(nums);
			swap(nums,n+1,n);
		}
		if (!existsSwap) Ans++;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int [] nums=new int [N];
			boolean nonInc=true;
			for (int n=0;n<N;n++) {
				nums[n]=Integer.parseInt(st.nextToken());
				if (n>0) nonInc &= nums[n]>=nums[n-1];
			}

			Ans=0;
			if (!nonInc) find(nums);
			System.out.printf("There are %d swap maps for input data set %d.\n",Ans,testCase++);
		}
	}

}