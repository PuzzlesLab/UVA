import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	private static class Tuple implements Comparable<Tuple> {
		int a, b;
		public Tuple(int a, int b) {
			this.a=a;
			this.b=b;
		}
		
		public int compareTo(Tuple t) {
			if (this.a!=t.a) return this.a-t.a;
			return this.b-t.b;
		}
		
		public int length() {
			return this.b-this.a;
		}
	}

	private static class Monkey {
		int v;
		Tuple thirsty;
		
		public Monkey(String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.v=Integer.parseInt(st.nextToken());
			this.thirsty=new Tuple(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
	}

	private static Monkey [] Monkeys;
	private static int M;
	private static ArrayList<Integer> TS;
	private static int [] CurrD;

	private static void appendMonkeyIntervals(int n, int offset, StringBuilder sb) {
		ArrayList<Tuple> tuples=new ArrayList<>();
		for (int t=0;t<TS.size()-1;t++) if (EdgeMap[n][t+offset]!=null && EdgeMap[n][t+offset].flow>0) {
			int flow=EdgeMap[n][t+offset].flow;
			int t1=TS.get(t);
			int t2=TS.get(t+1);

			int t1s=t1+CurrD[t];
			if (t1s+flow<=t2) tuples.add(new Tuple(t1s,t1s+flow));
			else {
				tuples.add(new Tuple(t1s,t2));
				int over=flow-tuples.get(tuples.size()-1).length();
				tuples.add(new Tuple(t1,t1+over)); // Cycle through
			}
			CurrD[t]=(CurrD[t]+flow)%(t2-t1);
		}
		Collections.sort(tuples);

		ArrayList<Tuple> combined=new ArrayList<>();
		for (int i=0;i<tuples.size();i++) {
			Tuple t=tuples.get(i);
			if (combined.isEmpty()) combined.add(new Tuple(t.a,t.b));
			else if (combined.get(combined.size()-1).b==t.a) combined.get(combined.size()-1).b=t.b;
			else combined.add(new Tuple(t.a,t.b));
		}

		sb.append(combined.size());
		for (int i=0;i<combined.size();i++) {
			sb.append(" (");
			sb.append(combined.get(i).a);
			sb.append(',');
			sb.append(combined.get(i).b);
			sb.append(')');
		}

		sb.append('\n');
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			Monkeys=new Monkey[N];
			for (int n=0;n<N;n++) Monkeys[n]=new Monkey(br.readLine());
			// Calc all monkeys fulfillment + intervals
			int sum=0;
			HashSet<Integer> tsSet=new HashSet<>();
			for (int n=0;n<N;n++) {
				sum+=Monkeys[n].v;
				tsSet.add(Monkeys[n].thirsty.a);
				tsSet.add(Monkeys[n].thirsty.b);
			}
			TS=new ArrayList<>(tsSet);
			Collections.sort(TS);
			int [] tsIdxMap=new int [TS.isEmpty()?1:TS.get(TS.size()-1)+1];
			for (int i=0;i<TS.size();i++) tsIdxMap[TS.get(i)]=i;

			int A=N+TS.size()+2;
			int sNode=A-2;
			int tNode=A-1;

			NodeMap=new Node[A];
			for (int i=0;i<A;i++) NodeMap[i]=new Node();
			EdgeMap=new Edge[A][A];
			
			// Start node -> monkey
			for (int n=0;n<N;n++) addEdge(sNode,n,Monkeys[n].v);
			// Monkey to time slots
			for (int n=0;n<N;n++) for (int t=tsIdxMap[Monkeys[n].thirsty.a];t<tsIdxMap[Monkeys[n].thirsty.b];t++) {
				addEdge(n,N+t,TS.get(t+1)-TS.get(t));
			}
			// Time slots to end node
			for (int t=0;t<TS.size()-1;t++) addEdge(N+t,tNode,M*(TS.get(t+1)-TS.get(t)));
			int ans=compute(sNode,tNode);
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			if (ans!=sum) sb.append("No\n");
			else {
				sb.append("Yes\n");
				CurrD=new int [TS.size()];
				for (int n=0;n<N;n++) appendMonkeyIntervals(n,N,sb);
			}
			System.out.print(sb);

			tc++;
		}
	}

}
