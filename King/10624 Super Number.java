import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	private static BigInteger [] baseNums=new BigInteger [61];
	private static BigInteger ans=null;

	public static void find(BigInteger curr, int digitsCount, int N, int M) {
		if (ans!=null) return;
		else if (digitsCount==M) ans=curr;
		else {
			int startNum=curr.equals(BigInteger.ZERO) ? 1 : 0;
			BigInteger mulTen=curr.multiply(BigInteger.TEN);
			for (int i=startNum;i<10;i++) {
				BigInteger next=mulTen.add(baseNums[i]);
				if (digitsCount<N-1 || next.remainder(baseNums[digitsCount+1]).equals(BigInteger.ZERO)) {
					find(next,digitsCount+1,N,M);
				}
			}
		}
	}

	public static void main (String [] args) throws Exception {
		for (int i=0;i<baseNums.length;i++) baseNums[i]=BigInteger.valueOf(i);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			ans=null;
			find(BigInteger.ZERO,0,N,M);
			if (ans==null) ans=BigInteger.valueOf(-1);
			System.out.printf("Case %d: %s\n", testCase, ans);
		}
	}

}
