import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static String [][] Dp;
	private static char [][] Road;

	private static String find(int i, int i2) {
		if (i>i2) return null; // We don't want to go back to previous node.
		if (i==i2) return "";
		if (Dp[i][i2]==null) {
			if (Road[i][i2]!='*') Dp[i][i2]=String.valueOf(Road[i][i2]);
			for (int n=i+1;n<Dp.length;n++) if (Road[i][n]!='*') {
				for (int n2=i2-1;n2>=0;n2--) if (Road[n2][i2]!='*' && Road[i][n]==Road[n2][i2]) {
					String now=find(n,n2);
					if (now==null) continue;

					StringBuilder sb=new StringBuilder();
					sb.append(Road[i][n]);
					sb.append(now); // n->n2;
					sb.append(Road[n2][i2]);

					String s=sb.toString();
					int currLen=(Dp[i][i2]==null)?0:Dp[i][i2].length();
					if (s.length()>currLen) Dp[i][i2]=s;
					else if (s.length()==currLen && s.compareTo(Dp[i][i2])<0) Dp[i][i2]=s;
				}
			}
		}
		return Dp[i][i2];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			Road=new char[N][N];
			Dp=new String[N][N];
			
			for (int n=0;n<N;n++) {
				String s=br.readLine();
				for (int n2=0;n2<N;n2++) Road[n][n2]=s.charAt(n2);
			}
			
			String ans=find(0,N-1);
			if (ans==null) ans="NO PALINDROMIC PATH";
			System.out.println(ans);
		}
	}
}