import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

	private static String formatName(String s) {
		StringBuilder sb=new StringBuilder();
		sb.append(Character.toUpperCase(s.charAt(0)));
		for (int i=1;i<s.length();i++) sb.append(Character.toLowerCase(s.charAt(i)));
		return sb.toString();
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			int P=Integer.parseInt(br.readLine());
			int [] pOffset=new int [P];
			int offset=P;
			String [][] pNames=new String [P][];
			HashMap<Integer,String> nameMap=new HashMap<>();
			for (int p=0;p<P;p++) {
				pOffset[p]=offset;
				StringTokenizer st=new StringTokenizer(br.readLine());
				int K=Integer.parseInt(st.nextToken());
				pNames[p]=new String[K];
				for (int k=0;k<K;k++) {
					pNames[p][k]=formatName(st.nextToken());
					nameMap.put(pOffset[p]+k,pNames[p][k]);
				}
				offset+=K;
			}
			int A=offset+P+2;

			int sNode=A-2;
			int tNode=A-1;
			NodeMap=new Node[A];
			for (int i=0;i<A;i++) NodeMap[i]=new Node();
			EdgeMap=new Edge[A][A];
			
			// Start node to problems
			for (int p=0;p<P;p++) addEdge(sNode,p,1);
			// Problem to respective names
			for (int p=0;p<P;p++) for (int k=0;k<pNames[p].length;k++) addEdge(p,pOffset[p]+k,1);
			// Names to alphabet nodes
			for (int p=0;p<P;p++) for (int k=0;k<pNames[p].length;k++) {
				int cIdx=(pNames[p][k].charAt(0)-'A'); // A=0, B=1, C=2...
				if (cIdx<P) addEdge(pOffset[p]+k,offset+cIdx,1); // I.e. 3 problems only need A,B,C. We exclude D onwards.
			}
			// Alphabet nodes to end
			for (int p=0;p<P;p++) addEdge(offset+p,tNode,1);

			compute(sNode,tNode);
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc);
			sb.append(":\n");
			for (int p=0;p<P;p++) {
				int cIdx=offset+p;
				for (int i=0;i<A;i++) if (EdgeMap[i][cIdx]!=null && EdgeMap[i][cIdx].flow==1) {
					sb.append(nameMap.get(i));
					sb.append('\n');
				}
			}
			System.out.print(sb);
		}
	}

}