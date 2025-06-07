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
		final int INF=Integer.MAX_VALUE/2;
		int ans=0;
		while (hasPath(s,t)) {
			resetEIdx();
			int flow=0;
			while ((flow=pushFlow(s,t,INF))>0) ans+=flow;
		}
		return ans;
	}

	private static final int [][] Deltas= {{-1,0},{1,0},{0,-1},{0,1}};
	private static int S;
	private static int A;
	
	private static int getUpId(int s, int a) {
		return s*A+a;
	}

	private static int getDownId(int s, int a) {
		return S*A+getUpId(s,a);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			S=Integer.parseInt(st.nextToken());
			A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());

			int T=S*A*2+2;
			int sNode=T-2;
			int tNode=T-1;

			NodeMap=new Node[T];
			for (int i=0;i<T;i++) NodeMap[i]=new Node();

			// Start node to banks
			for (int b=0;b<B;b++) {
				st=new StringTokenizer(br.readLine());
				int s=Integer.parseInt(st.nextToken())-1;
				int a=Integer.parseInt(st.nextToken())-1;
				addEdge(sNode,getUpId(s,a),1);
			}
			for (int s=0;s<S;s++) for (int a=0;a<A;a++) {
				addEdge(getUpId(s,a),getDownId(s,a),1);
				for (int [] d: Deltas) { // Movement between nodes
					int ns=s+d[0];
					int na=a+d[1];
					if (ns>=0 && ns<S && na>=0 && na<A) addEdge(getDownId(s,a),getUpId(ns,na),1);
				}
				if (s==0 || s==S-1 || a==0 || a==A-1) { // Side nodes to end node
					addEdge(getDownId(s,a),tNode,1);
				}
			}

			int ans=compute(sNode,tNode);
			System.out.println(ans==B?"possible":"not possible");
		}
	}

}