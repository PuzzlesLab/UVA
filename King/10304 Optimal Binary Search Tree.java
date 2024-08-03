import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int NULL=-100000;
	private static final int NO_VALUE=1000000;
	private static int [] Freq;
	private static int [] Sum;
	private static int [][] Dp;
	
	private static int getMin(int from, int to) {
		if (Dp[from][to]==NULL) {
			if (from>to) return Dp[from][to]=NO_VALUE;
			if (from==to) return Dp[from][to]=0;

			int min=NO_VALUE;
			min=Math.min(min,getMin(from+1,to)-Freq[from]); // From as root.
			for (int root=from;root<to;root++) {
				min=Math.min(min,getMin(from,root-1)+getMin(root+1,to)-Freq[root]); // Any middle as root.
			}
			min=Math.min(min,getMin(from,to-1)-Freq[to]); // To as root.
			Dp[from][to]=(Sum[to]-Sum[from-1])+min;
		}
		return Dp[from][to];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int S=Integer.parseInt(st.nextToken());
			Freq=new int [S+1];
			Sum=new int [S+1];
			Dp=new int [S+1][S+1];
			for (int i=1;i<=S;i++) {
				Freq[i]=Integer.parseInt(st.nextToken());
				Sum[i]=Sum[i-1]+Freq[i];
			}

			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],NULL);
			System.out.println(getMin(1,S));
		}
	}

}