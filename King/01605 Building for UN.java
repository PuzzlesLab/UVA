import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		char [] c=new char [52];
		for (int i=0;i<26;i++) c[i]=(char)('A'+i);
		for (int i=0;i<26;i++) c[i+26]=(char)('a'+i);
		while ((s=br.readLine())!=null) {
			int n=Integer.parseInt(s);
			char [][][] ans=new char [2][n][n];
			for (int i=0;i<n;i++) for (int i2=0;i2<n;i2++) ans[0][i][i2]=c[i];
			for (int i=0;i<n;i++) for (int i2=0;i2<n;i2++) ans[1][i][i2]=c[i2];
			
			StringBuilder sb=new StringBuilder();
			sb.append(2);
			sb.append(' ');
			sb.append(n);
			sb.append(' ');
			sb.append(n);
			sb.append('\n');
			
			for (int i=0;i<n;i++) {
				sb.append(ans[0][i]);
				sb.append('\n');
			}
			sb.append('\n');
			for (int i=0;i<n;i++) {
				sb.append(ans[1][i]);
				sb.append('\n');
			}
			
			System.out.print(sb.toString());
		}
	}
}