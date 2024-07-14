import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static BigInteger M;
	private static int [] Counts;

	public static void play(int n, int A, int B, int C) {
		int cmp=M.compareTo(BigInteger.ZERO);
		if (cmp<0) return;
		if (cmp==0) {
			System.out.printf("%d %d %d\n",Counts[0],Counts[N%2==0?1:2],Counts[N%2==0?2:1]);
			return;
		}
		if (n<=0) return;

		// To skip, we need at least 2^(N-1)-1
		BigInteger bound=BigInteger.ONE.shiftLeft(n-1).subtract(BigInteger.ONE);
		
		if (M.compareTo(bound)>0) {
			M=M.subtract(bound);
			Counts[A]-=n-1;
			Counts[C]+=n-1;
		} else play(n-1,A,C,B);

		M=M.subtract(BigInteger.ONE);
		Counts[A]--;
		Counts[B]++;

		if (M.compareTo(bound)>0) {
			M=M.subtract(bound);
			Counts[C]-=n-1;
			Counts[B]+=n-1;
		} else play(n-1,C,B,A);
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=new BigInteger(st.nextToken());
			if (N==0 && M.equals(BigInteger.ZERO)) break;

			Counts=new int [3];
			Counts[0]=N;

			play(N,0,2,1);
		}
	}

}
