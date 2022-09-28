import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class Node {
		int x, y, z;
		boolean benny, visited;

		public Node(int x, int y, int z) {
			this.x=x;
			this.y=y;
			this.z=z;
		}
	}

	private static String constructKey(int x, int y, int z) {
		StringBuilder sb=new StringBuilder();
		sb.append(x);
		sb.append('_');
		sb.append(y);
		sb.append('_');
		sb.append(z);
		return sb.toString();
	}

	private static final int [][] Deltas={{-1,-1},{-1,0},{0,-1},{0,1},{1,0},{1,1}};
	private static Node [][] Nodes;
	private static boolean ExistsSideX;
	private static boolean ExistsSideY;
	private static boolean ExistsSideZ;

	private static void dfs(int x, int y) {
		if (Nodes[x][y].x==0) ExistsSideX=true;
		if (Nodes[x][y].y==0) ExistsSideY=true;
		if (Nodes[x][y].z==0) ExistsSideZ=true;
		
		Nodes[x][y].visited=true;
		for (int [] delta: Deltas) {
			int nx=x+delta[0];
			int ny=y+delta[1];
			if (nx>=0 && nx<Nodes.length && ny>=0 && ny<Nodes[nx].length && Nodes[nx][ny].benny && !Nodes[nx][ny].visited) {
				dfs(nx,ny);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			Nodes=new Node [N+1][];
			HashMap<String,Node> nodeMap=new HashMap<>();
			for (int r=0;r<Nodes.length;r++) Nodes[r]=new Node [r+1];
			for (int c=0;c<=N;c++) {
				for (int r=c;r<=N;r++) {
					Node n=new Node(c,r-c,N-r);
					nodeMap.put(constructKey(n.x,n.y,n.z),n);
					Nodes[r][c]=n;
				}
			}
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int z=Integer.parseInt(st.nextToken());
				String key=constructKey(x,y,z);
				if (nodeMap.containsKey(key)) nodeMap.get(key).benny=true;
			}
			
			boolean bennyWins=false;
			for (int x=0;x<Nodes.length && !bennyWins;x++) for (int y=0;y<Nodes[x].length && !bennyWins;y++) {
				if (Nodes[x][y].benny && !Nodes[x][y].visited) {
					ExistsSideX=false;
					ExistsSideY=false;
					ExistsSideZ=false;
					dfs(x,y);
					bennyWins=ExistsSideX && ExistsSideY && ExistsSideZ;
				}
			}
			
			System.out.println(bennyWins?"Benny":"Willy");
		}
	}

}