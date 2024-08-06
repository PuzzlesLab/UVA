import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		// https://en.wikipedia.org/wiki/Erd%C5%91s%E2%80%93Gallai_theorem
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			Integer [] nums=new Integer [N];
			int sum=0;
			for (int n=0;n<N;n++) {
				nums[n]=Integer.parseInt(st.nextToken());
				sum=(sum+nums[n])&1;
			}
			Arrays.sort(nums, Collections.reverseOrder());
			int [] cSum=new int [N];
			for (int n=0;n<N;n++) cSum[n]+=(n>0?cSum[n-1]:0)+nums[n];

			if (sum==1 || nums[0]>=N) {
				System.out.println(0);
				continue;
			}

			boolean flag=true;
			for (int n=0;n<N && flag;n++) {
				int right=n*(n-1);
				for (int k=n;k<N;k++) {
					right=right+Math.min(nums[k],n);
				}
				flag &= (n>0?cSum[n-1]:0)<=right;
			}
			System.out.println(flag?1:0);
		}
 	}

}