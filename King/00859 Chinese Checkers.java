import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Context {
		int x, y, moves;
		
		public Context(int x, int y, int moves) {
			this.x=x;
			this.y=y;
			this.moves=moves;
		}
	}

	public static void main(String[] args) throws Exception {
		final int NO_MOVES=-1;
		final int [][] stepDeltas={{0,-1},{-1,0},{0,1}};
		final int [][] jumpDeltas={{0,-1},{0,1},{-1,0},{-1,-1},{-1,1}}; // Left/right/front/diagonal-left/diagonal-right

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=0;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int L=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int I=2*2*C;
			
			boolean [][] board=new boolean [L][C];
			for (int i=0;i<I;i++) {
				st=new StringTokenizer(br.readLine());
				board[L-Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())-1]=true;
			}

			st=new StringTokenizer(br.readLine());
			int sx=L-Integer.parseInt(st.nextToken());
			int sy=Integer.parseInt(st.nextToken())-1;
			board[sx][sy]=true;

			int [][] moves=new int[L][C];
			for (int l=0;l<L;l++) Arrays.fill(moves[l], NO_MOVES);
			moves[sx][sy]=0;
			// Move left + right + front
			for (int [] delta: stepDeltas) {
				int nx=sx+delta[0];
				int ny=sy+delta[1];
				if (nx>=0 && nx<L && ny>=0 && ny<C && !board[nx][ny] && moves[nx][ny]==NO_MOVES) moves[nx][ny]=1;
			}

			// Jump
			LinkedList<Context> q=new LinkedList<>();
			q.addLast(new Context(sx,sy,0));
			while (!q.isEmpty()) {
				Context ctx=q.removeFirst();
				for (int [] delta: jumpDeltas) {
					int nx=ctx.x+delta[0];
					int ny=ctx.y+delta[1];
					if (nx>=0 && nx<L && ny>=0 && ny<C && board[nx][ny]) {
						int nx2=nx+delta[0];
						int ny2=ny+delta[1];
						if (nx2>=0 && nx2<L && ny2>=0 && ny2<C && !board[nx2][ny2] && moves[nx2][ny2]==NO_MOVES) {
							moves[nx2][ny2]=ctx.moves+1;
							q.addLast(new Context(nx2,ny2,ctx.moves+1));
						}
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			if (tc>0) sb.append('\n');
			for (int l=0;l<L;l++) for (int c=0;c<C;c++) if (moves[l][c]>0) {
				sb.append(L-l);
				sb.append(' ');
				sb.append(c+1);
				sb.append(' ');
				sb.append(moves[l][c]);
				sb.append('\n');
			}
			System.out.print(sb.toString());
			tc++;
		}
	}

}
