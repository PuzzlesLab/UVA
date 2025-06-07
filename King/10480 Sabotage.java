import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
		final long INF=Long.MAX_VALUE>>2;
		int ans=0;
		while (hasPath(s,t)) {
			resetEIdx();
			long flow=0;
			while ((flow=pushFlow(s,t,INF))>0) ans+=flow;
		}
		return ans;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean first=true;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			int A=N;
			NodeMap=new Node[A];
			for (int i=0;i<A;i++) NodeMap[i]=new Node();

			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				long c=Long.parseLong(st.nextToken());
				addEdge(n1,n2,c);
				addEdge(n2,n1,c);
			}
			for (int i=0;i<A;i++) NodeMap[i].edges.sort((a,b) -> a.nId - b.nId);

			compute(0,1);
			// Find S set
			HashSet<Integer> set1=new HashSet<>();
			set1.add(0);
			LinkedList<Integer> q=new LinkedList<>();
			q.add(0);
			while (!q.isEmpty()) {
				int curr=q.removeFirst();
				for (int i=0;i<NodeMap[curr].edges.size();i++) {
					Edge e=NodeMap[curr].edges.get(i);
					if (e.cap>e.flow && !set1.contains(e.nId)) { // flow < cap = not max flow, useless to cut. (& Implicit e.cap>0)
						set1.add(e.nId);
						q.addLast(e.nId);
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			if (!first) sb.append('\n');
			for (int n=0;n<N;n++) if (set1.contains(n)) {
				for (int i=0;i<NodeMap[n].edges.size();i++) {
					Edge e=NodeMap[n].edges.get(i);
					if (e.cap>0 && e.cap==e.flow && !set1.contains(e.nId)) { // Cut max flows to have min cost.
						sb.append(n+1);
						sb.append(' ');
						sb.append(e.nId+1);
						sb.append('\n');
					}
				}
			}
			System.out.print(sb);
			first=false;
		}
	}

}