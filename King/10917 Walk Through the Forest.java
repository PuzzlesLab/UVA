import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Edge implements Comparable<Edge> {
		int n,d;
		
		public Edge(int n, int d) {
			this.n=n;
			this.d=d;
		}

		public int compareTo(Edge e) {
			return this.d-e.d;
		}
	}

	private static int N;
	private static final int OFFICE=0;
	private static final int HOME=1;
	private static final int MAX_DIST=Integer.MAX_VALUE>>1;

	private static int[] shortestPath(ArrayList<Edge> [] adjList) {
		int [] dist=new int [N];
		Arrays.fill(dist,MAX_DIST);
		PriorityQueue<Edge> q=new PriorityQueue<>();
		dist[1]=0;
		q.offer(new Edge(HOME,0));
		while (!q.isEmpty()) {
			Edge c=q.poll();
			for (int i=0;i<adjList[c.n].size();i++) {
				Edge e=adjList[c.n].get(i);
				int nd=c.d+e.d;
				if (nd<dist[e.n]) {
					dist[e.n]=nd;
					q.offer(new Edge(e.n,nd));
				}
			}
		}
		return dist;
	}

	private static ArrayList<Edge> [] minGraph(ArrayList<Edge> [] adjList, int [] dist) {
		ArrayList<Edge> [] adjList2=new ArrayList [N];
		for (int n=0;n<N;n++) adjList2[n]=new ArrayList<>();
		for (int n=0;n<N;n++) {
			for (int i=0;i<adjList[n].size();i++) {
				Edge e=adjList[n].get(i);
				if (dist[n]>dist[e.n]) adjList2[n].add(e);
			}
		}
		return adjList2;
	}
	
	private static int [] topo(ArrayList<Edge> [] adjList) {
		int [] inDeg=new int [N];
		for (int n=0;n<N;n++) {
			for (int i=0;i<adjList[n].size();i++) inDeg[adjList[n].get(i).n]++;
		}
		
		int [] result=new int [N];
		int resultI=0;
		LinkedList<Integer> q=new LinkedList<>();
		for (int n=0;n<N;n++) if (inDeg[n]==0) q.addLast(n);
		while (!q.isEmpty()) {
			int curr=q.removeFirst();
			result[resultI++]=curr;
			for (int i=0;i<adjList[curr].size();i++) {
				int next=adjList[curr].get(i).n;
				inDeg[next]--;
				if (inDeg[next]==0) q.addLast(next);
			}
		}
	
		int [] reverse=new int [N];
		for (int i=0;i<resultI;i++) reverse[i]=result[resultI-1-i];
		return result;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> [] adjList=new ArrayList [N];
			for (int n=0;n<N;n++) adjList[n]=new ArrayList<>();
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				int d=Integer.parseInt(st.nextToken());
				adjList[n1].add(new Edge(n2,d));
				adjList[n2].add(new Edge(n1,d));
			}

			int [] dist=shortestPath(adjList);
			ArrayList<Edge> [] adjList2=minGraph(adjList,dist);
			int [] points=topo(adjList2);

			long [] dp=new long [N];
			dp[OFFICE]=1;
			for (int i=0;i<points.length;i++) {
				int n=points[i];
				for (int i2=0;i2<adjList2[n].size();i2++) dp[adjList2[n].get(i2).n]+=dp[n];
			}
			System.out.println(dp[HOME]);
		}
	}

}