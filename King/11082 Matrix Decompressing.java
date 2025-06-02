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
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int [] RS=new int [R+1];
			int [] CS=new int [C+1];
			
			st=new StringTokenizer(br.readLine());
			for (int r=1;r<=R;r++) RS[r]=Integer.parseInt(st.nextToken()); // Cumulative row sum
			st=new StringTokenizer(br.readLine());
			for (int c=1;c<=C;c++) CS[c]=Integer.parseInt(st.nextToken()); // Cumulative col sum

			int A=R+C+2+2;
			int sNode=A-2;
			int tNode=A-1;

			NodeMap=new Node[A];
			for (int i=0;i<A;i++) NodeMap[i]=new Node();
			EdgeMap=new Edge[A][A];

			/*
			 *  In feasible solution, every cell is [1,20], but solution from running algorithm can give 0 flow.
			 * 	So we offset the range to [0,19] by:
			 *  - Subtract 1 when constructing the edge capacity.
			 *  - Add 1 to edge flow during solution construction.
			 */
			// Retrieve r-th row sum from the cumulative row sum.
			// Remember to subtract the column cells (count)!
			for (int r=1;r<=R;r++) addEdge(sNode,r,RS[r]-RS[r-1]-C);
			// Max from row to column is 19.
			for (int r=1;r<=R;r++) for (int c=1;c<=C;c++) addEdge(r,R+1+c,19);
			// Retrieve c-th (col+row) sum from the cumulative (col+row) sum.
			// Remember to subtract the row cells (count)!
			for (int c=1;c<=C;c++) addEdge(R+1+c,tNode,CS[c]-CS[c-1]-R);

			compute(sNode,tNode); // Max flow + cell count (We subtracted earlier, so time to add back!) = Sum of cells in matrix
			int [][] sol=new int [R][C];
			for (int r=1;r<=R;r++) for (int c=1;c<=C;c++) sol[r-1][c-1]=EdgeMap[r][R+1+c].flow+1;
			StringBuilder sb=new StringBuilder();
			sb.append("Matrix ");
			sb.append(tc);
			sb.append('\n');
			for (int r=0;r<sol.length;r++) {
				for (int c=0;c<sol[r].length;c++) {
					sb.append(sol[r][c]);
					sb.append(' ');
				}
				if (sb.charAt(sb.length()-1)==' ') sb.setLength(sb.length()-1);
				sb.append('\n');
			}
			System.out.println(sb);
		}
	}

}