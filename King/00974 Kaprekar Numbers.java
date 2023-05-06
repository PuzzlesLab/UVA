import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class zz {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		
		int [] dp=new int [40001];
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int n1=Integer.parseInt(st.nextToken());
			int n2=Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> sol=new ArrayList<>();
			for (int n=n1;n<=n2;n++) {
				if (dp[n]==0) {
					int sq=n*n;
					if (sq<10) {
						dp[n]=2;
						continue;
					}

					int multi=10;
					while (multi<sq) {
						int p1=sq/multi;
						int p2=sq%multi;
						multi*=10;
						
						if (p2==0) continue;
						if (p1+p2==n) {
							dp[n]=1;
							break;
						}
					}
				}
				if (dp[n]==1) sol.add(n);
			}

			StringBuilder sb=new StringBuilder();
			sb.append("case #");
			sb.append(testCase);
			sb.append('\n');
			if (sol.isEmpty()) sb.append("no kaprekar numbers\n");
			else {
				for (int i=0;i<sol.size();i++) {
					sb.append(sol.get(i));
					sb.append('\n');
				}
			}
			if (testCase>1) System.out.println();
			System.out.print(sb.toString());
		}
	}

}
