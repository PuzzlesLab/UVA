import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	private static final int MAX_DIST=1000000;
	private static int X;
	private static int Y;
	private static ArrayList<Tuple> Points;
	private static int P;
	private static int [][] TspDP;

	private static int getDist(char [][] map, Tuple from, Tuple to) {
		int dx=Math.abs(from.x-to.x);
		int dy=Math.abs(from.y-to.y);
		int diag=Math.min(dx, dy);
		dx-=diag;
		dy-=diag;
		return diag+dx+dy;
	}
	
	private static int tsp(int [][] dist, int curr, int mask) {
		if (mask==(1<<P)-1) return dist[curr][0];
		if (TspDP[curr][mask]==-1) {
			int d=MAX_DIST;
			for (int p=0;p<P;p++) if ((mask&(1<<p))==0) {
				d=Math.min(d,dist[curr][p]+tsp(dist,p,mask|(1<<p)));
			}
			TspDP[curr][mask]=d;
		}
		return TspDP[curr][mask];
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			X=Integer.parseInt(st.nextToken());
			Y=Integer.parseInt(st.nextToken());
			
			char [][] map=new char [X][];
			for (int x=0;x<X;x++) map[x]=br.readLine().toCharArray();

			Tuple depot=new Tuple(-1,-1);
			Points=new ArrayList<>();
			Points.add(depot);
			for (int x=0;x<X;x++) for (int y=0;y<Y;y++) {
				if (map[x][y]=='L') {
					depot.x=x;
					depot.y=y;
				}
				else if (map[x][y]=='#') Points.add(new Tuple(x,y));
			}
			P=Points.size();

			int [][] dist=new int [P][P];
			for (int p=0;p<P;p++) {
				Arrays.fill(dist[p],MAX_DIST);
				dist[p][p]=0;
			}
			for (int p=0;p<P;p++) for (int p2=0;p2<P;p2++) dist[p][p2]=dist[p2][p]=getDist(map,Points.get(p),Points.get(p2));

			TspDP=new int [P][1<<P];
			for (int p=0;p<P;p++) Arrays.fill(TspDP[p],-1);
			System.out.println(tsp(dist,0,1));
		}
	}

}
