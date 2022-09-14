import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main { 
	
	private static char [] Seq;
	private static char [] Search;
	private static BigInteger [][] Dp;

	private static BigInteger compute(int seqIdx, int searchIdx) {
		if (searchIdx==Search.length) return BigInteger.ONE;
		if (seqIdx==Seq.length) return BigInteger.ZERO;
		
		if (Dp[seqIdx][searchIdx]==null) {
			BigInteger count=BigInteger.ZERO;
			for (int i=seqIdx;i<Seq.length;i++) if (Seq[i]==Search[searchIdx]) {
				count=count.add(compute(i+1,searchIdx+1));
			}
			Dp[seqIdx][searchIdx]=count;
		}

		return Dp[seqIdx][searchIdx];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			Seq=br.readLine().toCharArray();
			Search=br.readLine().toCharArray();
			
			Dp=new BigInteger [Seq.length][Search.length];
			System.out.println(compute(0,0));
		}
	}

}
