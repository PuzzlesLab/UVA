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
		final int INF=Integer.MAX_VALUE>>1;
		int ans=0;
		while (hasPath(s,t)) {
			resetEIdx();
			int flow=0;
			while ((flow=pushFlow(s,t,INF))>0) ans+=flow;
		}
		return ans;
	}

	private static int N;

	private static int getInId(int n) {
		return n;
	}
	
	private static int getOutId(int n) {
		return n+N;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			N=Integer.parseInt(s);

			int A=N*2+2;
			int sNode=A-2;
			int tNode=A-1;
			NodeMap=new Node[A];
			for (int i=0;i<A;i++) NodeMap[i]=new Node();
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) addEdge(getInId(n),getOutId(n),Integer.parseInt(st.nextToken()));

			int M=Integer.parseInt(br.readLine());
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int i=Integer.parseInt(st.nextToken())-1;
				int j=Integer.parseInt(st.nextToken())-1;
				int c=Integer.parseInt(st.nextToken());
				addEdge(getOutId(i),getInId(j),c);
			}
			
			st=new StringTokenizer(br.readLine());
			int B=Integer.parseInt(st.nextToken());
			int D=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine().trim());
			for (int b=0;b<B;b++) addEdge(sNode,getInId(Integer.parseInt(st.nextToken())-1),Integer.MAX_VALUE>>1);
			for (int d=0;d<D;d++) addEdge(getOutId(Integer.parseInt(st.nextToken())-1),tNode,Integer.MAX_VALUE>>1);

			int ans=compute(sNode,tNode);
			System.out.println(ans);
		}
	}

}
