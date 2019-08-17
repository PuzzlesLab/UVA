import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static String C5 []= {
	    ".***..", "*...*.", "*****.", "*...*.", "*...*.",//A
	    "****..", "*...*.", "****..", "*...*.", "****..",//B
	    ".****.", "*...*.", "*.....", "*.....", ".****.",//C
	    "****..", "*...*.", "*...*.", "*...*.", "****..",//D
	    "*****.", "*.....", "***...", "*.....", "*****.",//E
	    "*****.", "*.....", "***...", "*.....", "*.....",//F
	    ".****.", "*.....", "*..**.", "*...*.", ".***..",//G
	    "*...*.", "*...*.", "*****.", "*...*.", "*...*.",//H
	    "*****.", "..*...", "..*...", "..*...", "*****.",//I
	    "..***.", "...*..", "...*..", "*..*..", ".**...",//J
	    "*...*.", "*..*..", "***...", "*..*..", "*...*.",//K
	    "*.....", "*.....", "*.....", "*.....", "*****.",//L
	    "*...*.", "**.**.", "*.*.*.", "*...*.", "*...*.",//M
	    "*...*.", "**..*.", "*.*.*.", "*..**.", "*...*.",//N
	    ".***..", "*...*.", "*...*.", "*...*.", ".***..",//O
	    "****..", "*...*.", "****..", "*.....", "*.....",//P
	    ".***..", "*...*.", "*...*.", "*..**.", ".****.",//Q
	    "****..", "*...*.", "****..", "*..*..", "*...*.",//R
	    ".****.", "*.....", ".***..", "....*.", "****..",//S
	    "*****.", "*.*.*.", "..*...", "..*...", ".***..",//T
	    "*...*.", "*...*.", "*...*.", "*...*.", ".***..",//U
	    "*...*.", "*...*.", ".*.*..", ".*.*..", "..*...",//V
	    "*...*.", "*...*.", "*.*.*.", "**.**.", "*...*.",//W
	    "*...*.", ".*.*..", "..*...", ".*.*..", "*...*.",//X
	    "*...*.", ".*.*..", "..*...", "..*...", "..*...",//Y
	    "*****.", "...*..", "..*...", ".*....", "*****.",//Z
	};
	
	public static void main (String [] args) throws Exception {
		char [][][] c5Chars=new char [26][5][];
		int counter=0;
		for (int i=0;i<c5Chars.length;i++) for (int i2=0;i2<c5Chars[i].length;i2++) c5Chars[i][i2]=C5[counter++].toCharArray();
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		while ((s=br.readLine())!=null) {
			char [][] page=new char [60][60];
			for (int i=0;i<page.length;i++) Arrays.fill(page[i], '.');
			while (!s.equals(".EOP")) {
				StringTokenizer st=new StringTokenizer(s);
				
				String cmd=st.nextToken();
				String font=st.nextToken();
				int row=Integer.parseInt(st.nextToken())-1;
				int col=0;
				if (cmd.equals(".P")) col=Integer.parseInt(st.nextToken())-1;
				String text=s.split("\\|")[1];
				
				int x=row, y=0;
				if (cmd.equals(".P")) y=col;
				else if (cmd.equals(".L")) y=0;
				else if (cmd.equals(".R")) y=60-(text.length()*(font.equals("C1") ? 1 : 6));
				else if (cmd.equals(".C")) y=30-(text.length()*(font.equals("C1") ? 1 : 6))/2;
				
				for (char c : text.toCharArray()) {
					if (font.equals("C1")) {
						if (c!=' '  && x>=0 && x<page.length && y>=0 && y<page[x].length) page[x][y++]=c;
						else y++;
					} else if (font.equals("C5")) {
						int idx=c-'A';
						if (idx>=0 && idx<26) {
							for (int i=0;i<c5Chars[idx].length;i++) {
								for (int i2=0;i2<c5Chars[idx][i].length;i2++) if (c5Chars[idx][i][i2]=='*') {
									int currX=x+i;
									int currY=y+i2;
									if (currX>=0 && currX<page.length && currY>=0 && currY<page[currX].length) {
										page[currX][currY]=c5Chars[idx][i][i2];
									}
								}
							}
						}
						y+=6;
					}
				}
				s=br.readLine();
			}
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<page.length;i++) {
				sb.append(new String(page[i]));
				sb.append('\n');
			}
			sb.append('\n');
			for (int i=0;i<page.length;i++) sb.append('-');
			sb.append('\n');
			System.out.println(sb.toString());
		}
	}

}