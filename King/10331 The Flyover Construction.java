import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Edge {
		int idx, n1, n2, cost, count;
		
		public Edge(int idx, int n1, int n2, int cost) {
			this.idx=idx;
			this.n1=n1;
			this.n2=n2;
			this.cost=cost;
		}
	}

	public static void main(String[] args) throws Exception {
		final int MAX=1000000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int V=Integer.parseInt(st.nextToken());
			int E=Integer.parseInt(st.nextToken());
			
			int [][] cost=new int [V][V];
			Edge [] edges=new Edge[E];
			for (int v=0;v<V;v++) {
				Arrays.fill(cost[v],MAX);
				cost[v][v]=0;
			}
			for (int e=0;e<E;e++) {
				st=new StringTokenizer(br.readLine());
				int from=Integer.parseInt(st.nextToken())-1;
				int to=Integer.parseInt(st.nextToken())-1;
				cost[from][to]=cost[to][from]=Math.min(cost[from][to],Integer.parseInt(st.nextToken()));
				edges[e]=new Edge(e+1,from,to,cost[from][to]);
			}
			for (int k=0;k<V;k++) for (int i=0;i<V;i++) for (int j=0;j<V;j++) cost[i][j]=Math.min(cost[i][j],cost[i][k]+cost[k][j]);

			int maxUsed=0;
			for (int i=0;i<V;i++) for (int j=i+1;j<V;j++) for (int e=0;e<E;e++) {
				Edge edge=edges[e];
				int testC1=cost[i][edge.n1]+edge.cost+cost[edge.n2][j];
				int testC2=cost[i][edge.n2]+edge.cost+cost[edge.n1][j];
				if (testC1==cost[i][j] || testC2==cost[i][j]) {
					edge.count++;
					maxUsed=Math.max(maxUsed,edge.count);
				}
			}

			StringBuilder sb=new StringBuilder();
			for (int e=0;e<E;e++) {
				Edge edge=edges[e];
				if (edge.count!=maxUsed) continue;
				sb.append(edge.idx);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}