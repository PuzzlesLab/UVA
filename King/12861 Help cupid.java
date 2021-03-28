import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			int [] nums=new int [N];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) nums[i]=Integer.parseInt(st.nextToken());
			Arrays.sort(nums);

			int ans1=0;
			for (int i=0;i<N;i+=2) {
				int diff=Math.abs(nums[i]-nums[i+1]);
				ans1+=Math.min(diff,24-diff);
			}
			int ans2=0;
			for (int i=1;i<N;i+=2) {
				int diff=Math.abs(nums[i]-nums[(i+1)%N]);
				ans2+=Math.min(diff,24-diff);
			}
			
			System.out.println(Math.min(ans1, ans2));
		}
	}
}