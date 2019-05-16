import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static class Edge {
		int src, dest, weight;
		public Edge(int src, int dest, int weight) { this.src=src; this.dest=dest; this.weight=weight; }
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> edges=new ArrayList<>();
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			int [] dist=new int [N];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[0]=0;
			for (int n=0;n<N-1;n++) for (Edge e : edges) if (dist[e.src]!=Integer.MAX_VALUE)
				dist[e.dest] = Math.min(dist[e.dest], dist[e.src]+e.weight);
			
			boolean flag=false;
			for (Edge e : edges) if (dist[e.src]!=Integer.MAX_VALUE && dist[e.dest] > dist[e.src]+e.weight) {
				flag=true;
				break;
			}
			
			System.out.println(flag ? "possible" : "not possible");
		}
	}

}