import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int [] cost=new int [36];
			for (int i=0;i<36;i+=9) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int i2=0;i2<9;i2++) {
					cost[i+i2]=Integer.parseInt(st.nextToken());
				}
			}
			StringBuilder sb=new StringBuilder();
			if (testCase>1) sb.append('\n');
			sb.append("Case ");
			sb.append(testCase);
			sb.append(":\n");
			
			int Q=Integer.parseInt(br.readLine());
			for (int q=0;q<Q;q++) {
				int N=Integer.parseInt(br.readLine());
				ArrayList<Integer> cheapestBase=new ArrayList<>();
				int cheapest=Integer.MAX_VALUE;

				for (int base=2;base<=36;base++) {
					int tempN=N;
					int currCost=0;
					while (tempN>0) {
						currCost+=cost[tempN%base];
						tempN/=base;
					}
					
					if (currCost<cheapest) {
						cheapestBase.clear();
						cheapestBase.add(base);
						cheapest=currCost;
					} else if (currCost==cheapest) {
						cheapestBase.add(base);
					}
				}
				
				sb.append("Cheapest base(s) for number ");
				sb.append(N);
				sb.append(':');
				for (int i=0;i<cheapestBase.size();i++) {
					sb.append(' ');
					sb.append(cheapestBase.get(i));
				}
				sb.append('\n');
			}
			
			System.out.print(sb.toString());
		}
	}
}
