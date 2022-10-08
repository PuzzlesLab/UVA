import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class Main {

	private static final int [][] Deltas={{0,1},{0,-1},{-1,0},{1,0}};

	private static class Cluster {
		int x, y, size;

		public Cluster(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	private static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	private static class Move {
		int x, y, count, points;
		char color;
		
		public Move(int x, int y, int count, int points, char color) {
			this.x=x;
			this.y=y;
			this.count=count;
			this.points=points;
			this.color=color;
		}
	}

	private static Cluster findCluster(char [][] game) {
		Cluster result=null;

		boolean [][] visited=new boolean [game.length][game[0].length];
		for (int y=0;y<game[0].length;y++) for (int x=game.length-1;x>=0;x--) if (!visited[x][y] && game[x][y]!=' ') {
			Cluster c=new Cluster(x,y);
			Stack<Position> stk=new Stack<>();
			stk.push(new Position(x,y));
			visited[x][y]=true;

			while (!stk.isEmpty()) {
				Position curr=stk.pop();
				c.size++;

				for (int [] delta: Deltas) {
					int nx=curr.x+delta[0];
					int ny=curr.y+delta[1];
					if (nx>=0 && nx<game.length && ny>=0 && ny<game[nx].length && !visited[nx][ny] && game[x][y]==game[nx][ny]) {
						stk.push(new Position(nx,ny));
						visited[nx][ny]=true;
					}
				}
			}
			
			if (result==null || c.size>result.size) result=c;
		}

		return result;
	}

	private static void purge(char [][] game, int x, int y) {
		Stack<Position> stk=new Stack<>();
		stk.push(new Position(x,y));
		char color=game[x][y];
		game[x][y]=' ';
		
		while (!stk.isEmpty()) {
			Position curr=stk.pop();
			for (int [] delta: Deltas) {
				int nx=curr.x+delta[0];
				int ny=curr.y+delta[1];
				if (nx>=0 && nx<game.length && ny>=0 && ny<game[nx].length && game[nx][ny]==color) {
					stk.push(new Position(nx,ny));
					game[nx][ny]=' ';
				}
			}
		}
	}

	private static int countItems(char [][] game, int col) {
		int count=0;
		for (int x=0;x<game.length;x++) if (game[x][col]!=' ') count++;
		return count;
	}

	private static void rearrange(char [][] game) {
		// Drop rows if empty
		for (int y=0;y<game[0].length;y++) {
			for (int x=game.length-1;x>=0;x--) if (game[x][y]==' ') {
				// Find next valid row
				for (int nx=x-1;nx>=0;nx--) if (game[nx][y]!=' ') {
					game[x][y]=game[nx][y];
					game[nx][y]=' ';
					break;
				}
			}
		}
		
		// Move column left if empty
		for (int y=0;y<game[0].length;y++) {
			int itemsCount=countItems(game,y);
			if (itemsCount>0) continue;
			
			// Find next valid column
			for (int ny=y+1;ny<game[0].length;ny++) {
				if (countItems(game,ny)>0) {
					for (int x=0;x<game.length;x++) {
						game[x][y]=game[x][ny];
						game[x][ny]=' ';
					}
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine().trim());
		for (int tc=1;tc<=TC;tc++) {
			br.readLine(); //Empty line
			
			char [][] game=new char [10][];
			for (int i=0;i<game.length;i++) game[i]=br.readLine().trim().toCharArray();

			ArrayList<Move> moves=new ArrayList<>();
			while (true) {
				Cluster toClean=findCluster(game);
				if (toClean==null || toClean.size==1) break;
				moves.add(new Move(10-toClean.x,toClean.y+1,toClean.size,(toClean.size-2)*(toClean.size-2),game[toClean.x][toClean.y]));
				purge(game, toClean.x, toClean.y);
				rearrange(game);
			}

			StringBuilder sb=new StringBuilder();
			if (tc>1) sb.append('\n');
			sb.append("Game ");
			sb.append(tc);
			sb.append(":\n\n");

			int score=0;
			int remSize=game.length*game[0].length;
			for (int i=0;i<moves.size();i++) {
				Move curr=moves.get(i);
				sb.append("Move ");
				sb.append(i+1);
				sb.append(" at (");
				sb.append(curr.x);
				sb.append(',');
				sb.append(curr.y);
				sb.append("): removed ");
				sb.append(curr.count);
				sb.append(" balls of color ");
				sb.append(curr.color);
				sb.append(", got ");
				sb.append(curr.points);
				sb.append(" points.\n");
				score+=curr.points;
				remSize-=curr.count;
			}
			if (remSize==0) score+=1000;
			sb.append("Final score: ");
			sb.append(score);
			sb.append(", with ");
			sb.append(remSize);
			sb.append(" balls remaining.");
			System.out.println(sb.toString());
		}
	}

}
