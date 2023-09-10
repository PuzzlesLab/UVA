import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX_N=301;
		BigInteger [] fac=new BigInteger [MAX_N];
		fac[0]=BigInteger.ONE;
		for (int i=1;i<fac.length;i++) fac[i]=fac[i-1].multiply(BigInteger.valueOf(i));

		BigInteger TWO=new BigInteger("2");
		BigInteger [] cat=new BigInteger [MAX_N];
		cat[0]=BigInteger.ONE;
		for (int i=0;i<cat.length-1;i++) {
			BigInteger bi=BigInteger.valueOf(i);
			cat[i+1]=bi.shiftLeft(2).add(TWO).multiply(cat[i]).divide(bi.add(TWO));
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			if (N==0) break;
			System.out.println(cat[N].multiply(fac[N]));
		}
	}

}