import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		int [] F=new int [N];
		int [] C=new int [N];
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			F[n]=Integer.parseInt(st.nextToken());
			C[n]=Integer.parseInt(st.nextToken());
		}
		
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int water=Integer.parseInt(st.nextToken());
			int time=Integer.parseInt(st.nextToken());
			
			int lowestCost=Integer.MAX_VALUE;
			int max=0 | ((1<<N)-1);
			for (int i=1;i<=max;i++) {
				int totalFlowRate=0;
				int totalCost=0;
				
				for (int n=0;n<N;n++) if ((i & (1 << n)) > 0) {
					totalFlowRate+=F[n];
					totalCost+=C[n];
				}
				
				if (totalFlowRate*time>=water && totalCost<lowestCost) lowestCost=totalCost;
			}
			if (lowestCost!=Integer.MAX_VALUE) System.out.printf("Case %d: %d\n",testCase,lowestCost);
			else System.out.printf("Case %d: IMPOSSIBLE\n",testCase);
		}
	}

}