import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [][] Prices;
	private static int [][] Dp;
	private static int MaxPrice;

	private static int compute(int type, int money) {
		if (type==Prices.length) return money;
		if (Dp[type][money]==Integer.MIN_VALUE) {
			int sol=-1000;
			for (int i=0;i<Prices[type].length;i++) if (money+Prices[type][i]<=MaxPrice) {
				sol=Math.max(sol,compute(type+1,money+Prices[type][i]));
			}
			Dp[type][money]=sol;
		}
		return Dp[type][money];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			MaxPrice=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			
			Prices=new int [C][];
			for (int c=0;c<C;c++) {
				st=new StringTokenizer(br.readLine());
				int K=Integer.parseInt(st.nextToken());
				Prices[c]=new int [K];
				for (int k=0;k<K;k++) Prices[c][k]=Integer.parseInt(st.nextToken());
			}

			Dp=new int [C][MaxPrice+1];
			for (int c=0;c<C;c++) Arrays.fill(Dp[c],Integer.MIN_VALUE);
			int sol=compute(0,0);
			System.out.println(sol>=0 ? sol : "no solution");
		}
	}

}
