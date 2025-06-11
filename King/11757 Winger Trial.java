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
		Edge reverse;

		public Edge(int n, int c, int f) {
			this.nId=n;
			this.cap=c;
			this.flow=f;
		}
	}
	
	private static Node [] NodeMap;
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
				e.reverse.flow-=currF;
				return currF;
			}
		}
		return 0;
	}

	private static void addEdge(int s, int t, int w) {
		Edge e1=new Edge(t,w,0);
		Edge e2=new Edge(s,0,0);
		e1.reverse=e2;
		e2.reverse=e1;
		
		NodeMap[s].edges.add(e1);
		NodeMap[t].edges.add(e2);
	}

	private static int compute(int s, int t) {
		final int INF=Integer.MAX_VALUE/4;
		int ans=0;
		while (hasPath(s,t)) {
			resetEIdx();
			long flow=0;
			while ((flow=pushFlow(s,t,INF))>0) ans+=flow;
		}
		return ans;
	}

	private static int N;
	
	private static int getInNode(int n) {
		return n;
	}
	
	private static int getOutNode(int n) {
		return n+N;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int L=Integer.parseInt(st.nextToken());
			int W=Integer.parseInt(st.nextToken());
			N=Integer.parseInt(st.nextToken());
			double d=Integer.parseInt(st.nextToken());
			
			int [] x=new int [N];
			int [] y=new int [N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				x[n]=Integer.parseInt(st.nextToken());
				y[n]=Integer.parseInt(st.nextToken());
			}
			
			int A=N*2+2;
			int sNode=A-2;
			int tNode=A-1;
			NodeMap=new Node[A];
			for (int i=0;i<A;i++) NodeMap[i]=new Node();
			
			for (int n=0;n<N;n++) if (y[n]<=d) addEdge(sNode,getInNode(n),1);
			for (int n=0;n<N;n++) if (y[n]>=W-d) addEdge(getOutNode(n),tNode,1);
			for (int n=0;n<N;n++) addEdge(getInNode(n),getOutNode(n),1);
			double minDist=4*d*d;
			for (int n=0;n<N;n++) for (int n2=0;n2<N;n2++) if (n!=n2) {
				double dx=x[n]-x[n2];
				double dy=y[n]-y[n2];
				double dSq=dx*dx+dy*dy;
				if (dSq<=minDist) addEdge(getOutNode(n),getInNode(n2),1);
			}
			
			int ans=compute(sNode,tNode);
			System.out.printf("Case %d: %d\n",tc++,ans);
		}
	}

}
