import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			int [] heights=new int [N];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) heights[n]=Integer.parseInt(st.nextToken());
			
			int nextFind=1;
			int seqLength=0;
			for (int n=0;n<N;n++) {
				if (heights[n]==nextFind) {
					nextFind++;
					seqLength++;
				}
			}
			System.out.printf("Case %d: %d\n", testCase, N-seqLength);
		}
	}

}
