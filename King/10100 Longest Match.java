import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {

	private static final int NULL=-100000;
	private static ArrayList<String> Line1;
	private static ArrayList<String> Line2;
	private static int [][] Dp;

	private static ArrayList<String> tokenize(String s) {
		ArrayList<String> list=new ArrayList<>();
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			if (Character.isAlphabetic(c) || Character.isDigit(c)) sb.append(c);
			else if (sb.length()>0) {
				list.add(sb.toString());
				sb.setLength(0);
			}
		}
		if (sb.length()>0) list.add(sb.toString());
		return list;
	}
	
	private static int find(int i, int i2) {
		if (i==0 || i2==0) return 0;
		if (Dp[i][i2]==NULL) {
			int ans=NULL+1;
			if (Line1.get(i-1).equals(Line2.get(i2-1))) ans=Math.max(ans,1+find(i-1,i2-1));
			ans=Math.max(ans,find(i-1,i2));
			ans=Math.max(ans,find(i,i2-1));
			Dp[i][i2]=ans;
		}
		return Dp[i][i2];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while ((s=br.readLine())!=null) {
			Line1=tokenize(s);
			Line2=tokenize(br.readLine());
			
			Dp=new int [Line1.size()+1][Line2.size()+1];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],NULL);
			int match=find(Line1.size(),Line2.size());
			
			StringBuilder sb=new StringBuilder();
			sb.append(String.format("%2d",tc++));
			sb.append(". ");
			if (Line1.isEmpty() || Line2.isEmpty()) sb.append("Blank!");
			else {
				sb.append("Length of longest match: ");
				sb.append(match);
			}
			System.out.println(sb.toString());
		}
	}
}