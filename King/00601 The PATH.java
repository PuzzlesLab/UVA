import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class Main {

	private static class Path {
		boolean hasLeftCol, hasRightCol, hasTopRow, hasBtmRow;
		char color;
		
		public Path(char color) {
			this.color=color;
		}
	}

	private static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	public static void main(String[] args) throws Exception {
		final int [][] deltas={{0,1},{0,-1},{-1,0},{1,0}};

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			br.readLine(); //Empty line

			char [][] board=new char [N][];
			for (int n=0;n<N;n++) board[n]=br.readLine().toCharArray();
			br.readLine(); //Empty line

			ArrayList<Path> paths=new ArrayList<>();
			Path [][] ofPath=new Path [N][N];
			for (int x=0;x<board.length;x++) for (int y=0;y<board[x].length;y++) if (ofPath[x][y]==null && board[x][y]!='U') {
				Path p=new Path(board[x][y]);
				p.hasTopRow=x==0;
				p.hasBtmRow=x==board.length-1;
				p.hasLeftCol=y==0;
				p.hasRightCol=y==board[x].length-1;
				paths.add(p);
				ofPath[x][y]=p;

				Stack<Position> stack=new Stack<>();
				stack.push(new Position(x,y));
				while (!stack.isEmpty()) {
					Position curr=stack.pop();
					for (int [] delta: deltas) {
						int nx=curr.x+delta[0];
						int ny=curr.y+delta[1];
						if (nx>=0 && nx<board.length && ny>=0 && ny<board[nx].length && board[nx][ny]==p.color && ofPath[nx][ny]==null) {
							stack.push(new Position(nx,ny));
							ofPath[nx][ny]=p;
							p.hasLeftCol|=ny==0;
							p.hasRightCol|=ny==board[nx].length-1;
							p.hasTopRow|=nx==0;
							p.hasBtmRow|=nx==board.length-1;
						}
					}
				}
			}
			
			// Check white paths
			boolean wWin=false;
			for (int i=0;i<paths.size();i++) {
				Path p=paths.get(i);
				if (p.color=='W' && p.hasLeftCol && p.hasRightCol) {
					wWin=true;
					break;
				}
			}
			if (wWin) {
				System.out.println("White has a winning path.");
				continue;
			}
			
			// Check black paths
			boolean bWin=false;
			for (int i=0;i<paths.size();i++) {
				Path p=paths.get(i);
				if (p.color=='B' && p.hasTopRow && p.hasBtmRow) {
					bWin=true;
					break;
				}
			}
			if (bWin) {
				System.out.println("Black has a winning path.");
				continue;
			}

			// Fill U (White)
			for (int x=0;x<board.length;x++) for (int y=0;y<board[x].length;y++) if (board[x][y]=='U') {
				boolean leftCol=y==0;
				boolean rightCol=y==board[x].length-1;
				for (int [] delta: deltas) {
					int nx=x+delta[0];
					int ny=y+delta[1];
					if (nx>=0 && nx<board.length && ny>=0 && ny<board[nx].length && board[nx][ny]=='W') {
						leftCol|=ofPath[nx][ny].hasLeftCol;
						rightCol|=ofPath[nx][ny].hasRightCol;
					}
				}
				if (leftCol && rightCol) {
					wWin=true;
					break;
				}
			}
			if (wWin) {
				System.out.println("White can win in one move.");
				continue;
			}

			// Fill U (Black)
			for (int x=0;x<board.length;x++) for (int y=0;y<board[x].length;y++) if (board[x][y]=='U') {
				boolean topRow=x==0;
				boolean btmRow=x==board.length-1;
				for (int [] delta: deltas) {
					int nx=x+delta[0];
					int ny=y+delta[1];
					if (nx>=0 && nx<board.length && ny>=0 && ny<board[nx].length && board[nx][ny]=='B') {
						topRow|=ofPath[nx][ny].hasTopRow;
						btmRow|=ofPath[nx][ny].hasBtmRow;
					}
				}
				if (topRow && btmRow) {
					bWin=true;
					break;
				}
			}
			if (bWin) {
				System.out.println("Black can win in one move.");
				continue;
			}
			
			System.out.println("There is no winning path.");
		}
	}

}
