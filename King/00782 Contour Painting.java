import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class Main {
	
	private static int ax, ay=0;
	public static void findAsterisk(char [][] ch) {
		ax=-1;
		ay=-1;
		for (int i=0;i<ch.length && ax==-1;i++) for (int i2=0;i2<ch[i].length && ax==-1;i2++) if (ch[i][i2]=='*') {
			ax=i;
			ay=i2;
		}
	}

	public static int [] deltas= {-1,1};
	
	public static boolean [][] markArea(char [][] ch, int x, int y) {
		boolean [][] visited=new boolean [ch.length][];
		for (int i=0;i<ch.length;i++) visited[i]=new boolean [ch[i].length];
		Stack<Integer> stkX=new Stack<>();
		Stack<Integer> stkY=new Stack<>();
		stkX.push(x);
		stkY.push(y);
		visited[x][y]=true;
		while (!stkX.isEmpty()) {
			int cx=stkX.pop();
			int cy=stkY.pop();
			for (int d : deltas) {
				if (cx+d>=0 && cx+d<ch.length && cy<ch[cx+d].length && ch[cx+d][cy]==' ' && !visited[cx+d][cy]) {
					visited[cx+d][cy]=true;
					stkX.push(cx+d);
					stkY.push(cy);
				}
				if (cy+d>=0 && cy+d<ch[cx].length && ch[cx][cy+d]==' ' && !visited[cx][cy+d]) {
					visited[cx][cy+d]=true;
					stkX.push(cx);
					stkY.push(cy+d);
				}
			}
		}
		return visited;
	}
	
	public static char [][] fillContour(char [][] ch, boolean [][] area) {
		char [][] filled=new char [ch.length][];
		for (int i=0;i<ch.length;i++) filled[i]=Arrays.copyOf(ch[i], ch[i].length);
		
		for (int row=0;row<ch.length;row++) for (int col=0;col<ch[row].length;col++) if (area[row][col]) {
			boolean isBound=false;
			for (int d : deltas) {
				isBound |= (row+d>=0 && row+d<ch.length && col<ch[row+d].length && ch[row+d][col]=='X');
				isBound |= (col+d>=0 && col+d<ch[row].length && ch[row][col+d]=='X');
				if (isBound) break;
			}
			if (isBound) filled[row][col]='#';
		}
		
		return filled;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			ArrayList<String> list=new ArrayList<>();
			
			String s;
			while (true) {
				s=br.readLine();
				if (s.trim().length()>0 && s.trim().charAt(0)=='_') break;
				list.add(s);
			}
			
			char [][] ch=new char[list.size()][];
			for (int i=0;i<ch.length;i++) {
				StringBuilder zz=new StringBuilder(list.get(i).toString());
				while (zz.length()<81) zz.append(' ');
				ch[i]=zz.toString().toCharArray();
			}
			
			findAsterisk(ch);
			ch[ax][ay]=' ';
			char [][] ans=fillContour(ch, markArea(ch, ax, ay));
			
			StringBuilder sb=new StringBuilder();

			for (int i=0;i<ans.length;i++) {
				sb.append(new String(ans[i]));
				while (sb.length()>0 && sb.charAt(sb.length()-1)==' ') sb.setLength(sb.length()-1);
				sb.append('\n');
			}
			System.out.print(sb.toString());
			System.out.println(s);
		}
	}

}