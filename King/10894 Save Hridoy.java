import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static String PrintS="*****\n*....\n*****\n....*\n*****";
	private static String PrintA=".***.\n*...*\n*****\n*...*\n*...*";
	private static String PrintV="*...*\n*...*\n*...*\n.*.*.\n..*..";
	private static String PrintE="*****\n*....\n***..\n*....\n*****";
	private static String PrintSpace=".";
	private static String PrintH="*...*\n*...*\n*****\n*...*\n*...*";
	private static String PrintR="*****\n*...*\n*****\n*.*..\n*..**";
	private static String PrintI="*****\n..*..\n..*..\n..*..\n*****";
	private static String PrintD="***..\n*..*.\n*...*\n*..*.\n***..";
	private static String PrintO="*****\n*...*\n*...*\n*...*\n*****";
	private static String PrintY="*...*\n.*.*.\n..*..\n..*..\n..*..";
	
	private static char [][] extrapolate(String s, int size) {
		int absSize=Math.abs(size);
		if (s.equals(PrintSpace)) {
			char [][] result=null;

			if (size<0) {
				result=new char [absSize][absSize*5];
				for (int i=0;i<result.length;i++) Arrays.fill(result[i], '.');
			} else if (size>0) {
				result=new char [absSize*5][absSize];
				for (int i=0;i<result.length;i++) Arrays.fill(result[i], '.');
			}
			return result;
		} else {
			StringTokenizer st=new StringTokenizer(s);
			char [][] base=new char [5][];
			for (int i=0;i<base.length;i++) base[i]=st.nextToken().toCharArray();
			char [][] result=new char [5*absSize][base[0].length*absSize];
			for (int bRow=0;bRow<base.length;bRow++) for (int bCol=0;bCol<base[bRow].length;bCol++) {
				for (int rRow=0;rRow<absSize;rRow++) for (int rCol=0;rCol<absSize;rCol++) result[bRow*absSize+rRow][bCol*absSize+rCol]=base[bRow][bCol];
			}
			return result;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int size=Integer.parseInt(s);
			int absSize=Math.abs(size);
			char [][][] word=new char [11][][];
			word[0]=extrapolate(PrintS, size);
			word[1]=extrapolate(PrintA, size);
			word[2]=extrapolate(PrintV, size);
			word[3]=extrapolate(PrintE, size);
			word[4]=extrapolate(PrintSpace, size);
			word[5]=extrapolate(PrintH, size);
			word[6]=extrapolate(PrintR, size);
			word[7]=extrapolate(PrintI, size);
			word[8]=extrapolate(PrintD, size);
			word[9]=extrapolate(PrintO, size);
			word[10]=extrapolate(PrintY, size);
			
			StringBuilder sb=new StringBuilder();
			if (size<0) {
				int rowCount=-absSize;
				for (char [][] c: word) rowCount+=c.length+absSize;
				
				char [][] result=new char [rowCount][5*absSize];
				for (int row=0;row<result.length;row++) Arrays.fill(result[row], '.');
				
				int currRow=0;
				for (char [][] c : word) {
					for (int row=0;row<c.length;row++) for (int col=0;col<c[row].length;col++) result[currRow+row][col]=c[row][col];
					currRow+=c.length+absSize;
				}
				
				for (int row=0;row<result.length;row++) {
					sb.append(new String(result[row]));
					sb.append('\n');
				}
			} else {
				int colCount=-absSize;
				for (char [][] c: word) colCount+=c[0].length+absSize;
				
				char [][] result=new char [5*absSize][colCount];
				for (int row=0;row<result.length;row++) Arrays.fill(result[row], '.');
				
				int currCol=0;
				for (char [][] c : word) {
					for (int row=0;row<c.length;row++) for (int col=0;col<c[row].length;col++) result[row][currCol+col]=c[row][col];
					currCol+=c[0].length+absSize;
				}
				
				for (int row=0;row<result.length;row++) {
					sb.append(new String(result[row]));
					sb.append('\n');
				}
			}
			
			sb.append('\n');
			System.out.println(sb.toString());
		}
	}
}