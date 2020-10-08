import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			char [][] ch=new char [M*N][M*N*2];
			for (int i=0;i<ch.length;i++) Arrays.fill(ch[i],' ');
			int currColumnPadding=M*N-1;
			int currRow=0;
			
			for (int n=0;n<N;n++) {
				for (int m=0;m<M;m++) {
					int currCol=currColumnPadding;
					int start=currCol;
					int end=ch[currRow].length-currCol-1;
					
					if (m==M-1) for (int i=start+1;i<end;i++) ch[currRow][i]='_';
					int temp=start;
					for (int i=0;i<=n;i++) {
						ch[currRow][temp]='/';
						temp+=(M+M);
					}
					temp=end;
					for (int i=0;i<=n;i++) {
						ch[currRow][temp]='\\';
						temp-=(M+M);
					}
					currColumnPadding--;
					currRow++;
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Triangular Museum ");
			sb.append(testCase++);
			sb.append('\n');
			for (char [] c : ch) {
				sb.append(new String(c));
				while (sb.charAt(sb.length()-1)==' ') sb.setLength(sb.length()-1);
				sb.append('\n');
			}
			System.out.println(sb.toString());
		}
	}
}