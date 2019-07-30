import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int curX=0, curY=0;
			
			char [][] screen=new char [10][10];
			for (int i=0;i<10;i++) for (int i2=0;i2<10;i2++) screen[i][i2]=' ';
			
			boolean commandOn=false;
			boolean overwriteMode=true;
			for (int n=0;n<N;n++) {
				char [] chars = br.readLine().toCharArray();
				for (int ci=0;ci<chars.length;ci++) {
					char c=chars[ci];
					if (c=='^' && !commandOn) commandOn=true;
					else if (commandOn && c!='^') {
						if (Character.isDigit(c)) {
							curX=c-'0';
							curY=chars[ci+1]-'0';
							ci++;
						}
						else if (c=='b') curY=0;
						else if (c=='c') for (int i=0;i<10;i++) for (int i2=0;i2<10;i2++) screen[i][i2]=' ';
						else if (c=='d') curX=Math.min(curX+1, 9);
						else if (c=='e') for (int i=curY;i<10;i++) screen[curX][i]=' ';
						else if (c=='h') {
							curX=0; curY=0;
						}
						else if (c=='i') overwriteMode=false;
						else if (c=='l') curY=Math.max(0, curY-1);
						else if (c=='o') overwriteMode=true;
						else if (c=='r') curY=Math.min(9, curY+1);
						else if (c=='u') curX=Math.max(curX-1, 0);
						commandOn=false;
					} else {
						if (!overwriteMode) for (int i=9;i>curY;i--) screen[curX][i]=screen[curX][i-1];
						screen[curX][curY]=c;
						curY=Math.min(curY+1, 9);
						commandOn=false;
					}
				}
			}
			
			StringBuilder ans=new StringBuilder("Case ");
			ans.append(testCase++);
			ans.append('\n');
			ans.append("+----------+\n");
			for (int i=0;i<screen.length;i++) {
				ans.append('|');
				ans.append(new String(screen[i]));
				ans.append("|\n");
			}
			ans.append("+----------+\n");
			System.out.print(ans);
		}
	}

}
