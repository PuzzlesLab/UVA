import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

class Main {

	private static class Tuple {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	private static final int MAX_DIST=1000000;
	private static char [][] Map=new char [8][];
	private static int [][] Deltas={{-1,2},{-2,1},{-1,-2},{-2,-1},{1,2},{2,1},{1,-2},{2,-1}};
	private static ArrayList<Tuple> Points;
	private static int P;
	private static int [][] Dist;
	private static int [][] Dp;

	private static int [] calcDist(Tuple from) {
		int [][] minDist=new int [Map.length][Map[0].length];
		for (int i=0;i<minDist.length;i++) Arrays.fill(minDist[i],MAX_DIST);
		minDist[from.x][from.y]=0;
		
		LinkedList<Tuple> q=new LinkedList<>();
		q.addLast(from);

		while (!q.isEmpty()) {
			Tuple c=q.removeFirst();
			for (int [] d: Deltas) {
				int nx=c.x+d[0];
				int ny=c.y+d[1];
				if (nx>=0 && nx<Map.length && ny>=0 && ny<Map[nx].length && minDist[nx][ny]==MAX_DIST) {
					if (Map[nx][ny]=='K' || Map[nx][ny]=='p') continue;
					minDist[nx][ny]=minDist[c.x][c.y]+1;
					q.addLast(new Tuple(nx,ny));
				}
			}
		}
		
		int [] pDist=new int [P];
		for (int p=0;p<P;p++) pDist[p]=minDist[Points.get(p).x][Points.get(p).y];
		return pDist;
	}

	private static int tsp(int curr, int mask) {
		if (mask==(1<<P)-1) return 0;
		
		if (Dp[curr][mask]==-1) {
			int ans=MAX_DIST;
			for (int i=0;i<P;i++) if ((mask&(1<<i))==0) ans=Math.min(ans,Dist[curr][i]+tsp(i,mask|(1<<i)));
			Dp[curr][mask]=ans;
		}
		return Dp[curr][mask];
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			String s=br.readLine();
			if (s.trim().length()==0) s=br.readLine(); // Empty line, let's read next line.
			int N=Integer.parseInt(s);
			for (int i=0;i<Map.length;i++) Map[i]=br.readLine().toCharArray();
			
			Tuple knight=new Tuple(0,0);
			Points=new ArrayList<>();
			Points.add(knight);
			for (int i=0;i<Map.length;i++) for (int i2=0;i2<Map[i].length;i2++) {
				if (Map[i][i2]=='P') Points.add(new Tuple(i,i2));
				else if (Map[i][i2]=='k') {
					knight.x=i;
					knight.y=i2;
				}
			}
			P=Points.size();
			
			Dist=new int [P][];
			for (int p=0;p<P;p++) Dist[p]=calcDist(Points.get(p));

			Dp=new int [P][1<<P];
			for (int p=0;p<P;p++) Arrays.fill(Dp[p],-1);
			int ans=tsp(0,1);
			System.out.println(ans<=N?"Yes":"No");
		}
	}

}