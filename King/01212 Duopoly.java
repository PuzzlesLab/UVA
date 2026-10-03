import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Node {
		ArrayList<Edge> edges=new ArrayList<>();
		int lastEIdx;
	}
	
	private static class Edge {
		int nId, cap, flow;

		public Edge(int n, int c, int f) {
			this.nId=n;
			this.cap=c;
			this.flow=f;
		}
	}
	
	private static class Bid {
		ArrayList<Integer> channels=new ArrayList<>();
		int price;
		
		public Bid(int p) {
			this.price=p;
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

	private static void addEdge(int s, int t, int w) {
		EdgeMap[s][t]=new Edge(t,w,0);
		NodeMap[s].edges.add(EdgeMap[s][t]);
		EdgeMap[t][s]=new Edge(s,0,0);
		NodeMap[t].edges.add(EdgeMap[t][s]);
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=1;tc<=TC;tc++) {
            br.readLine(); // blank
            int sum=0;

        	int N=Integer.parseInt(br.readLine());
        	Bid [] telecomBids=new Bid [N];
        	int [] telecomIdx=new int [300001];
        	Arrays.fill(telecomIdx,-1);
        	for (int n=0;n<N;n++) {
        		StringTokenizer st=new StringTokenizer(br.readLine());
        		telecomBids[n]=new Bid(Integer.parseInt(st.nextToken()));
        		sum+=telecomBids[n].price;

        		while (st.hasMoreTokens()) {
        			int c=Integer.parseInt(st.nextToken());
        			telecomBids[n].channels.add(c);
        			telecomIdx[c]=n;
        		}
        	}

        	N=Integer.parseInt(br.readLine());
        	Bid [] mobileBids=new Bid [N];
        	for (int n=0;n<N;n++) {
        		StringTokenizer st=new StringTokenizer(br.readLine());
        		mobileBids[n]=new Bid(Integer.parseInt(st.nextToken()));
        		sum+=mobileBids[n].price;

        		while (st.hasMoreTokens()) mobileBids[n].channels.add(Integer.parseInt(st.nextToken()));
        	}

        	int nodesCount=2+telecomBids.length+mobileBids.length;
        	NodeMap=new Node [nodesCount];
        	EdgeMap=new Edge [nodesCount][nodesCount];
        	Depth=new int [nodesCount];
        	for (int i=0;i<nodesCount;i++) NodeMap[i]=new Node();

        	int sId=0;
        	int tId=1;
        	for (int n=0;n<telecomBids.length;n++) addEdge(sId,2+n,telecomBids[n].price); // Start node to telecom bids
        	for (int n=0;n<mobileBids.length;n++) { // Mobile bids to end node
        		int cId=2+telecomBids.length+n;
        		addEdge(cId,tId,mobileBids[n].price);

        		for (int i=0;i<mobileBids[n].channels.size();i++) { // Create edge between intersecting bids.
        			int c=mobileBids[n].channels.get(i);

            		int tcId=telecomIdx[c];
            		if (tcId==-1) continue;

            		tcId+=2;
        			addEdge(tcId,cId,100000000);
        		}
        	}
        	int ans=sum-compute(sId,tId); // Find min loss to solve the intersecting bids.

        	StringBuilder sb=new StringBuilder();
        	if (tc>1) sb.append('\n');
        	sb.append("Case ");
        	sb.append(tc);
        	sb.append(":\n");
        	sb.append(ans);
        	System.out.println(sb);
        }
	}

}