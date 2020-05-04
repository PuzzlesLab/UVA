import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void compress(int [][] dp, int x1, int y1, int x2, int y2, StringBuilder sb) {
		int currValue=dp[x2][y2];
		if (x1>0) currValue-=dp[x1-1][y2];
		if (y1>0) currValue-=dp[x2][y1-1];
		if (x1>0 && y1>0) currValue+=dp[x1-1][y1-1];
		
		int sizeX=x2-x1+1;
		int sizeY=y2-y1+1;
		int size=sizeX*sizeY;
		
		if (currValue == 0) sb.append(0);
		else if (currValue == size) sb.append(1);
		else if (currValue!=0 && currValue!=size) {
			sb.append('D');
			
			int midX=(x1+x2)/2;
			int midY=(y1+y2)/2;
			
			compress(dp,x1,y1,midX,midY,sb);
			if (midY+1 <= y2) compress(dp,x1,midY+1,midX,y2,sb);
			if (midX+1 <= x2) compress(dp,midX+1,y1,x2,midY,sb);
			if (midX+1 <= x2 && midY+1 <= y2) compress(dp,midX+1,midY+1,x2,y2,sb);
		}
	}
	
	private static int uncompressPos=0;
	public static void uncompress(char [] ch, int [][] map, int x1, int y1, int x2, int y2) {
		if (ch[uncompressPos] == 'D') {
			int midX=(x1+x2)/2;
			int midY=(y1+y2)/2;
			
			uncompressPos++;
			uncompress(ch,map,x1,y1,midX,midY);
			if (midY+1 <= y2) {
				uncompressPos++;
				uncompress(ch,map,x1,midY+1,midX,y2);
			}
			if (midX+1 <= x2) {
				uncompressPos++;
				uncompress(ch,map,midX+1,y1,x2,midY);
			}
			if (midX+1 <= x2 && midY+1 <= y2) {
				uncompressPos++;
				uncompress(ch,map,midX+1,midY+1,x2,y2);
			}
			
		} else if (ch[uncompressPos] == '1') {
			for (int i=x1;i<=x2;i++) for (int i2=y1;i2<=y2;i2++) map[i][i2]=1;
		} else if (ch[uncompressPos] == '0') {
			for (int i=x1;i<=x2;i++) for (int i2=y1;i2<=y2;i2++) map[i][i2]=0;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s=null;

		while (true) {
			if (s==null) s=br.readLine();
			if (s.charAt(0) == '#') break;

			StringTokenizer st=new StringTokenizer(s);
			char type=st.nextToken().charAt(0);
			int row=Integer.parseInt(st.nextToken());
			int col=Integer.parseInt(st.nextToken());
			String ans=null;
			
			StringBuilder problem=new StringBuilder();
			while (true) {
				s=br.readLine();
				if (s.charAt(0) == '#' || s.contains(" ")) break;
				problem.append(s);
			}
			char [] ch=problem.toString().toCharArray();
			
			if (type == 'B') {
				int [][] map=new int[row][col];
				for (int r=0;r<row;r++) for (int c=0;c<col;c++) map[r][c]=ch[r*col+c]-'0';
				
				int [][] dp=new int [row][col];
				for (int r=0;r<row;r++) for (int c=0;c<col;c++) {
					dp[r][c]=map[r][c];
					if (r>0) dp[r][c]+=dp[r-1][c];
					if (c>0) dp[r][c]+=dp[r][c-1];
					if (r>0 && c>0) dp[r][c]-=dp[r-1][c-1];
				}
				
				StringBuilder a=new StringBuilder();
				compress(dp, 0, 0, row-1, col-1, a);
				ans=a.toString();
			} else if (type == 'D') {
				int [][] map=new int [row][col];
				uncompressPos=0;
				uncompress(ch,map,0,0,row-1,col-1);
				
				StringBuilder a=new StringBuilder();
				for (int r=0;r<row;r++) for (int c=0;c<col;c++) a.append(map[r][c]);
				ans=a.toString();
			}
			
			StringBuilder formatAns=new StringBuilder();
			for (int i=0;i*50<ans.length();i++) {
				formatAns.append(ans.substring(i*50,Math.min(ans.length(),(i+1)*50)));
				formatAns.append('\n');
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append(type == 'B' ? 'D' : 'B');
			sb.append(String.format("%4d", row));
			sb.append(String.format("%4d", col));
			sb.append('\n');
			sb.append(formatAns);
			System.out.print(sb.toString());
		}
	}

}