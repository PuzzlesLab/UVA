import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static boolean dfs (char [][] ch, int x, int y, int currTarget, char [] target, String [] op) {
		if (currTarget==target.length) {
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<op.length;i++) {
				sb.append(op[i]);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
			return true;
		} else {
			boolean flag=false;
			if (!flag && y>0 && ch[x][y-1]==target[currTarget]) {
				op[currTarget]="left";
				flag=dfs(ch,x,y-1,currTarget+1,target,op);
			}
			if (!flag && y<ch[x].length-1 && ch[x][y+1]==target[currTarget]) {
				op[currTarget]="right";
				flag=dfs(ch,x,y+1,currTarget+1,target,op);
			}
			if (!flag && x>0 && ch[x-1][y]==target[currTarget]) {
				op[currTarget]="forth";
				flag=dfs(ch,x-1,y,currTarget+1,target,op);
			}
			return flag;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		char [] target= {'I', 'E', 'H', 'O', 'V', 'A', '#'};
		
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int row=Integer.parseInt(st.nextToken());
			int col=Integer.parseInt(st.nextToken());
			
			char [][] ch=new char [row][];
			for (int r=0;r<row;r++) ch[r]=br.readLine().toCharArray();

			int sx=0, sy=0;
			for (int r=0;r<row;r++) for (int c=0;c<col;c++) if (ch[r][c]=='@') {
				sx=r;
				sy=c;
				break;
			}
			
			dfs(ch,sx,sy,0,target,new String [7]);
		}
	}

}
 