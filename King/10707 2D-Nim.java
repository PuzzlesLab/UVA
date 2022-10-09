import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static final int [][] Deltas={{0,-1},{0,1},{-1,0},{1,0}};

	private static class Position {
		int x, y;
		public Position(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	private static class Cluster implements Comparable<Cluster> {
		int x, y, size;
		boolean [][] map;
		
		public Cluster(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public int compareTo(Cluster c) {
			if (this.size!=c.size) return this.size-c.size;
			if (this.x!=c.x) return this.x-c.x;
			return this.y-c.y;
		}
	}

	private static ArrayList<Cluster> getClusters(boolean [][] map) {
		ArrayList<Cluster> result=new ArrayList<>();

		boolean [][] visited=new boolean [map.length][map[0].length];
		for (int x=0;x<map.length;x++) for (int y=0;y<map[x].length;y++) if (!visited[x][y] && map[x][y]) {
			Position startPos=new Position(x,y);
			Stack<Position> stk=new Stack<>();
			stk.push(startPos);
			visited[x][y]=true;

			Cluster c=new Cluster(x,y);
			ArrayList<Position> points=new ArrayList<>();
			int minx=x, maxx=x, miny=y, maxy=y;

			while (!stk.isEmpty()) {
				Position curr=stk.pop();
				points.add(curr);
				minx=Math.min(minx,curr.x);
				maxx=Math.max(maxx,curr.x);
				miny=Math.min(miny,curr.y);
				maxy=Math.max(maxy,curr.y);
				for (int [] delta: Deltas) {
					int nx=curr.x+delta[0];
					int ny=curr.y+delta[1];
					if (nx>=0 && nx<map.length && ny>=0 && ny<map[nx].length && !visited[nx][ny] && map[nx][ny]) {
						stk.push(new Position(nx,ny));
						visited[nx][ny]=true;
					}
				}
			}

			result.add(c);
			c.size=points.size();
			c.map=new boolean [maxx-minx+1][maxy-miny+1];
			for (int i=0;i<points.size();i++) {
				Position p=points.get(i);
				c.map[p.x-minx][p.y-miny]=true;
			}
		}

		Collections.sort(result);
		return result;
	}

	private static boolean [][] rotate(boolean [][] flags) {
		boolean [][] result=new boolean [flags[0].length][flags.length];
		for (int x=0;x<flags.length;x++) {
			for (int y=0;y<flags[x].length;y++) {
				result[flags[x].length-1-y][x]=flags[x][y];
			}
		}
		return result;
	}

	private static boolean [][] reflect(boolean [][] flags) {
		boolean [][] result=new boolean [flags.length][flags[0].length];
		for (int x=0;x<flags.length;x++) {
			for (int y=0;y<flags[x].length;y++) {
				result[x][flags[x].length-1-y]=flags[x][y];
			}
		}
		return result;
	}

	private static boolean compare(boolean [][] flags1, boolean [][] flags2) {
		if (flags1.length!=flags2.length || flags1[0].length!=flags2[0].length) return false;
		for (int x=0;x<flags1.length;x++) for (int y=0;y<flags1[x].length;y++) if (flags1[x][y]!=flags2[x][y]) return false;
		return true;
	}

	private static boolean verify(Cluster c1, Cluster c2) {
		if (c1.size!=c2.size) return false;

		boolean [][] curr=c2.map;
		for (int reflect=0;reflect<2;reflect++) {
			for (int rotate=0;rotate<4;rotate++) {
				curr=rotate(curr);
				if (compare(c1.map,curr)) return true;
			}
			curr=reflect(curr);
		}

		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int W=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			boolean [][] map1=new boolean [W][H];
			for (int n=0;n<N;n++) {
				int x=W-1-Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				map1[x][y]=true;
			}
			ArrayList<Cluster> clusters1=getClusters(map1);

			st=new StringTokenizer(br.readLine());
			boolean [][] map2=new boolean [W][H];
			for (int n=0;n<N;n++) {
				int x=W-1-Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				map2[x][y]=true;
			}
			ArrayList<Cluster> clusters2=getClusters(map2);

			// Same number of clusters
			if (clusters1.size()!=clusters2.size()) {
				System.out.println("NO");
				continue;
			}

			// Every cluster pair has same size
			boolean sameSize=true;
			for (int i=0;i<clusters1.size() && sameSize;i++) sameSize&=clusters1.get(i).size==clusters2.get(i).size;
			if (!sameSize) {
				System.out.println("NO");
				continue;
			}

			// Check every cluster
			HashSet<Cluster> c2Set=new HashSet<>(clusters2);
			boolean same=true;
			for (int i=0;i<clusters1.size() && same;i++) {
				Cluster match=null;
				for (Cluster c2: c2Set) if (verify(clusters1.get(i),c2)) {
					match=c2;
					break;
				}
				same&=(match!=null);
				c2Set.remove(match);
			}

			System.out.println(same?"YES":"NO");
		}
	}

}
