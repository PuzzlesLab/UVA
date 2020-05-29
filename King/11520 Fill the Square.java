import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	private static int [][] delta={{-1,0},{1,0},{0,-1},{0,1}};
	private static boolean canPutChar(char [][] ch, char c, int x, int y) {
		for (int [] d : delta) if (x+d[0]>=0 && x+d[0]<ch.length && y+d[1]>=0 && y+d[1]<ch.length && ch[x+d[0]][y+d[1]]==c) return false;
		return true;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			char [][] ch=new char [N][];
			for (int n=0;n<N;n++) ch[n]=br.readLine().toCharArray();
			
			for (int i=0;i<N;i++) for (int i2=0;i2<N;i2++) if (ch[i][i2]=='.') {
				for (int ci='A';ci<='Z';ci++) if (canPutChar(ch,(char)ci,i,i2)) {
					ch[i][i2]=(char)ci;
					break;
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(":\n");
			for (int i=0;i<N;i++) {
				sb.append(new String(ch[i]));
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}