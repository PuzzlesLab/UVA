import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int P=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());
			
			int [] eggs=new int [N];
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) eggs[i]=Integer.parseInt(st.nextToken());
			//eggs already sorted, skip sorting.
			int weight=0;
			int ans=0;
			for (int i=0;i<N && i<P && weight+eggs[i]<=Q;i++) {
				weight+=eggs[i];
				ans++;
			}
			
			System.out.printf("Case %d: %d\n", testCase, ans);
		}
	}

}