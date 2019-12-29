import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	private static class Edge {
		int src, dest, ppa;
		
		public Edge(int s, int d, int p) {
			this.src=s;
			this.dest=d;
			this.ppa=p;
		}
		
	}
	
	
	private static boolean [] visited;
	private static int visitedCount=0;
	public static void dfs(ArrayList<Edge> [] adjList, int curr) {
		visitedCount++;
		for (int i=0;i<adjList[curr].size();i++) {
			Edge e=adjList[curr].get(i);
			if (!visited[e.dest]) {
				visited[e.dest]=true;
				dfs(adjList,e.dest);
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> [] adjList=new ArrayList [N];
			for (int n=0;n<N;n++) adjList[n]=new ArrayList<>();
			
			int maxPPA=Integer.MIN_VALUE;
			ArrayList<Edge> edges=new ArrayList<>();
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int c1=Integer.parseInt(st.nextToken())-1;
				int c2=Integer.parseInt(st.nextToken())-1;
				int ppa=Integer.parseInt(st.nextToken());
				maxPPA=Math.max(maxPPA,ppa);
				edges.add(new Edge(c1,c2,ppa));
			}
			
			for (int i=0;i<edges.size();i++) {
				Edge e=edges.get(i);
				if (e.ppa==maxPPA) {
					adjList[e.src].add(new Edge(e.src, e.dest, e.ppa));
					adjList[e.dest].add(new Edge(e.dest, e.src, e.ppa));
				}
			}

			visited=new boolean [N];
			int ans=Integer.MIN_VALUE;
			for (int n=0;n<N;n++) if (!visited[n]) {
				visitedCount=0;
				visited[n]=true;
				dfs(adjList,n); //Using loop to dfs will cause TLE, what a shame.
				ans=Math.max(visitedCount, ans);
			}

			System.out.println(ans);
		}
	}

}