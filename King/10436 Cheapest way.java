import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		int MAX=1000000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			br.readLine();

			int N=Integer.parseInt(br.readLine());
			String [] stations=new String [N];
			int [] stCost=new int [N];
			HashMap<String,Integer> nameToIdx=new HashMap<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				stations[n]=st.nextToken();
				stCost[n]=Integer.parseInt(st.nextToken());
				nameToIdx.put(stations[n],n);
			}
			
			int P=Integer.parseInt(br.readLine());
			int [][] pCost=new int [N][N];
			for (int n=0;n<N;n++) {
				Arrays.fill(pCost[n],MAX);
				pCost[n][n]=stCost[n];
			}
			for (int p=0;p<P;p++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int st1=nameToIdx.get(st.nextToken());
				int st2=nameToIdx.get(st.nextToken());
				int cost=Integer.parseInt(st.nextToken())*2;
				pCost[st1][st2]=cost+stCost[st2];
				pCost[st2][st1]=cost+stCost[st1];
			}

			int [][] parent=new int [N][N];
			for (int n=0;n<N;n++) Arrays.fill(parent[n],n);
			for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++) {
				int c=pCost[i][k]+pCost[k][j];
				if (c<pCost[i][j]) {
					pCost[i][j]=Math.min(pCost[i][j],c);
					parent[i][j]=parent[k][j];
				}
			}

			StringBuilder sb=new StringBuilder();
			if (tc>1) sb.append('\n');
			sb.append("Map #");
			sb.append(tc);
			sb.append('\n');
			int Q=Integer.parseInt(br.readLine());
			for (int q=1;q<=Q;q++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int st1=nameToIdx.get(st.nextToken());
				int st2=nameToIdx.get(st.nextToken());
				double ans=pCost[st1][st2];
				if (st1!=st2) ans+=stCost[st1];
				ans=ans*1.1/Integer.parseInt(st.nextToken());

				sb.append("Query #");
				sb.append(q);
				sb.append('\n');
				
				Stack<String> stk=new Stack<>();
				int temp=st2;
				while (temp!=st1) {
					stk.push(stations[temp]);
					temp=parent[st1][temp];
				}
				stk.push(stations[st1]);
				while (!stk.isEmpty()) {
					sb.append(stk.pop());
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				sb.append('\n');
				
				sb.append("Each passenger has to pay : ");
				sb.append(String.format("%.2f",ans));
				sb.append(" taka\n");
			}
			System.out.print(sb.toString());
		}
	}

}