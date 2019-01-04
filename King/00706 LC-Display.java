import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	/*
	 * 
	 * Encoding :
	 * 
	 *     0
	 *   1   2
	 *     3
	 *   4   5
	 *     6
	 * 
	 */
	public static void main (String [] args) throws Exception {
		int [][] code= {{0,1,2,4,5,6},{2,5},{0,2,3,4,6}, {0,2,3,5,6},{1,2,3,5},{0,1,3,5,6},
						{0,1,3,4,5,6},{0,2,5},{0,1,2,3,4,5,6},{0,1,2,3,5,6}};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int S=Integer.parseInt(st.nextToken());
			String N=st.nextToken();
			int NLength=N.length();
			char [][] c=new char [2*S+3][(S+2)*NLength+(NLength-1)];
			for (char [] zz : c) Arrays.fill(zz, ' '); //!!! Remember to fill in blank char!
			for (int i=0;i<NLength;i++) {
				int col=(S+2)*i+i;
				for (int zz : code[ N.charAt(i)-'0']) draw(c, zz, col, S);
			}
			StringBuilder sb=new StringBuilder();
			for (char [] line : c) {
				sb.append(new String(line));
				sb.append('\n');
			}
			System.out.println(sb.toString());
		}
	}
	
	public static void draw (char [][] c, int num, int startCol, int size) {
		switch (num) {
			case 0:	for (int col=1;col<=size;col++) c[0][startCol+col]='-';
					break;
			case 1: for (int row=1;row<=size;row++) c[row][startCol]='|';
					break;
			case 2:	for (int row=1;row<=size;row++) c[row][startCol+size+1]='|';
					break;
			case 3:	for (int col=1;col<=size;col++) c[size+1][startCol+col]='-';
					break;
			case 4: for (int row=size+2;row<c.length-1;row++) c[row][startCol]='|';
					break;
			case 5: for (int row=size+2;row<c.length-1;row++) c[row][startCol+size+1]='|';
					break;
			case 6: for (int col=1;col<=size;col++) c[c.length-1][startCol+col]='-';
					break;
			case 7: for (int col=1;col<=size;col++) c[c.length-1][startCol+col]='-';
					break;
		}
	}

}