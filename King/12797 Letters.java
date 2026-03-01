import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

class Main {

	private static final int [][] Deltas={{0,1},{0,-1},{-1,0},{1,0}};
	private static final int MAX_DIST=1000000;
	private static int Dist=MAX_DIST;
	private static char [][] Map;
	private static int N;

	private static class Context {
		int x, y, step;
		
		public Context(int x, int y, int step) {
			this.x=x;
			this.y=y;
			this.step=step;
		}
	}

	private static void bfs(boolean [] allowedChar) {
		boolean [][] visited=new boolean [Map.length][Map.length];
		LinkedList<Context> q=new LinkedList<>();
		q.add(new Context(0,0,1));
		visited[0][0]=true;
		while (!q.isEmpty()) {
			Context ctx=q.removeFirst();
			if (ctx.x==N-1 && ctx.y==N-1) {
				Dist=Math.min(Dist,ctx.step);
				break;
			}
			for (int [] d: Deltas) {
				int dx=ctx.x+d[0];
				int dy=ctx.y+d[1];
				if (dx>=0 && dx<N && dy>=0 && dy<N && !visited[dx][dy] && allowedChar[Map[dx][dy]]) {
					visited[dx][dy]=true;
					q.addLast(new Context(dx,dy,ctx.step+1));
				}
			}
		}
	}

	private static void compute(int curr, boolean [] allowedChar) {
		if (curr==10) {
			if (!allowedChar[Map[0][0]] || !allowedChar[Map[N-1][N-1]]) return;
			bfs(allowedChar);
			return;
		}
		char cUp=(char)('A'+curr);
		char cLow=(char)('a'+curr);
		allowedChar[cUp]=true;
		compute(curr+1,allowedChar);
		allowedChar[cUp]=false;
		allowedChar[cLow]=true;
		compute(curr+1,allowedChar);
		allowedChar[cLow]=false;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			N=Integer.parseInt(s);
			Map=new char [N][];
			for (int n=0;n<N;n++) Map[n]=br.readLine().toCharArray();
			
			boolean [] cExists=new boolean [128];
			for (int n=0;n<N;n++) for (int n2=0;n2<N;n2++) cExists[Map[n][n2]]=true;

			boolean [] allowed=new boolean[128];
			Arrays.fill(allowed,true);
			for (int i='A';i<='J';i++) allowed[i]=false;
			for (int i='a';i<='j';i++) allowed[i]=false;

			Dist=MAX_DIST;
			compute(0, allowed);
			System.out.println(Dist==MAX_DIST?-1:Dist);
		}
	}

}