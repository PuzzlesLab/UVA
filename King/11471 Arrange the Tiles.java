import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Tile {
		char up,right,down,left;
		
		public Tile(String s) {
			this.up=s.charAt(0);
			this.right=s.charAt(1);
			this.down=s.charAt(2);
			this.left=s.charAt(3);
		}
	}
	
	private static class Context {
		char last3Down,last2Down,lastDown,lastRight; // 3 columns only, so recording last 3 down is enough.
		int hash;

		public Context(char c1, char c2, char c3, char c4) {
			this.last3Down=c1;
			this.last2Down=c2;
			this.lastDown=c3;
			this.lastRight=c4;

			int a=getColor(this.last3Down)*5*5*5;
			int b=getColor(this.last2Down)*5*5;
			int c=getColor(this.lastDown)*5;
			int d=getColor(this.lastRight);
			this.hash=a+b+c+d;
		}

		public static int getColor(char c) {
			if (c=='R') return 1;
			if (c=='G') return 2;
			if (c=='B') return 3;
			if (c=='Y') return 4;
			return 0;
		}
	}
	
	private static Tile [] Tiles;
	private static int [][] Dp;
	private static int Ans;

	private static int dfs(Tile [][] board, int pos, Context ctx, int mask) {
		if (pos==Tiles.length) return 1;

		int x=pos/3;
		int y=pos%3;

		if (Dp[ctx.hash][mask]==-1) {
			int sum=0;
			for (int i=0;i<Tiles.length;i++) if ((mask&(1<<i))==0) {
				Tile t=Tiles[i];
				if (x>0 && board[x-1][y].down!=t.up) continue; // check top
				if (y>0 && board[x][y-1].right!=t.left) continue; // check left
				board[x][y]=t;

				Context nCtx=new Context(ctx.last2Down,ctx.lastDown,t.down,y<2?t.right:'A');
				sum+=dfs(board,pos+1,nCtx,mask|(1<<i));
			}
			Dp[ctx.hash][mask]=sum;
		}

		return Dp[ctx.hash][mask];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			Tiles=new Tile[12];
			int T=0;
			while (true) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				while (st.hasMoreTokens()) Tiles[T++]=new Tile(st.nextToken());
				if (T==Tiles.length) break;
			}

			Dp=new int [5*5*5*5][1<<Tiles.length];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
			Ans=dfs(new Tile[4][3],0,new Context('A','A','A','A'),0);

			System.out.printf("Case %d: %d\n",tc,Ans);
		}
	}

}