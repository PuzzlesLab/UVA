import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main { 

	private static long [][] Dp;

	public static long compute(int level, boolean lastBranched, int targetLevel) {
		if (level==targetLevel) return 1;
		
		int idx2=lastBranched?1:0;
		
		if (Dp[level][idx2]==0) {
			long sum=compute(level+1,true,targetLevel);
			if (lastBranched) sum+=compute(level+1,false,targetLevel);
			Dp[level][idx2]=sum;
		}

		return Dp[level][idx2];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int level=Integer.parseInt(s);
			Dp=new long [level+1][2];
			System.out.println(compute(1,false,level));
		}
	}

}
