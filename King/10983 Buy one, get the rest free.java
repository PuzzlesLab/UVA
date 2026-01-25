import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Flight {
		int day;
		int from;
		int to;
		int price;
		int cap;
		
		public Flight(int u, int v, int c, int p, int e) {
			this.from=u;
			this.to=v;
			this.cap=c;
			this.price=p;
			this.day=e;
		}
	}
	
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

	private static final int MAX_BUDGET=100000;
	private static int M;
	private static int N;
	private static int D; 
	private static Node [] NodeMap;
	private static Edge [][] EdgeMap;
	private static int [] Depth;
	private static Flight [] Flights;
	private static int [] Parts;
	private static int AllParts;

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

	private static void addEdge(int n1, int n2, int cap) {
		EdgeMap[n1][n2]=new Edge(n2,cap,0);
		NodeMap[n1].edges.add(EdgeMap[n1][n2]);
		EdgeMap[n2][n1]=new Edge(n1,0,0);
		NodeMap[n2].edges.add(EdgeMap[n2][n1]);
	}
	
	private static int getCityNId(int city, int day) {
		return city*(D+1)+day;
	}

	private static int solve(int budget) {
		NodeMap=new Node [getCityNId(N-1,D+1)+2];
		EdgeMap=new Edge [NodeMap.length][NodeMap.length];

		int s=NodeMap.length-2;
		int t=NodeMap.length-1;
		for (int m=0;m<NodeMap.length;m++) NodeMap[m]=new Node();

		// s to cities.
		for (int n=0;n<N;n++) addEdge(s,getCityNId(n,0),Parts[n]);
		// city day N to day N+1
		for (int n=0;n<N;n++) for (int d=0;d<D;d++) addEdge(getCityNId(n,d),getCityNId(n,d+1),AllParts);
		// Cities to cities
		for (int m=0;m<M;m++) {
			Flight f=Flights[m];
			if (f.price<=budget) addEdge(getCityNId(f.from,f.day),getCityNId(f.to,f.day+1),f.cap);
		}
		// city N's competition day to t
		addEdge(getCityNId(N-1,D),t,AllParts);

		int ans=0;
		while (hasPath(s,t)) {
			resetEIdx();
			int flow=0;
			while ((flow=pushFlow(s,t,AllParts))>0) ans+=flow;
		}

		return ans>=AllParts ? 0 : -1;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			D=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());

			Flights=new Flight[M];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				Flights[m]=new Flight(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}

			st=new StringTokenizer(br.readLine());
			AllParts=0;
			Parts=new int [N];
			for (int n=0;n<N;n++) {
				Parts[n]=Integer.parseInt(st.nextToken());
				AllParts+=Parts[n];
			}

			int min=0;
			int max=MAX_BUDGET+2;
			while (max>min+1) {
				int mid=(min+max)>>1;
				if (solve(mid)==0) max=mid;
				else min=mid;
			}
			if (solve(min)==0) max=min;
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc);
			sb.append(": ");
			if (max>MAX_BUDGET) sb.append("Impossible");
			else sb.append(max);
			System.out.println(sb.toString());
		}
	}

}