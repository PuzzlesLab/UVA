import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	private static int XWin=0;
	private static int OWin=0;
	
	public static void detectWinner(char [][] game) {
		XWin=0;
		OWin=0;
		for (int row=0;row<3;row++) if (game[row][0]!='.') {
			boolean same=true;
			for (int col=1;col<3;col++) same&=(game[row][0]==game[row][col]);
			if (same) if (game[row][0]=='X') XWin++; else OWin++;
		}
		
		for (int col=0;col<3;col++) if (game[0][col]!='.') {
			boolean same=true;
			for (int row=1;row<3;row++) same&=(game[0][col]==game[row][col]);
			if (same) if (game[0][col]=='X') XWin++; else OWin++;
		}
		
		if (game[0][0]!='.' && game[0][0]==game[1][1] && game[1][1]==game[2][2]) if (game[0][0]=='X') XWin++; else OWin++;
		
		if (game[0][2]!='.' && game[0][2]==game[1][1] && game[1][1]==game[2][0]) if (game[0][2]=='X') XWin++; else OWin++;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			char [][] game=new char [3][];
			for (int i=0;i<3;i++) game[i]=br.readLine().toCharArray();
			int Xcount=0, Ocount=0;
			for (int i=0;i<3;i++) for (int i2=0;i2<3;i2++) 
				if (game[i][i2]=='X') Xcount++; else if (game[i][i2]=='O') Ocount++;
			
			boolean valid=false;
			if (Xcount-Ocount==0 || Xcount-Ocount==1) {
				detectWinner(game);
				if (XWin>0 || OWin>0) {
					if (XWin>0 ^ OWin>0) {
						if (XWin>0) valid=(Xcount-Ocount==1);
						else if (OWin>0) valid=(Xcount==Ocount);
					} else valid=false;
				} else valid=true;
			}
			System.out.println(valid ? "yes" : "no");
			br.readLine();
		}
	}

}