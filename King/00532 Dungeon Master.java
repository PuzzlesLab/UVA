import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	private static int [][] deltas = {{0,0,-1},{0,0,1},{0,-1,0},{0,1,0},{-1,0,0},{1,0,0}};
	
	private static class Data {
		public int x, y, z, dist;
		public Data (int x, int y, int z, int dist) { this.x=x; this.y=y; this.z=z; this.dist=dist; }
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int X=Integer.parseInt(st.nextToken());
			int Y=Integer.parseInt(st.nextToken());
			int Z=Integer.parseInt(st.nextToken());
			
			char [][][] map=new char[X][Y][Z];
			int sx=-1, sy=-1, sz=-1, ex=-1, ey=-1, ez=-1;
			for (int x=0;x<X;x++) {
				for (int y=0;y<Y;y++) {
					map[x][y]=br.readLine().toCharArray();
					for (int z=0;z<Z;z++) {
						if (map[x][y][z]=='S') {
							sx=x; sy=y; sz=z;
						} else if (map[x][y][z]=='E') {
							ex=x; ey=y; ez=z;
						}
					}
				}
				br.readLine();
			}
			
			boolean [][][] visited=new boolean [X][Y][Z];
			visited[sx][sy][sz]=true;
			LinkedList<Data> queue=new LinkedList<>();
			queue.add(new Data(sx,sy,sz,0));
			int ans=-1;
			while (!queue.isEmpty()) {
				Data d=queue.removeFirst();
				if (d.x==ex && d.y==ey && d.z==ez) {
					ans=d.dist;
					break;
				}
				for (int [] delta : deltas) {
					int x=d.x+delta[0];
					int y=d.y+delta[1];
					int z=d.z+delta[2];
					if (x>=0 && x<X && y>=0 && y<Y && z>=0 && z<Z && !visited[x][y][z] && map[x][y][z]!='#') {
						visited[x][y][z]=true;
						queue.add(new Data(x,y,z,d.dist+1));
					}
				}
			}
			
			if (ans==-1) System.out.println("Trapped!");
			else System.out.printf("Escaped in %d minute(s).\n", ans);
		}
	}

}
