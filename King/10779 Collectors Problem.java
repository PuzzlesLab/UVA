import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	private static class Node {
		ArrayList<Edge> edges;
		int lastEIdx;

		public Node() {
			this.edges=new ArrayList<>();
		}
	}

	private static class Edge {
		int nId, cap, flow;

		public Edge(int n, int c, int f) {
			this.nId=n;
			this.cap=c;
			this.flow=f;
		}
	}
	
	private static Node [] NodeMap;
	private static Edge [][] EdgeMap;
	private static int [] Depth;

	private static boolean hasPath(int s, int t) {
		Depth=new int [NodeMap.length];
		Arrays.fill(Depth,-1);
		LinkedList<Integer> q=new LinkedList<>();
		q.addLast(s);
		Depth[s]=0;
		while (!q.isEmpty()) {
			int curr=q.removeFirst();
			if (curr==t) return true;

			Node node=NodeMap[curr];
			for (int i=0;i<node.edges.size();i++) {
				Edge e=node.edges.get(i);
				if (Depth[e.nId]!=-1) continue;
				if (e.flow>=e.cap) continue;
				Depth[e.nId]=Depth[curr]+1;
				q.addLast(e.nId);
			}
		}
		return false;
	}

	private static void resetEIdx() {
		for (int n=0;n<NodeMap.length;n++) NodeMap[n].lastEIdx=0;
	}

	private static int pushFlow(int s, int t, int minF) {
		if (s==t || minF==0) return minF;
		for (int i=NodeMap[s].lastEIdx;i<NodeMap[s].edges.size();i++) {
			NodeMap[s].lastEIdx=i;
			Edge e=NodeMap[s].edges.get(i);
			if (Depth[e.nId]!=Depth[s]+1) continue;

			int currF=pushFlow(e.nId,t,Math.min(minF,e.cap-e.flow));
			if (currF>0) {
				e.flow+=currF;
				EdgeMap[e.nId][s].flow-=currF;
				return currF;
			}
		}
		return 0;
	}

	private static void addEdge(int s, int t, int w) {
		EdgeMap[s][t]=new Edge(t,w,0);
		NodeMap[s].edges.add(EdgeMap[s][t]);
		EdgeMap[t][s]=new Edge(s,0,0);
		NodeMap[t].edges.add(EdgeMap[t][s]);
	}

	private static int compute(int s, int t) {
		final int INF=Integer.MAX_VALUE/2;
		int ans=0;
		while (hasPath(s,t)) {
			resetEIdx();
			int flow=0;
			while ((flow=pushFlow(s,t,INF))>0) ans+=flow;
		}
		return ans;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int A=N+M+1;

			NodeMap=new Node[A];
			for (int i=0;i<A;i++) NodeMap[i]=new Node();
			EdgeMap=new Edge[A][A];
			
			int sNode=0; // Bob node
			int tNode=A-1; // Virtual end node
			// Link people to card
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				int [] stickers=new int[M];
				int K=Integer.parseInt(st.nextToken());
				for (int k=0;k<K;k++) stickers[Integer.parseInt(st.nextToken())-1]++;
				for (int m=0;m<M;m++) {
					if (n==0) addEdge(n,N+m,stickers[m]); // Bob only flow out. (and to end node)
					else {
						if (stickers[m]==0) addEdge(N+m,n,1);
						else if (stickers[m]>1) addEdge(n,N+m,stickers[m]-1);
					}
				}
			}
			// Link Card to end
			for (int m=0;m<M;m++) addEdge(N+m,tNode,1);

			int ans=compute(sNode,tNode);
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc);
			sb.append(": ");
			sb.append(ans);
			System.out.println(sb);
		}
	}

}
