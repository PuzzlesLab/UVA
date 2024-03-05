import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<String> Left;
	private static ArrayList<String> Right;
	private static int [][] Dp;
	private static final int NULL=-100000;
	
	private static int lcs(int l, int r) {
		if (l==0 || r==0) return 0;
		if (Dp[l][r]==NULL) {
			int count=0;
			count=Math.max(count,lcs(l-1,r));
			count=Math.max(count,lcs(l,r-1));
			if (Left.get(l-1).equals(Right.get(r-1))) count=Math.max(count,1+lcs(l-1,r-1));
			Dp[l][r]=count;
		}
		return Dp[l][r];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			Left=new ArrayList<>();
			Right=new ArrayList<>();
			
			do {
				StringTokenizer st=new StringTokenizer(s);
				while (st.hasMoreTokens()) Left.add(st.nextToken());
				s=br.readLine();
			} while (!s.equals("#"));
			do {
				StringTokenizer st=new StringTokenizer(s);
				while (st.hasMoreTokens()) Right.add(st.nextToken());
				s=br.readLine();
			} while (!s.equals("#"));
			
			Dp=new int [Left.size()+1][Right.size()+1];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],NULL);

			lcs(Dp.length-1,Dp[0].length-1);

			int l=Dp.length-1;
			int r=Dp[0].length-1;
			Stack<String> stk=new Stack<>();
			while (l>0 && r>0) {
				if (Left.get(l-1).equals(Right.get(r-1))) {
					stk.push(Left.get(l-1));
					l--;
					r--;
				}
				else if (Dp[l-1][r]>Dp[l][r-1]) l--;
				else r--;
			}
			StringBuilder sb=new StringBuilder();
			while (!stk.isEmpty()) {
				sb.append(stk.pop());
				sb.append(' ');
			}
			if (sb.length()>0) sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}
}