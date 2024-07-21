import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static final int [][] Deltas={{0,1},{1,0},{0,-1},{-1,0}};
	private static final char [] Moves={'l','u','r','d'};
	private static final int ROW=3;
	private static final int BLANK=8;
	private static final long END_STATE=12345678;
	private static final HashMap<Long,String> Dp=new HashMap<>();

	private static class Context {
		StringBuilder sb;
		int bLoc;
		long state;
		
		public Context(long s, int b) {
			this.state=s;
			this.bLoc=b;
			this.sb=new StringBuilder();
		}
	}
	
	private static Context swap(long state, int x1, int y1, int x2, int y2) {
		int [][] puzzle=new int [ROW][ROW];
		for (int i=ROW-1;i>=0;i--) for (int i2=ROW-1;i2>=0;i2--) {
			puzzle[i][i2]=(int)(state%10);
			state/=10;
		}
		int temp=puzzle[x1][y1];
		puzzle[x1][y1]=puzzle[x2][y2];
		puzzle[x2][y2]=temp;

		long nextState=0;
		int bLoc=0;
		for (int i=0;i<ROW;i++) for (int i2=0;i2<ROW;i2++) {
			nextState=(nextState*10)+puzzle[i][i2];
			if (puzzle[i][i2]==BLANK) bLoc=i*ROW+i2;
		}
		return new Context(nextState,bLoc);
	}

	private static void buildDp() {
		long state=END_STATE;
		LinkedList<Context> q=new LinkedList<>();
		q.addLast(new Context(state,8));
		Dp.put(END_STATE,"");

		while (!q.isEmpty()) {
			Context ctx=q.removeFirst();
			int ex=ctx.bLoc/ROW;
			int ey=ctx.bLoc%ROW;

			for (int i=0;i<Deltas.length;i++) {
				int nx=ex+Deltas[i][0];
				int ny=ey+Deltas[i][1];
				if (nx>=0 && nx<ROW && ny>=0 && ny<ROW) {
					Context next=swap(ctx.state,ex,ey,nx,ny);
					if (Dp.containsKey(next.state)) continue;

					next.sb.append(Moves[i]);
					next.sb.append(ctx.sb);
					Dp.put(next.state,next.sb.toString());
					q.addLast(next);
				}
			}
		}
	}

	public static void main (String [] args) throws Exception {
		buildDp();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			br.readLine();
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			long state=0;
			for (int i=0;i<ROW;i++) for (int i2=0;i2<ROW;i2++) {
				char token=st.nextToken().charAt(0);
				int num=token=='x'?BLANK:(token-'0')-1;
				state=state*10+num;
			}

			if (n>0) System.out.println();
			System.out.println(Dp.getOrDefault(state,"unsolvable"));
		}
	}

}
