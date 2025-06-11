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
			long flow=0;
			while ((flow=pushFlow(s,t,INF))>0) ans+=flow;
		}
		return ans;
	}

	private static int X;
	private static int Y;
	private static int [][] Deltas={{0,-1},{0,1},{-1,0},{1,0}};
	
	private static int getInId(int x, int y) {
		return x*Y+y;
	}
	
	private static int getOutId(int x, int y) {
		return X*Y+getInId(x,y);
	}

	public static void main(String [] args) throws Exception {
		final int MAX=100000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			X=Integer.parseInt(st.nextToken());
			Y=Integer.parseInt(st.nextToken());
			int P=Integer.parseInt(st.nextToken());

			int A=X*Y*2+2;
			int sNode=A-2;
			int tNode=A-1;
			NodeMap=new Node[A];
			for (int i=0;i<A;i++) NodeMap[i]=new Node();
			
			char [][] map=new char [X][Y];
			for (int x=0;x<X;x++) map[x]=br.readLine().toCharArray();
			for (int x=0;x<X;x++) for (int y=0;y<Y;y++) {
				if (map[x][y]=='*') {
					addEdge(sNode,getInId(x,y),1);
					addEdge(getInId(x,y),getOutId(x,y),1);
				} else if (map[x][y]=='.') {
					addEdge(getInId(x,y),getOutId(x,y),1);
				} else if (map[x][y]=='@') {
					addEdge(getInId(x,y),getOutId(x,y),MAX);
				} else if (map[x][y]=='#') {
					addEdge(getInId(x,y),getOutId(x,y),MAX);
					addEdge(getOutId(x,y),tNode,P);
				}
			}
			for (int x=0;x<X;x++) for (int y=0;y<Y;y++) if (map[x][y]!='~') {
				for (int [] d: Deltas) {
					int nx=x+d[0];
					int ny=y+d[1];
					if (nx>=0 && nx<X && ny>=0 && ny<Y && map[nx][ny]!='~') {
						addEdge(getOutId(x,y),getInId(nx,ny),MAX);
					}
				}
			}

			int ans=compute(sNode,tNode);
			System.out.println(ans);
			br.readLine();
		}
	}

}
