import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int [] nums=new int[N];
			for (int n=0;n<N;n++) nums[n]=Integer.parseInt(st.nextToken());

			int ans=0;
			for (int i=0;i<N-1;i++) if ((ans%2==0 && nums[i]>nums[i+1]) || (ans%2==1 && nums[i]<nums[i+1])) ans++;
			System.out.println(ans+1);
		}
	}

}
