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

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		final int INF=Integer.MAX_VALUE>>1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int K=Integer.parseInt(st.nextToken());
			int P=Integer.parseInt(st.nextToken());
			int sNode=K+P;
			int tNode=sNode+1;
			int N=tNode+1;
			int totalPReq=0;
			
			NodeMap=new Node[N];
			for (int n=0;n<NodeMap.length;n++) NodeMap[n]=new Node();
			EdgeMap=new Edge[N][N];
			// Create edges from virtual start to problems.
			for (int p=K;p<sNode;p++) {
				EdgeMap[sNode][p]=new Edge(p,1,0);
				NodeMap[sNode].edges.add(EdgeMap[sNode][p]);
				EdgeMap[p][sNode]=new Edge(sNode,0,0);
				NodeMap[p].edges.add(EdgeMap[p][sNode]);
			}
			// Created edges from categories to virtual end.
			for (int k=0;k<K;k++) {
				EdgeMap[k][tNode]=new Edge(tNode,0,0);
				NodeMap[k].edges.add(EdgeMap[k][tNode]);
				EdgeMap[tNode][k]=new Edge(k,0,0);
				NodeMap[tNode].edges.add(EdgeMap[tNode][k]);
			}

			st=new StringTokenizer(br.readLine());
			for (int k=0;k<K;k++) {
				EdgeMap[k][tNode].cap=Integer.parseInt(st.nextToken());
				totalPReq+=EdgeMap[k][tNode].cap;
			}
			for (int p=0;p<P;p++) {
				st=new StringTokenizer(br.readLine());
				int count=Integer.parseInt(st.nextToken());
				int pIdx=K+p;
				for (int i=0;i<count;i++) {
					int kIdx=Integer.parseInt(st.nextToken())-1; // Convert to 0-based storage.
					EdgeMap[pIdx][kIdx]=new Edge(kIdx,1,0);
					NodeMap[pIdx].edges.add(EdgeMap[pIdx][kIdx]);
					EdgeMap[kIdx][pIdx]=new Edge(pIdx,0,0);
					NodeMap[kIdx].edges.add(EdgeMap[kIdx][pIdx]);
				}
			}

			int ans=0;
			while (hasPath(sNode,tNode)) {
				resetEIdx();
				int flow=0;
				while ((flow=pushFlow(sNode,tNode,INF))>0) ans+=flow;
			}

			if (ans<totalPReq) {
				System.out.println(0);
				continue;
			}

			StringBuilder sb=new StringBuilder();
			sb.append("1\n");
			for (int k=0;k<K;k++) {
				for (int i=0;i<NodeMap[k].edges.size();i++) {
					Edge e=NodeMap[k].edges.get(i);
					if (e.flow==-1) {
						sb.append(e.nId-K+1);
						sb.append(' ');
					}
				}
				if (sb.charAt(sb.length()-1)==' ') sb.setLength(sb.length()-1);
				sb.append('\n');
			}

			System.out.print(sb.toString());
		}
	}

}
