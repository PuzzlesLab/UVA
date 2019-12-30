import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Edge implements Comparable<Edge> {
		int src, dest, cost;
		public Edge(int s, int d, int c) {
			this.src=s;
			this.dest=d;
			this.cost=c;
		}
		public int compareTo(Edge e) {
			return this.cost-e.cost;
		}
	}
	
	public static int NOEDGE=100001;
	
	private static int[] djikstra(int from, ArrayList<Edge> [] adjList) {
		PriorityQueue<Edge> q=new PriorityQueue<>();
		q.offer(new Edge(from,from,0));
		int [] maxDist=new int [adjList.length];
		Arrays.fill(maxDist, NOEDGE);
		maxDist[from]=0;
		while (!q.isEmpty()) {
			Edge e=q.poll();
			for (Edge next : adjList[e.dest]) if (e.cost+next.cost<maxDist[next.dest]) {
				maxDist[next.dest]=e.cost+next.cost;
				q.offer(new Edge(e.src,next.dest,maxDist[next.dest]));
			}
		}
		return maxDist;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());

		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken())-1;
			int t=Integer.parseInt(st.nextToken())-1;
			int p=Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> [] adjList=new ArrayList [N];
			ArrayList<Edge> [] adjListReverse=new ArrayList [N];
			for (int i=0;i<N;i++) {
				adjList[i]=new ArrayList<>();
				adjListReverse[i]=new ArrayList<>();
			}

			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int src=Integer.parseInt(st.nextToken())-1;
				int dest=Integer.parseInt(st.nextToken())-1;
				int cost=Integer.parseInt(st.nextToken());
				adjList[src].add(new Edge(src,dest,cost));
				adjListReverse[dest].add(new Edge(dest,src,cost));
			}
			
			int [] srcToX=djikstra(s,adjList);
			int [] xToDest=djikstra(t,adjListReverse);

			int maxToll=-1;
			for (int i=0;i<N;i++) for (Edge e : adjList[i]) {
				int cost=srcToX[e.src]+e.cost+xToDest[e.dest];
				if (cost<=p) maxToll=Math.max(maxToll, e.cost);
			}
			
			System.out.println(maxToll);
		}
	}

}