import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main(String[] args) throws Exception {
		BigInteger [] ans=new BigInteger[1000001];
		ans[0]=BigInteger.ZERO;
		ans[1]=BigInteger.ZERO;
		ans[2]=BigInteger.ZERO;
		ans[3]=BigInteger.ZERO;
		for (int i=4;i<ans.length;i++) {
			BigInteger biMinus1=BigInteger.valueOf(i-1);
			BigInteger biMinus2=BigInteger.valueOf(i-2);
			
			BigInteger a=biMinus1.multiply(biMinus2).shiftRight(1); // For every x, there is N-2 of y, then halved by duplications.
			BigInteger b=biMinus1.shiftRight(1);
			ans[i]=ans[i-1].add(a.subtract(b).shiftRight(1));
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			System.out.println(ans[N]);
		}
	}

}
