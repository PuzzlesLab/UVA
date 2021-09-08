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
			int K=Integer.parseInt(st.nextToken());
			int currPos=0;
			for (int n=2;n<=N;n++) currPos=(currPos+K)%n;
			System.out.printf("Case %d: %d\n",testCase,currPos+1);
		}
	}

}
