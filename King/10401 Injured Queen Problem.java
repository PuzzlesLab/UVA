import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

class Main {

	private static int N;
	private static int [] QueenRowAtCol;
	private static BigInteger [][] Dp;

	private static BigInteger find(int lastRow, int col) {
		if (col==N) return BigInteger.ONE;

		if (Dp[lastRow][col]==null) {
			BigInteger count=BigInteger.ZERO;
			if (QueenRowAtCol[col]==-100) {
				for (int nr=0;nr<N;nr++) if (Math.abs(nr-lastRow)>1) count=count.add(find(nr,col+1));
			}
			else if (Math.abs(QueenRowAtCol[col]-lastRow)>1) count=count.add(find(QueenRowAtCol[col],col+1));
			Dp[lastRow][col]=count;
		}
		return Dp[lastRow][col];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			if (s.isEmpty()) continue;
			char [] state=s.toCharArray();
			N=state.length;

			QueenRowAtCol=new int [state.length];
			Arrays.fill(QueenRowAtCol,-100);
			for (int col=0;col<state.length;col++) {
				if (Character.isDigit(state[col])) QueenRowAtCol[col]=state[col]-'1';
				else if (Character.isAlphabetic(state[col])) QueenRowAtCol[col]=state[col]-'B'+10;
			}

			Dp=new BigInteger [state.length+1][state.length+1];

			BigInteger ans=BigInteger.ZERO;
			if (QueenRowAtCol[0]!=-100) ans=ans.add(find(QueenRowAtCol[0],1));
			else for (int row=0;row<state.length;row++) ans=ans.add(find(row,1));
			System.out.println(ans);
		}
	}

}
