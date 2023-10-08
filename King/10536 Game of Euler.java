import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int DIM=4;
	private static final int DIM2=DIM*DIM;
	private static int [] Dp=new int [1<<DIM2];
	// 0=undefined, 1=true, 2=false

	private static boolean gameEnded(int game) {
		return game==(1<<DIM2)-1;
	}

	private static boolean isSet(int game, int row, int col) {
		int key=row*DIM+col;
		return (game&(1<<key))!=0;
	}
	
	private static int setBit(int game, int row, int col) {
		int key=row*DIM+col;
		return game|(1<<key);
	}

	private static boolean play(int game) {
		if (Dp[game]==0) {
			// Place left;
			for (int row=0;row<DIM;row++) {
				int maxLen=0;
				for (int col=0;col<DIM;col++) {
					if (!isSet(game,row,col)) maxLen++;
					else break;
					if (maxLen==3) break;
				}

				for (int len=1;len<=maxLen;len++) {
					int nGame=game;
					for (int fill=0;fill<len;fill++) nGame=setBit(nGame,row,fill);
					if (!gameEnded(nGame) && !play(nGame)) {
						Dp[game]=1;
						return true;
					}
				}
			}
	
			// Place right
			for (int row=0;row<DIM;row++) {
				int maxLen=0;
				for (int col=DIM-1;col>=0;col--) {
					if (!isSet(game,row,col)) maxLen++;
					else break;
					if (maxLen==3) break;
				}
	
				for (int len=1;len<=maxLen;len++) {
					int nGame=game;
					for (int fill=0;fill<len;fill++) nGame=setBit(nGame,row,DIM-1-fill);
					if (!gameEnded(nGame) && !play(nGame)) {
						Dp[game]=1;
						return true;
					}
				}
			}
	
			// Place top
			for (int col=0;col<DIM;col++) {
				int maxLen=0;
				for (int row=0;row<DIM;row++) {
					if (!isSet(game,row,col)) maxLen++;
					else break;
					if (maxLen==3) break;
				}
				
				for (int len=1;len<=maxLen;len++) {
					int nGame=game;
					for (int fill=0;fill<len;fill++) nGame=setBit(nGame,fill,col);
					if (!gameEnded(nGame) && !play(nGame)) {
						Dp[game]=1;
						return true;
					}
				}
			}

			// Place bottom
			for (int col=0;col<DIM;col++) {
				int maxLen=0;
				for (int row=DIM-1;row>=0;row--) {
					if (!isSet(game,row,col)) maxLen++;
					else break;
					if (maxLen==3) break;
				}
				
				for (int len=1;len<=maxLen;len++) {
					int nGame=game;
					for (int fill=0;fill<len;fill++) nGame=setBit(nGame,DIM-1-fill,col);
					if (!gameEnded(nGame) && !play(nGame)) {
						Dp[game]=1;
						return true;
					}
				}
			}
	
			// Place center
			for (int row=1;row<DIM-1;row++) for (int col=1;col<DIM-1;col++) if (!isSet(game,row,col)) {
				int nGame=setBit(game,row,col);
				if (!gameEnded(nGame) && !play(nGame)) {
					Dp[game]=1;
					return true;
				}
			}

			Dp[game]=2;
		}

		return Dp[game]==1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine(); // empty;

			int game=0;
			for (int i=0;i<DIM;i++) {
				char [] line=br.readLine().toCharArray();
				for (int i2=0;i2<DIM;i2++) game=(game<<1)+(line[i2]=='X'?1:0);
			}

			System.out.println(play(game)?"WINNING":"LOSING");
		}
	}

}