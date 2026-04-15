import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_SEGMENT=1000000;
	private static boolean [][] Map;
	private static int [][] Deltas={{0,-1},{0,1},{-1,0},{1,0}};
	private static int TX;
	private static int TY;

	private static class Context {
		int x,y,segment,dir;
		
		public Context(int x, int y, int s, int d) {
			this.x=x;
			this.y=y;
			this.segment=s;
			this.dir=d;
		}
	}

	private static int bfs(int cx, int cy) {
		LinkedList<Context> q=new LinkedList<>();
		int [][] minSeg=new int [Map.length][Map[0].length];
		for (int i=0;i<minSeg.length;i++) Arrays.fill(minSeg[i],MAX_SEGMENT);

		q.addLast(new Context(cx,cy,0,-1));
		minSeg[cx][cy]=0;
		while (!q.isEmpty()) {
			Context ctx=q.removeFirst();
			for (int i=0;i<Deltas.length;i++) {
				int nx=ctx.x+Deltas[i][0];
				int ny=ctx.y+Deltas[i][1];
				if (nx<0||nx>=Map.length||ny<0||ny>=Map[nx].length||Map[nx][ny]) continue;
				
				int nSeg=ctx.segment+(ctx.dir==i?0:1);
				if (minSeg[nx][ny]<=nSeg) continue;

				minSeg[nx][ny]=nSeg;
				q.addLast(new Context(nx,ny,nSeg,i));
			}
		}
		return minSeg[TX][TY];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int W=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());
			Map=new boolean [H+2][W+2];
			for (int h=1;h<=H;h++) {
				s=br.readLine();
				for (int w=1;w<=W;w++) Map[h][w]=s.charAt(w-1)=='X';
			}

			//for (int i=0;i<Map.length;i++) System.out.println(Arrays.toString(Map[i]));

			StringBuilder sb=new StringBuilder();
			sb.append("Board #");
			sb.append(tc);
			sb.append(":\n");

			int p=1;
			while (!(s=br.readLine()).equals("0 0 0 0")) {
				st=new StringTokenizer(s);
				int cy=Integer.parseInt(st.nextToken());
				int cx=Integer.parseInt(st.nextToken());
				TY=Integer.parseInt(st.nextToken());
				TX=Integer.parseInt(st.nextToken());

				boolean toggle=Map[TX][TY];
				
				if (toggle) Map[TX][TY]=false;
				int ans=bfs(cx,cy);
				if (toggle) Map[TX][TY]=true;

				sb.append("Pair ");
				sb.append(p++);
				sb.append(": ");
				if (ans!=MAX_SEGMENT) {
					sb.append(ans);
					sb.append(" segments.");
				} else sb.append("impossible.");
				sb.append('\n');
			}

			System.out.println(sb);
			tc++;
		}
	}

}