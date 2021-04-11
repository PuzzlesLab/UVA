import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());

			long [] X=new long [N];
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) X[n]=Long.parseLong(st.nextToken());
			
			long [] Y=new long [N];
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) Y[n]=Long.parseLong(st.nextToken());
			
			long [] nett=new long [N];
			for (int n=0;n<N;n++) nett[n]=Y[n]-X[n];
			
			Arrays.sort(nett);
			
			int startIdx=0;
			while (K>0 && nett[startIdx]<=0) {
				K--;
				startIdx++;
			}
			
			long sum=0;
			for (int n=startIdx;n<N;n++) sum+=nett[n];
			
			if (sum<=0) System.out.printf("Case %d: No Profit\n", testCase);
			else System.out.printf("Case %d: %d\n", testCase, sum);
		}
	}
}