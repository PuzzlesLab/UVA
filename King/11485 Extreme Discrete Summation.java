import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	private static int [] Nums;
	private static BigInteger [][] Dp;

	private static BigInteger count(int remN, int currSum) {
		if (Dp[remN][currSum]==null) {
			if (remN==0) Dp[remN][currSum]=BigInteger.valueOf(currSum/10);
			else {
				BigInteger sum=BigInteger.ZERO;
				for (int n=0;n<Nums.length;n++) sum=sum.add(count(remN-1,currSum+Nums[n]));
				Dp[remN][currSum]=sum;
			}
		}
		return Dp[remN][currSum];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			Nums=new int [N];
			for (int n=0;n<N;n++) {
				String temp=st.nextToken();
				Nums[n]=temp.charAt(temp.length()-1)-'0';
			}

			Dp=new BigInteger [9][N*90+1];
			System.out.println(count(8,0));
		}
	}

}