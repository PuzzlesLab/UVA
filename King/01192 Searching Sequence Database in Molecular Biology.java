import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {

	private static ArrayList<String> Seq;
	private static final int NULL=-1000000;
	private static final int SAME=5;
	private static final int DIFF=-4;
	private static final int GAP=-7;
	private static String S1;
	private static String S2;
	private static int [][] Dp;

	private static int count(int i, int i2) {
		if (i==0 && i2==0) return 0;
		
		if (Dp[i][i2]==NULL) {
			int ans=NULL+1;
			if (i==0) ans=GAP+count(i,i2-1);
			else if (i2==0) ans=GAP+count(i-1,i2);
			else {
				ans=Math.max(ans,(S1.charAt(i-1)==S2.charAt(i2-1)?SAME:DIFF)+count(i-1,i2-1));
				ans=Math.max(ans,GAP+count(i,i2-1));
				ans=Math.max(ans,GAP+count(i-1,i2));
			}
			Dp[i][i2]=ans;
		}
		return Dp[i][i2];
	}

	private static void printSolution(boolean first) {
		if (S1==null) return;
		
		StringBuilder sb=new StringBuilder();
		if (first) sb.append('\n');
		sb.append("The query sequence is:\n");
		sb.append(new String(S1));
		sb.append("\n\n");
		sb.append("The most similar sequences are:\n");
		
		int [] sim=new int [Seq.size()];
		int maxSim=-1000000;
		for (int i=0;i<Seq.size();i++) {
			S2=Seq.get(i);
			Dp=new int [S1.length()+1][S2.length()+1];
			for (int i2=0;i2<Dp.length;i2++) Arrays.fill(Dp[i2],NULL);
			sim[i]=count(S1.length(),S2.length());
			maxSim=Math.max(maxSim,sim[i]);
		}
		for (int i=0;i<Seq.size();i++) if (sim[i]==maxSim) {
			sb.append('\n');
			sb.append(Seq.get(i));
			sb.append('\n');
			sb.append("The similarity score is: ");
			sb.append(sim[i]);
			sb.append('\n');
		}

		System.out.print(sb);
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Seq=new ArrayList<>();
		boolean first=true;
		while (true) {
			String s=br.readLine();
			if (s==null || s.equals(">query")) {
				printSolution(first);
				first=false;
				Seq.clear();
				if (s==null) break;
				else {
					S1=br.readLine();
					br.readLine();
					continue;
				}
			}

			Seq.add(br.readLine());
			br.readLine(); // empty
		}
	}
}