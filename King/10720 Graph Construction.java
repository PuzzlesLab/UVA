import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			Integer [] nums=new Integer [N];
			int sum=0;
			for (int n=0;n<N;n++) {
				nums[n]=Integer.parseInt(st.nextToken());
				sum=(sum+nums[n])&1;
			}
			Arrays.sort(nums,Collections.reverseOrder());

			int [] cSum=new int [N];
			cSum[0]=nums[0];
			for (int n=1;n<N;n++) cSum[n]=cSum[n-1]+nums[n];

			// https://en.wikipedia.org/wiki/Erd%C5%91s%E2%80%93Gallai_theorem
			boolean flag=sum==0;
			for (int n=0;n<N && flag;n++) flag&=nums[n]>=0 && nums[n]<=N;
			for (int k=0;k<N && flag;k++) {
				int right=k*(k-1);
				for (int k2=k;k2<N;k2++) right+=Math.min(k,nums[k2]);
				flag &= ((k-1>=0)?cSum[k-1]:0)<=right;
			}
			System.out.println(flag ? "Possible" : "Not possible");
		}
 	}

}