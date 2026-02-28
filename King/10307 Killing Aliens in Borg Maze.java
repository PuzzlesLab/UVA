import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_DIST=1000000;
	private static final int [][] Deltas={{-1,0},{0,-1},{0,1},{1,0}};
	private static int X;
	private static int Y;

	private static class Edge implements Comparable<Edge> {
		int x1,y1,x2,y2,cost;
		
		public Edge(int x1, int y1, int x2, int y2, int c) {
			this.x1=x1;
			this.y1=y1;
			this.x2=x2;
			this.y2=y2;
			this.cost=c;
		}
		
		public int compareTo(Edge e) {
			return this.cost-e.cost;
		}
	}

	private static int getIndex(int x, int y) {
		return x*Y+y;
	}

	private static int bfs(char [][] map, int x1, int y1, int x2, int y2) {
		boolean [] visited=new boolean [X*Y];
		LinkedList<int []> q=new LinkedList<>();
		q.add(new int [] {x1,y1,0});
		visited[getIndex(x1,y1)]=true;
		while (!q.isEmpty()) {
			int [] curr=q.removeFirst();
			if (curr[0]==x2 && curr[1]==y2) return curr[2];
			for (int [] d: Deltas) {
				int nx=curr[0]+d[0];
				int ny=curr[1]+d[1];

				if (nx<0||nx>=X||ny<0||ny>=Y) continue;
				if (map[nx][ny]=='#' || visited[getIndex(nx,ny)]) continue;
				visited[getIndex(nx,ny)]=true;
				q.addLast(new int [] {nx,ny,curr[2]+1});
			}
			
		}
		return MAX_DIST;
	}

	private static int getParent(int [] parent, int node) {
		if (parent[node]==node) return node;
		return parent[node]=getParent(parent,parent[node]);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			Y=Integer.parseInt(st.nextToken());
			X=Integer.parseInt(st.nextToken());
			char [][] map=new char [X][];
			for (int i=0;i<X;i++) map[i]=br.readLine().toCharArray();
			
			ArrayList<int []> points=new ArrayList<>();
			for (int x=0;x<X;x++) for (int y=0;y<Y;y++) if (map[x][y]=='A' || map[x][y]=='S') points.add(new int [] {x,y});

			PriorityQueue<Edge> q=new PriorityQueue<>();
			for (int i=0;i<points.size();i++) for (int i2=i+1;i2<points.size();i2++) {
				int [] p1=points.get(i);
				int [] p2=points.get(i2);
				q.add(new Edge(p1[0],p1[1],p2[0],p2[1],bfs(map,p1[0],p1[1],p2[0],p2[1])));
			}
			
			int [] parent=new int [X*Y];
			for (int i=0;i<parent.length;i++) parent[i]=i;
			int ans=0;
			while (!q.isEmpty()) {
				Edge e=q.poll();
				int p1=getParent(parent,getIndex(e.x1,e.y1));
				int p2=getParent(parent,getIndex(e.x2,e.y2));
				if (p1==p2) continue;
				if (p1>p2) {
					parent[p1]=p2;
					ans+=e.cost;
				} else {
					parent[p2]=p1;
					ans+=e.cost;
				}
			}
			System.out.println(ans);
		}
	}

}
