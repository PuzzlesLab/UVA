import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
		br.readLine(); // Empty.
		for (int tc=0;tc<TC;tc++) {
			ArrayList<String> residents=new ArrayList<>();
			HashMap<String,Integer> residentIdMap=new HashMap<>();
			ArrayList<String> clubs=new ArrayList<>();
			HashMap<String,Integer> clubIdMap=new HashMap<>();
			ArrayList<String> parties=new ArrayList<>();	
			HashMap<String,Integer> partyIdMap=new HashMap<>();
			ArrayList<ArrayList<String>> inputs=new ArrayList<>();
			while (true) {
				String s=br.readLine();
				if (s==null || s.isEmpty()) break;
				StringTokenizer st=new StringTokenizer(s);
				ArrayList<String> list=new ArrayList<>();
				while (st.hasMoreTokens()) list.add(st.nextToken());
				inputs.add(list);
				
				String resident=list.get(0);
				if (!residentIdMap.containsKey(resident)) {
					residentIdMap.put(resident,residents.size());
					residents.add(resident);
				}
				String party=list.get(1);
				if (!partyIdMap.containsKey(party)) {
					partyIdMap.put(party,parties.size());
					parties.add(party);
				}
				for (int i=2;i<list.size();i++) {
					s=list.get(i);
					if (!clubIdMap.containsKey(s)) {
						clubIdMap.put(s,clubs.size());
						clubs.add(s);
					}
				}
			}

			int Cd=residents.size();
			int Pd=Cd+clubs.size();
			int sNode=Pd+parties.size();
			int tNode=sNode+1;
			int N=tNode+1;

			NodeMap=new Node[N];
			for (int i=0;i<N;i++) NodeMap[i]=new Node();
			EdgeMap=new Edge[N][N];
			
			// Start to Club
			for (int i=0;i<clubIdMap.size();i++) addEdge(sNode,Cd+i,1);
			// Club to Resident + Resident to Party
			for (int i=0;i<inputs.size();i++) {
				ArrayList<String> tokens=inputs.get(i);
				int rId=residentIdMap.get(tokens.get(0));
				int pId=partyIdMap.get(tokens.get(1))+Pd;
				addEdge(rId,pId,1);
				for (int i2=2;i2<tokens.size();i2++) {
					int cId=clubIdMap.get(tokens.get(i2))+Cd;
					addEdge(cId,rId,1);
				}
			}
			// Party to End
			for (int i=0;i<parties.size();i++) addEdge(i+Pd,tNode,(clubs.size()>>1)+((clubs.size()&1)==0?-1:0));

			int ans=compute(sNode,tNode);
			StringBuilder sb=new StringBuilder();
			if (tc>0) sb.append('\n');
			if (ans!=clubs.size()) sb.append("Impossible.");
			else {
				for (int i=0;i<clubs.size();i++) {
					Node cNode=NodeMap[i+Cd];
					for (int i2=0;i2<cNode.edges.size();i2++) {
						Edge e=cNode.edges.get(i2);
						if (e.flow==1) {
							sb.append(residents.get(e.nId));
							sb.append(' ');
							sb.append(clubs.get(i));
							sb.append('\n');
							break;
						}
					}
				}
				if (sb.charAt(sb.length()-1)=='\n') sb.setLength(sb.length()-1);
			}
			System.out.println(sb);
		}
	}

}
