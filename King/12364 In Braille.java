import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

class Main {
	
	private static char [][] codeToChar(String s) {
		char [][] ch=new char [3][2];
		int count=0;
		for (int r=0;r<ch.length;r++) for (int c=0;c<ch[r].length;c++) ch[r][c]=s.charAt(count++);
		return ch;
	}
	
	public static void main (String [] args) throws Exception {
		String [] code= {".***..","*.....","*.*...","**....","**.*..","*..*..","***...","****..","*.**..",".**..."};
		HashMap<String,Character> charToCode=new HashMap<>();
		for (int i=0;i<code.length;i++) charToCode.put(code[i],(char)(i+'0'));
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			char parse=br.readLine().charAt(0);
			if (parse=='S') {
				s=br.readLine();
				char [][][] word=new char [N][][];
				for (int n=0;n<N;n++) word[n]=codeToChar(code[s.charAt(n)-'0']);
				
				char [][] deflate=new char [3][N*3-1];
				for (int i=0;i<3;i++) Arrays.fill(deflate[i],' ');
				for (int n=0;n<N;n++) for (int x=0;x<word[n].length;x++) for (int y=0;y<word[n][x].length;y++) deflate[x][y+n*3]=word[n][x][y];
				
				StringBuilder sb=new StringBuilder();
				for (char [] ch : deflate) {
					sb.append(new String(ch));
					sb.append('\n');
				}
				System.out.print(sb.toString());
			} else if (parse=='B') {
				char [][] ch=new char[3][];
				for (int i=0;i<3;i++) ch[i]=br.readLine().toCharArray();
				
				StringBuilder sb=new StringBuilder();
				for (int n=0;n<N;n++) {
					StringBuilder temp=new StringBuilder();
					for (int r=0;r<3;r++) for (int c=0;c<2;c++) temp.append(ch[r][n*3+c]);
					sb.append(charToCode.get(temp.toString()));
				}
				System.out.println(sb.toString());
			}
		}
	}
}