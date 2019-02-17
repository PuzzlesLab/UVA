import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static int [][] Attack;
	public static char [][] Ch;
	
	public static boolean isInBound(int x, int y) { return x>=0 && x<8 && y>=0 && y<8; }
	public static boolean isWhite(int x, int y) { return Ch[x][y]!=0 && Character.isUpperCase(Ch[x][y]);}
	public static boolean isBlack(int x, int y) { return Ch[x][y]!=0 && Character.isUpperCase(Ch[x][y]);}
	public static boolean isEmpty(int x, int y) { return Ch[x][y]==0;}
	
	private static int [][] PawnDeltaBlack= {{1,-1},{1,1}};
	private static int [][] PawnDeltaWhite= {{-1,-1},{-1,1}};
	public static void pawn(boolean black, int x, int y) {
		for (int [] delta : black? PawnDeltaBlack : PawnDeltaWhite) {
			int currX=x+delta[0];
			int currY=y+delta[1];
			if (isInBound(currX, currY)) Attack[currX][currY]++;
		}
	}
	
	private static int [][] RookDelta= {{-1,0},{1,0},{0,-1},{0,1}};
	public static void rook(int x, int y) {
		for (int [] delta : RookDelta) {
			int currX=x+delta[0];
			int currY=y+delta[1];
			while (isInBound(currX,currY)) {
				if (isEmpty(currX,currY)) Attack[currX][currY]++;
				else break;
				currX+=delta[0];
				currY+=delta[1];
			}
		}
	}
	
	private static int [][] KnightDelta= {{2,1},{-2,1},{2,-1},{-2,-1},{1,2},{-1,2},{1,-2},{-1,-2}};
	public static void knight(int x, int y) {
		for (int [] delta : KnightDelta) {
			int currX=x+delta[0];
			int currY=y+delta[1];
			if (isInBound(currX,currY)) Attack[currX][currY]++;
		}
	}
	
	private static int [][] BishopDelta= {{1,1},{-1,1},{1,-1},{-1,-1}};
	public static void bishop(int x, int y) {
		for (int [] delta : BishopDelta) {
			int currX=x+delta[0];
			int currY=y+delta[1];
			while (isInBound(currX,currY)) {
				if (isEmpty(currX,currY)) Attack[currX][currY]++;
				else break;
				currX+=delta[0];
				currY+=delta[1];
			}
		}
	}
	
	public static void queen(int x, int y) {
		rook(x, y);
		bishop(x, y);
	}
	
	public static void king(int x, int y) {
		for (int dx=-1;dx<=1;dx++) for (int dy=-1;dy<=1;dy++) {
			int currX=x+dx;
			int currY=y+dy;
			if (isInBound(currX,currY) && isEmpty(currX,currY))  Attack[currX][currY]++;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s,"/");
			Ch=new char[8][8];
			for (int i=0;i<Ch.length && st.hasMoreTokens();i++) {
				char [] temp=st.nextToken().toCharArray();
				int delta=0;
				for (int i2=0;i2<temp.length;i2++) {
					if (Character.isDigit(temp[i2])) delta+=temp[i2]-'0';
					else Ch[i][delta++]=temp[i2];
				}
			}
			
			Attack=new int [8][8];
			for (int i=0;i<Ch.length;i++) for (int i2=0;i2<Ch.length;i2++) if (Ch[i][i2]!=0) {
				char lowerCase=Character.toLowerCase(Ch[i][i2]);
				switch (lowerCase) {
					case 'p': 	pawn(Character.isLowerCase(Ch[i][i2]), i, i2);
							  	break;
					case 'r': 	rook(i, i2);
								break;
					case 'n': 	knight(i, i2);
					  			break;
					case 'b': 	bishop(i, i2);
					  			break;
					case 'q': 	queen(i, i2);
					  			break;
					case 'k': 	king(i, i2);
					  			break;
				}
			}
			
			int count=0;
			for (int i=0;i<Ch.length;i++) for (int i2=0;i2<Ch.length;i2++) if (Ch[i][i2]==0 && Attack[i][i2]==0) count++;
			System.out.println(count);
			
		}
	}

}
