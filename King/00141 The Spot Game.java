import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	
	private static String boardHash(boolean [][] board) {
		StringBuilder sb=new StringBuilder();
		for (int x=0;x<board.length;x++) for (int y=0;y<board.length;y++) sb.append(board[x][y] ? '1' : '0');
		return sb.toString();
	}
	
	private static boolean[][] rotateBoard(boolean [][] board) {
		boolean [][] boardNew=new boolean [board.length][board.length];
		for (int x=0;x<board.length;x++) for (int y=0;y<board.length;y++) boardNew[x][y]=board[board.length-1-y][x];
		return boardNew;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int winner=-1, step=-1;
			
			boolean [][] board=new boolean[N][N];
			HashSet<String> boardHashes=new HashSet<>();
			for (int i=1;i<=2*N;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken())-1;
				int y=Integer.parseInt(st.nextToken())-1;
				char op=st.nextToken().charAt(0);
				
				if (winner==-1) {
					board[x][y]=op=='+';
					boolean flag=false;
					HashSet<String> testHash=new HashSet<>();
					for (int r=0;r<4;r++) {
						board = rotateBoard(board);
						String hash=boardHash(board);
						testHash.add(hash);
						flag |= (boardHashes.contains(hash));
					}
					if (flag) {
						winner=i%2;
						step=i;
					} else boardHashes.addAll(testHash);
				}
				
			}
			if (winner==-1) System.out.println("Draw");
			else System.out.printf("Player %d wins on move %d\n", winner+1, step);
		}
	}

}