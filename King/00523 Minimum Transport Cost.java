import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static class Edge implements Comparable<Edge> {
		int to, cost;
		
		public Edge(int to, int cost) {
			this.to=to;
			this.cost=cost;
		}
		
		public int compareTo(Edge e) {
			return this.cost-e.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		boolean firstOutput=true;
		br.readLine(); // Blank space
		for (int tc=0;tc<TC;tc++) {
			// Read the lines into array list to process later. Lame input to parse.
			ArrayList<String> adjMat=new ArrayList<>();
			adjMat.add(br.readLine());
			StringTokenizer st=new StringTokenizer(adjMat.get(0));
			int N=st.countTokens();
			for (int n=1;n<N;n++) adjMat.add(br.readLine());
			
			// Construct adjacency list from the lame input.
			ArrayList<Edge> [] adjList=new ArrayList[N];
			for (int n=0;n<N;n++) adjList[n]=new ArrayList<>();
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(adjMat.get(n));
				for (int n2=0;n2<N;n2++) {
					int cost=Integer.parseInt(st.nextToken());
					if (cost>=0) adjList[n].add(new Edge(n2,cost));
				}
			}
			
			int [] cityCost=new int [N];
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) cityCost[n]=Integer.parseInt(st.nextToken());

			while (true) {
				String s=br.readLine();
				if (s==null || s.isEmpty()) break;
				
				st=new StringTokenizer(s);
				int start=Integer.parseInt(st.nextToken())-1;
				int dest=Integer.parseInt(st.nextToken())-1;
				int [] cost=new int [N];
				Arrays.fill(cost,1000000);
				int [] parent=new int [N];
				Arrays.fill(parent,-1);
				
				PriorityQueue<Edge> q=new PriorityQueue<>();
				q.offer(new Edge(start,0));
				cost[start]=0;
				while (!q.isEmpty()) {
					Edge e=q.poll();
					if (e.cost>cost[e.to]) continue;
					if (e.to==dest) break;
					for (int i=0;i<adjList[e.to].size();i++) {
						Edge ne=adjList[e.to].get(i);
						int nc=e.cost+cityCost[ne.to]+ne.cost;
						if (nc>=cost[ne.to]) continue;
						parent[ne.to]=e.to;
						cost[ne.to]=nc;
						q.offer(new Edge(ne.to,nc));
					}
				}
				
				int ans=cost[dest]-(start==dest ? 0 : cityCost[dest]);
				Stack<Integer> path=new Stack<>();
				int temp=dest;
				while (temp!=-1) {
					path.push(temp);
					temp=parent[temp];
				}

				StringBuilder sb=new StringBuilder();
				if (firstOutput) firstOutput=false;
				else sb.append('\n');
				sb.append("From ");
				sb.append(start+1);
				sb.append(" to ");
				sb.append(dest+1);
				sb.append(" :\nPath: ");
				while (!path.isEmpty()) {
					sb.append(path.pop()+1);
					sb.append("-->");
				}
				sb.setLength(sb.length()-3);
				sb.append('\n');
				sb.append("Total cost : ");
				sb.append(ans);
				System.out.println(sb.toString());
			}
		}
	}

}
