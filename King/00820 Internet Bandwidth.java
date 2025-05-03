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
		for (int n=1;n<NodeMap.length;n++) NodeMap[n].lastEIdx=0;
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
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			NodeMap=new Node[N+1];
			EdgeMap=new Edge[N+1][N+1];
			for (int n=1;n<=N;n++) NodeMap[n]=new Node();
			StringTokenizer st=new StringTokenizer(br.readLine());
			int sId=Integer.parseInt(st.nextToken());
			int tId=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());

			for (int c=0;c<C;c++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());
				if (n1==n2) continue;
				int w=Integer.parseInt(st.nextToken());
				EdgeMap[n1][n2]=new Edge(n2,w,0);
				NodeMap[n1].edges.add(EdgeMap[n1][n2]);
				EdgeMap[n2][n1]=new Edge(n1,w,0);
				NodeMap[n2].edges.add(EdgeMap[n2][n1]);
			}

			int ans=0;
			while (hasPath(sId,tId)) {
				resetEIdx();
				int flow=0;
				while ((flow=pushFlow(sId,tId,Integer.MAX_VALUE))>0) ans+=flow;
			}

			System.out.printf("Network %d\nThe bandwidth is %d.\n\n", tc++, ans);
		}
	}

}