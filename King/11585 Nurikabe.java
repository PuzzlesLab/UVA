import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static final int [][] Deltas= {{-1,0},{1,0},{0,-1},{0,1}};
	private static final int [][] CornerDeltas= {{-1,-1},{-1,1},{1,-1},{1,1}};
	private static int R;
	private static int C;

	private static class Space {
		boolean shaded;
		boolean hasNumber;
		int number;
	}

	private static class Position {
		int x, y;
		public Position(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	private static boolean condition0(Space [][] spaces) {
		// Can't exists shaded + numbered space.
		for (int r=0;r<R;r++) for (int c=0;c<C;c++) if (spaces[r][c].shaded && spaces[r][c].hasNumber) return false;
		
		// Verify sum of value of numbered spaces = numbered spaces count
		int sum1=0;
		int sum2=0;
		for (int r=0;r<R;r++) for (int c=0;c<C;c++) {
			if (spaces[r][c].hasNumber) sum1+=spaces[r][c].number;
			if (!spaces[r][c].shaded) sum2++;
		}
		return sum1==sum2;
	}

	private static boolean condition1(Space [][] spaces) {
		boolean [][] visited=new boolean [R][C];
		int shadedGroupCount=0;
		for (int r=0;r<R;r++) for (int c=0;c<C;c++) if (spaces[r][c].shaded && !visited[r][c]) {
			shadedGroupCount++;
			if (shadedGroupCount>1) return false;

			// Visit all the adjacent shaded spaces.
			Stack<Position> stk=new Stack<>();
			stk.push(new Position(r,c));
			visited[r][c]=true;
			while (!stk.isEmpty()) {
				Position curr=stk.pop();
				for (int [] delta: Deltas) {
					int nx=curr.x+delta[0];
					int ny=curr.y+delta[1];
					if (nx>=0 && nx<R && ny>=0 && ny<C && !visited[nx][ny] && spaces[nx][ny].shaded) {
						stk.push(new Position(nx,ny));
						visited[nx][ny]=true;
					}
				}
			}
		}

		return true;
	}

	private static boolean condition2(Space [][] spaces) {
		boolean [][] visited=new boolean [R][C];

		for (int r=0;r<R;r++) for (int c=0;c<C;c++) if (!visited[r][c] && !spaces[r][c].shaded) {
			// Find size
			Stack<Position> stk=new Stack<>();
			stk.push(new Position(r,c));
			visited[r][c]=true;

			int numCount=0;
			int expectedCount=0;
			int count=0;

			while (!stk.isEmpty()) {
				Position curr=stk.pop();
				count++;

				if (spaces[curr.x][curr.y].hasNumber) {
					expectedCount=spaces[curr.x][curr.y].number;
					numCount++;
					if (numCount>1) return false;
				}

				for (int [] delta: Deltas) {
					int nx=curr.x+delta[0];
					int ny=curr.y+delta[1];
					if (nx>=0 && nx<R && ny>=0 && ny<C && !visited[nx][ny] && !spaces[nx][ny].shaded) {
						stk.push(new Position(nx,ny));
						visited[nx][ny]=true;
					}
				}
			}

			if (numCount!=1) return false;
			if (expectedCount!=count) return false;
		}
		return true;
	}

	private static boolean condition3(Space [][] spaces) {
		boolean [][] visited=new boolean [R][C];

		for (int r=0;r<R;r++) for (int c=0;c<C;c++) if (!visited[r][c] && !spaces[r][c].shaded) {
			// Find size
			Stack<Position> stk=new Stack<>();
			stk.push(new Position(r,c));
			visited[r][c]=true;
			boolean reachEdge=false;
			
			while (!stk.isEmpty()) {
				Position curr=stk.pop();
				reachEdge|=curr.x==0 || curr.x==R-1 || curr.y==0 || curr.y==C-1;

				for (int [] delta: Deltas) {
					int nx=curr.x+delta[0];
					int ny=curr.y+delta[1];
					if (nx>=0 && nx<R && ny>=0 && ny<C && !visited[nx][ny] && !spaces[nx][ny].shaded) {
						stk.push(new Position(nx,ny));
						visited[nx][ny]=true;
					}
				}
				for (int [] delta: CornerDeltas) {
					int nx=curr.x+delta[0];
					int ny=curr.y+delta[1];
					if (nx>=0 && nx<R && ny>=0 && ny<C && !visited[nx][ny] && !spaces[nx][ny].shaded) {
						stk.push(new Position(nx,ny));
						visited[nx][ny]=true;
					}
				}
			}

			if (!reachEdge) return false;
		}

		return true;
	}

	private static boolean condition4(Space [][] spaces) {
		for (int r=0;r<R-1;r++) for (int c=0;c<C-1;c++) {
			boolean existsUnshaded=false;
			for (int dr=0;dr<2 && !existsUnshaded;dr++) for (int dc=0;dc<2 && !existsUnshaded;dc++) existsUnshaded|=!spaces[r+dr][c+dc].shaded;
			if (!existsUnshaded) return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		
		for (int tc=0;tc<TC;tc++) {
			br.readLine(); // Empty line.
			StringTokenizer st=new StringTokenizer(br.readLine());
			R=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			int D=Integer.parseInt(st.nextToken());

			Space [][] spaces=new Space[R][C];
			for (int r=0;r<R;r++) for (int c=0;c<C;c++) spaces[r][c]=new Space();
			for (int d=0;d<D;d++) {
				st=new StringTokenizer(br.readLine());
				int r=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				int n=Integer.parseInt(st.nextToken());
				spaces[r][c].hasNumber=true;
				spaces[r][c].number=n;
			}

			for (int r=0;r<R;r++) {
				char [] ch=br.readLine().toCharArray();
				for (int c=0;c<C;c++) if (ch[c]=='#') spaces[r][c].shaded=true;
			}

			if (!condition0(spaces)) {
				System.out.println("not solved");
				continue;
			}
			if (!condition1(spaces)) {
				System.out.println("not solved");
				continue;
			}
			if (!condition4(spaces)) {
				System.out.println("not solved");
				continue;
			}
			if (!condition2(spaces)) {
				System.out.println("not solved");
				continue;
			}
			if (!condition3(spaces)) {
				System.out.println("not solved");
				continue;
			}

			System.out.println("solved");
		}
	}

}
