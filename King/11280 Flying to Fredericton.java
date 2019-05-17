import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class uva {
	
	private static class Edge {
		int dest, weight;
		public Edge (int dest, int weight) { this.dest=dest; this.weight=weight; }
	}
	
	private static class BFEdge implements Comparable<BFEdge> {
		int dest, weight, stops;
		public BFEdge (int dest, int weight, int stops) { this.dest=dest; this.weight=weight; this.stops=stops; }
		public int compareTo(BFEdge e) { return this.weight-e.weight; }
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			br.readLine(); //empty.
			
			int N=Integer.parseInt(br.readLine());
			HashMap<String,Integer> map=new HashMap<>();
			
			for (int n=0;n<N;n++) map.put(br.readLine(), n);
			ArrayList<Edge> [] edges=new ArrayList [N];
			for (int n=0;n<N;n++) edges[n]=new ArrayList<>();
			
			int M=Integer.parseInt(br.readLine());
			for (int m=0;m<M;m++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				edges[map.get(st.nextToken())].add(new Edge(map.get(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Scenario #");
			sb.append(testCase);
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			int Q=Integer.parseInt(st.nextToken());
			int src=map.get("Calgary");
			int dest=map.get("Fredericton");
			for (int q=0;q<Q;q++) {
				int V=Integer.parseInt(st.nextToken());
				int [] dist=new int [N];
				Arrays.fill(dist,Integer.MAX_VALUE);
				PriorityQueue<BFEdge> queue=new PriorityQueue<>();
				queue.offer(new BFEdge(src,0,0));
				while (!queue.isEmpty()) {
					BFEdge e=queue.poll();
					dist[e.dest]=Math.min(dist[e.dest], e.weight);
					if (e.dest==dest) break;
					else if (e.stops<=V) {
						for (Edge nextEdge : edges[e.dest]) 
							queue.offer(new BFEdge(nextEdge.dest, nextEdge.weight+e.weight, e.stops+1));
					}
				}
				
				sb.append('\n');
				if (dist[dest]==Integer.MAX_VALUE) sb.append("No satisfactory flights");
				else {
					sb.append("Total cost of flight(s) is $");
					sb.append(dist[dest]);
				}
			}
			
			if (testCase>1) System.out.println();
			System.out.println(sb.toString());
		}
	}

}