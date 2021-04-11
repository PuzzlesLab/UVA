import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int W=Integer.parseInt(st.nextToken());
			int [] elephants=new int [M];
			
			st=new StringTokenizer(br.readLine());
			for (int m=0;m<M;m++) elephants[m]=Integer.parseInt(st.nextToken());
			Arrays.sort(elephants);
			
			int currWeight=0;
			int ans=0;
			for (int m=0;m<M;m++) {
				int nextWeight=currWeight+elephants[m];
				if (nextWeight<=W) {
					currWeight=nextWeight;
					ans++;
				} else break;
			}
			
			System.out.println(ans);
		}
	}
}