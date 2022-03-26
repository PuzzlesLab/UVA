import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Solution {
		int sy, ey=Integer.MAX_VALUE;
	}
	private static Solution solution=null;
	private static int [][] deltas = {{0,1},{0,-1},{1,0},{-1,0}};
	
	private static void find(int [][] maze, boolean [][] visited, int cx, int cy, int expectedNum, int maxNum, int count) {
		if (cx==maze.length-1) {
			if (solution==null) solution=new Solution();
			solution.ey=Math.min(solution.ey, cy);
		} else {
			for (int [] delta: deltas) {
				int nx=cx+delta[0];
				int ny=cy+delta[1];
				if (nx>=0 && nx<maze.length && ny>=0 && ny<maze[nx].length && !visited[nx][ny] && maze[nx][ny]==expectedNum) {
					visited[nx][ny]=true;
					if (expectedNum==maxNum) find(maze,visited,nx,ny,1,maxNum+1,count+1);
					else find(maze,visited,nx,ny,expectedNum+1,maxNum,count+1);
					visited[nx][ny]=false;
				}
			}
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine(); //empty
			StringTokenizer st=new StringTokenizer(br.readLine());
			int X=Integer.parseInt(st.nextToken());
			int Y=Integer.parseInt(st.nextToken());
			
			int [][] maze=new int [X][Y];
			for (int x=0;x<X;x++) {
				st=new StringTokenizer(br.readLine());
				for (int y=0;y<Y;y++) maze[x][y]=Integer.parseInt(st.nextToken());
			}
			
			solution=null;
			for (int y=0;y<Y;y++) if (maze[0][y]==1 && solution==null) {
				boolean [][] visited=new boolean[X][Y];
				visited[0][y]=true;
				find(maze,visited,0,y,1,2,1);
				visited[0][y]=false;
				
				if (solution!=null) solution.sy=y;
			}
			
			if (testCase>0) System.out.println();
			if (solution!=null) System.out.printf("%d %d\n%d %d\n",1,solution.sy+1,X,solution.ey+1);
		}
	}

}
