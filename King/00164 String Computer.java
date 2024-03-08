import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int NULL=100000;
	private static final int KEEP=1;
	private static final int CHANGE=2;
	private static final int INSERT=3;
	private static final int DELETE=4;
	private static char [] Left;
	private static char [] Right;
	private static int [][] Dp;
	private static int [][] Action;


	private static int find(int l, int r) {
		if (l==0 && r==0) return 0;

		if (Dp[l][r]==NULL) {
			int ans=NULL-1;
			if (l==0) {
				Action[l][r]=INSERT;
				ans=1+find(l,r-1);
			} else if (r==0) {
				Action[l][r]=DELETE;
				ans=1+find(l-1,r);
			} else {
				int [] values={(Left[l-1]==Right[r-1]?0:1)+find(l-1,r-1),1+find(l,r-1),1+find(l-1,r)};
				int minIdx=0;
				for (int i=0;i<values.length;i++) if (values[i]<=values[minIdx]) minIdx=i;
				ans=values[minIdx];
				Action[l][r]=minIdx+2;
				if (Left[l-1]==Right[r-1] && Action[l][r]==2) Action[l][r]=1;
			}

			Dp[l][r]=ans;
		}
		return Dp[l][r];
	}

	private static void addPrint(ArrayList<String> ans, int l, int r) {
		if (l==0 && r==0) return;

		if (Action[l][r]==KEEP) addPrint(ans,l-1,r-1);
		else if (Action[l][r]==CHANGE) {
			addPrint(ans,l-1,r-1);
			ans.add(String.format("C%s%02d",Right[r-1],r));
		} else if (Action[l][r]==INSERT) {
			addPrint(ans,l,r-1);
			ans.add(String.format("I%s%02d",Right[r-1],r));
		} else if (Action[l][r]==DELETE) {
			addPrint(ans,l-1,r);
			ans.add(String.format("D%s%02d",Left[l-1],r+1));
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb=new StringBuilder();
		while (!(s=br.readLine()).equals("#")) {
			StringTokenizer st=new StringTokenizer(s);
			Left=st.nextToken().toCharArray();
			Right=st.nextToken().toCharArray();

			Dp=new int [Left.length+1][Right.length+1];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],NULL);
			Dp[0][0]=0;
			Action=new int [Left.length+1][Right.length+1];
			find(Left.length,Right.length);

			ArrayList<String> ans=new ArrayList<>();
			addPrint(ans,Left.length,Right.length);

			for (int i=0;i<ans.size();i++) sb.append(ans.get(i));
			sb.append("E\n");
		}
		System.out.print(sb); // Print per case will run into TLE
	}
}