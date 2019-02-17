import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static char [][] board;
	
	public static boolean pawn(boolean up, char k, int x, int y) {
		if (up && x>0) return ((y>0 && board[x-1][y-1]==k) || (y<7 && board[x-1][y+1]==k));
		else if (!up && x<7) return ((y>0 && board[x+1][y-1]==k) || (y<7 && board[x+1][y+1]==k));
		return false;
	}
	
	public static boolean rook(char k, int x, int y) {
		int [][] deltas= {{-1,0},{1,0},{0,-1},{0,1}};
		for (int [] delta : deltas) {
			int tempx=x+delta[0];
			int tempy=y+delta[1];
			while (tempx>=0 && tempy>=0 && tempx<8 && tempy<8) {
				if (board[tempx][tempy]==k) return true;
				else if (board[tempx][tempy]!='.') break;
				tempx+=delta[0];
				tempy+=delta[1];
			}
		}
		return false;
	}
	
	public static boolean bishop(char k, int x, int y) {
		int [][] deltas= {{-1,-1},{1,1},{1,-1},{-1,1}};
		for (int [] delta : deltas) {
			int tempx=x+delta[0];
			int tempy=y+delta[1];
			while (tempx>=0 && tempy>=0 && tempx<8 && tempy<8) {
				if (board[tempx][tempy]==k) return true;
				else if (board[tempx][tempy]!='.') break;
				tempx+=delta[0];
				tempy+=delta[1];
			}
		}
		return false;
	}
	
	public static boolean queen(char k, int x, int y) {
		return rook(k,x,y) || bishop(k,x,y);
	}
	
	public static boolean king(char k, int x, int y) {
		for (int i=Math.max(0,x-1);i<=Math.min(7,x+1);i++) for (int i2=Math.max(0,y-1);i2<=Math.min(7,y+1);i2++) if (board[i][i2]==k) return true;
		return false;
	}
	
	public static boolean knight(char k, int x, int y) {
		int [][] deltas= {{-1,-2},{-1,2},{-2,1},{-2,-1},{1,-2},{1,2},{2,1},{2,-1}};
		for (int [] delta : deltas) {
			int tempx=x+delta[0];
			int tempy=y+delta[1];
			if (tempx>=0 && tempy>=0 && tempx<8 && tempy<8 && board[tempx][tempy]==k) return true;
		}
		return false;
	}
	
	public static boolean dispatch(int x, int y) {
		switch (board[x][y]) {
			case 'P': return pawn(true,'k',x,y);
			case 'p': return pawn(false,'K',x,y);
			case 'R': return rook('k',x,y);
			case 'r': return rook('K',x,y);
			case 'B': return bishop('k',x,y);
			case 'b': return bishop('K',x,y);
			case 'Q': return queen('k',x,y);
			case 'q': return queen('K',x,y);
			case 'K': return king('k',x,y);
			case 'k': return king('K',x,y);
			case 'N': return knight('k',x,y);
			case 'n': return knight('K',x,y);
		}
		return false;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		while (true) {
			board=new char [8][];
			for (int i=0;i<8;i++) board[i]=br.readLine().toCharArray();
			boolean quit=true;
			for (int i=0;i<8 && quit;i++) for (int i2=0;i2<8 && quit;i2++) quit=board[i][i2]=='.';
			if (quit) break;
			boolean whiteCheck=false;
			for (int i=0;i<8 && !whiteCheck;i++) for (int i2=0;i2<8 && !whiteCheck;i2++) {
				if (board[i][i2]!='.' && board[i][i2]>='a') whiteCheck=dispatch(i,i2);
			}

			boolean blackCheck=false;
			if (!whiteCheck) {
				for (int i=0;i<8 && !blackCheck;i++) for (int i2=0;i2<8 && !blackCheck;i2++)
					if (board[i][i2]!='.' && board[i][i2]<='Z') blackCheck=dispatch(i,i2);
			}

			String text="no";
			if (whiteCheck) text="white";
			else if (blackCheck) text="black";
			System.out.println(String.format("Game #%d: %s king is in check.", testCase++, text));
			br.readLine();
		}
	}

}