import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

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
		final int APP_MAX=26;
		final int PC_MAX=10;
		final int S_NODE=APP_MAX+PC_MAX;
		final int T_NODE=S_NODE+1;
		final int NODE_MAX=T_NODE+1;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean toInit=true;
		int appTotal=0;
		while (true) {
			String s=br.readLine();
			if (s==null || s.trim().isEmpty()) {
				int ans=compute(S_NODE,T_NODE);
				if (ans==appTotal) {
					StringBuilder sb=new StringBuilder();
					for (int i=APP_MAX;i<S_NODE;i++) {
						Node pc=NodeMap[i];
						boolean found=false;
						for (int i2=0;i2<pc.edges.size();i2++) {
							Edge e=pc.edges.get(i2);
							if (e.flow==-1) {
								sb.append((char)(e.nId+'A'));
								found=true;
								break;
							}
						}
						if (!found) sb.append('_');
					}
					System.out.println(sb.toString());
				} else System.out.println("!");

				toInit=true;
				if (s==null) break;
			} else {
				if (toInit) {
					toInit=false;
					NodeMap=new Node[NODE_MAX];
					EdgeMap=new Edge[NODE_MAX][NODE_MAX];
					// PC to end node.
					for (int i=0;i<NODE_MAX;i++) NodeMap[i]=new Node();
					for (int i=APP_MAX;i<S_NODE;i++) addEdge(i,T_NODE,1);
					appTotal=0;
				}
				// start node to app
				int appIdx=s.charAt(0)-'A';
				int appInstance=s.charAt(1)-'0';
				appTotal+=appInstance;
				addEdge(S_NODE,appIdx,appInstance);
				// App to PC
				for (int i=3;i<s.length();i++) {
					char c=s.charAt(i);
					if (Character.isDigit(c)) addEdge(appIdx,c-'0'+APP_MAX,1);
				}
			}
		}
	}

}