import java.util.LinkedList;
import java.util.Scanner;

class Main {

	/*
	 * Simplified statement:
	 * There are 2 malls, given the coordinates of the malls,
	 * find the lowest distance between them.
	 */
	private static class Edge {
		int x, y, dist;
		
		public Edge(int x, int y, int dist) {
			this.x=x;
			this.y=y;
			this.dist=dist;
		}
	}

	private static int [][] Map;
	private static final int [][] Deltas={{0,-1},{0,1},{-1,0},{1,0}};

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (true) {
			Map=new int [2001][2001];
			int m1=sc.nextInt();
			if (m1==0) break;

			LinkedList<Edge> edges=new LinkedList<>();
			for (int m=0;m<m1;m++) {
				int x=sc.nextInt();
				int y=sc.nextInt();
				Map[x][y]=1;
				edges.addLast(new Edge(x,y,0));
			}

			int m2=sc.nextInt();
			for (int m=0;m<m2;m++) {
				int x=sc.nextInt();
				int y=sc.nextInt();
				Map[x][y]=2;
			}

			int ans=-1;
			while (!edges.isEmpty() && ans==-1) {
				Edge e=edges.removeFirst();
				for (int [] delta: Deltas) {
					int nx=e.x+delta[0];
					int ny=e.y+delta[1];
					if (nx>=0 && nx<Map.length && ny>=0 && ny<Map[nx].length) {
						if (Map[nx][ny]==2) {
							ans=e.dist+1;
							break;
						}
						if (Map[nx][ny]==0) {
							Map[nx][ny]=3;
							edges.addLast(new Edge(nx,ny,e.dist+1));
						}
					}
				}
			}

			System.out.println(ans);
		}
	}

}