import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	private static final int [] Count= {6,2,5,5,4,5,6,3,7,6};
	private static BigInteger [][] Dp=new BigInteger [2001][2];

	private static BigInteger compute(int remN, int first) {
		if (Dp[remN][first]==null) {
			BigInteger ans=BigInteger.ZERO;
			for (int i=0;i<Count.length;i++) if (remN>=Count[i]) ans=ans.add(BigInteger.ONE).add(compute(remN-Count[i],0));
			if (first==1 && remN>=Count[0]) ans=ans.subtract(compute(remN-Count[0],0));
			Dp[remN][first]=ans;
		}
		return Dp[remN][first];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s=br.readLine())!=null) {
        	int N=Integer.parseInt(s);
        	System.out.println(compute(N,1));
        }
	}

}