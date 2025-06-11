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
		int nId;
		long cap, flow;
		Edge reverse;

		public Edge(int n, long c, long f) {
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

	private static long pushFlow(int s, int t, long minF) {
		if (s==t || minF==0) return minF;
		for (int i=NodeMap[s].lastEIdx;i<NodeMap[s].edges.size();i++) {
			NodeMap[s].lastEIdx=i;
			Edge e=NodeMap[s].edges.get(i);
			if (Depth[e.nId]!=Depth[s]+1) continue;

			long currF=pushFlow(e.nId,t,Math.min(minF,e.cap-e.flow));
			if (currF>0) {
				e.flow+=currF;
				e.reverse.flow-=currF;
				return currF;
			}
		}
		return 0;
	}

	private static void addEdge(int s, int t, long w) {
		Edge e1=new Edge(t,w,0);
		Edge e2=new Edge(s,0,0);
		e1.reverse=e2;
		e2.reverse=e1;
		
		NodeMap[s].edges.add(e1);
		NodeMap[t].edges.add(e2);
	}

	private static long compute(int s, int t) {
		final int INF=Integer.MAX_VALUE/4;
		long ans=0;
		while (hasPath(s,t)) {
			resetEIdx();
			long flow=0;
			while ((flow=pushFlow(s,t,INF))>0) ans+=flow;
		}
		return ans;
	}

	private static int getNode(int n) {
		return n;
	}

	public static void main(String [] args) throws Exception {
		final long MAX=Long.MAX_VALUE>>4;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			int [] top=new int [N];
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) top[n]=Integer.parseInt(st.nextToken());
			int [] btm=new int [N];
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) btm[n]=Integer.parseInt(st.nextToken());
			int [] pos=new int [N];
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) pos[n]=Integer.parseInt(st.nextToken());
			// -1=btm only, 1=top only, 0=both

			int A=N+2;
			int sNode=A-2;
			int tNode=A-1;
			NodeMap=new Node[A];
			for (int i=0;i<A;i++) NodeMap[i]=new Node();

			for (int n=0;n<N;n++) {
				if (pos[n]==-1) {
					addEdge(sNode,getNode(n),btm[n]);
					addEdge(getNode(n),tNode,MAX);
				} else if (pos[n]==1) {
					addEdge(sNode,getNode(n),MAX);
					addEdge(getNode(n),tNode,top[n]);
				} else if (pos[n]==0) {
					addEdge(sNode,getNode(n),btm[n]);
					addEdge(getNode(n),tNode,top[n]);
				}
			}

			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int p=Integer.parseInt(st.nextToken())-1;
				int q=Integer.parseInt(st.nextToken())-1;
				int r=Integer.parseInt(st.nextToken());
				addEdge(getNode(p),getNode(q),r);
				addEdge(getNode(q),getNode(p),r);
			}

			long ans=compute(sNode,tNode);
			System.out.println(ans);
		}
	}

}
