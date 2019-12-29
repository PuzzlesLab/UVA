import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
	
	private static int [][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	private static void coloring(char [][] game, int [][] color, int x, int y, int colorN) {
		for (int [] delta : deltas) {
			int nx=x+delta[0], ny=y+delta[1];
			if (nx>=0 && nx<9 && ny>=0 && ny<9 && game[nx][ny]=='.' && color[nx][ny]==-1) {
				color[nx][ny]=colorN;
				coloring(game,color,nx,ny,colorN);
			}
		}
	}
	
	
	private static boolean adjBlack, adjWhite;
	private static void determineOwner(char [][] game, int [][] color, int x, int y, int colorN, boolean [][] visited) {
		if (!visited[x][y]) {
			visited[x][y]=true;
			for (int [] delta : deltas) {
				int nx=x+delta[0], ny=y+delta[1];
				if (nx>=0 && nx<9 && ny>=0 && ny<9) {
					adjBlack |= game[nx][ny]=='X';
					adjWhite |= game[nx][ny]=='O';
					
					if (color[nx][ny]==colorN) determineOwner(game,color,nx,ny,colorN,visited);
				}
			}
		}
	}
	
	private static void fill(char [][] game, int [][] color, int x, int y, char ch) {
		for (int [] delta : deltas) {
			int nx=x+delta[0], ny=y+delta[1];
			if (nx>=0 && nx<9 && ny>=0 && ny<9 && color[nx][ny]==color[x][y] && game[nx][ny]=='.') {
				game[nx][ny]=ch;
				fill(game,color,nx,ny,ch);
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			char [][] game=new char [9][];
			for (int i=0;i<game.length;i++) game[i]=br.readLine().toCharArray();
			

			int [][] color=new int [9][9];
			for (int i=0;i<9;i++) Arrays.fill(color[i], -1);
			int colorMax=0;
			ArrayList<int []> colorKeyCell=new ArrayList<>();
			for (int i=0;i<9;i++) for (int i2=0;i2<9;i2++) if (game[i][i2]=='.' && color[i][i2]==-1) {
				color[i][i2]=colorMax;
				coloring(game, color, i, i2, colorMax++);
				colorKeyCell.add(new int []{i, i2});
			}
			
			for (int i=0;i<colorMax;i++) {
				int [] coor=colorKeyCell.get(i);
				adjBlack=false;
				adjWhite=false;
				boolean [][] visited=new boolean[9][9];
				determineOwner(game,color,coor[0],coor[1],i,visited);
				if (adjBlack ^ adjWhite) {
					game[coor[0]][coor[1]]=adjBlack ? 'X' : 'O';
					fill(game,color,coor[0],coor[1],game[coor[0]][coor[1]]);
				}
			}
			
			int [] score=new int [2];//0=black, 1=white;
			for (int i=0;i<9;i++) for (int i2=0;i2<9;i2++) if (game[i][i2]!='.') score[game[i][i2]=='X' ? 0 : 1]++;
			System.out.printf("Black %d White %d\n", score[0], score[1]);
		}
	}

}