import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine())/10;
			int P=Integer.parseInt(br.readLine());
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] calories=new int [P];
			int total=0;
			for (int p=0;p<P;p++) {
				calories[p]=Integer.parseInt(st.nextToken())/10;
				total+=calories[p];
			}
			
			boolean [] dp=new boolean [total+1];
			dp[0]=true;
			for (int p=0;p<P;p++) {
				for (int i=total-calories[p];i>=0;i--) {
					if (dp[i]) dp[i+calories[p]]=true;
				}
			}
			
			int ans=-1;
			for (int i=N;i<dp.length;i++) if (dp[i]) {
				ans=i;
				break;
			}
			
			System.out.println(ans==-1? "NO SOLUTION" : ans*10);
		}
	}

}
