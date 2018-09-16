import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static char [][] rotate(char [][] c) {
		char [][] newC= new char [c.length][c.length];
		for (int row=0;row<c.length;row++) for (int col=0;col<c.length;col++) newC[col][row]=c[c.length-row-1][col];
		return newC;
	}
	
	
	private static int exists(char [][] big, char [][] small) {
		int count=0;
		for (int rowStart=0;rowStart<=big.length-small.length;rowStart++) for (int colStart=0;colStart<=big.length-small.length;colStart++) {
			boolean flag=true;
			for (int i=0;i<small.length && flag;i++) for (int i2=0;i2<small.length && flag;i2++) flag&=(big[rowStart+i][colStart+i2]==small[i][i2]);
			if (flag) count++;
		}
		return count;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int B=Integer.parseInt(st.nextToken()), S=Integer.parseInt(st.nextToken());
			char [][] big=new char [B][1];
			for (int i=0;i<B;i++) big[i]=br.readLine().trim().toCharArray();
			
			char [][] small=new char[S][1];
			for (int i=0;i<S;i++) small[i]=br.readLine().trim().toCharArray();
			
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<4;i++) {
				sb.append(exists(big, small));
				sb.append(" ");
				small=rotate(small);
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}