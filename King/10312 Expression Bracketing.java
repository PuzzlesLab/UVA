import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX_N=28;

		BigInteger TWO=new BigInteger("2");
		BigInteger THREE=new BigInteger("3");
		BigInteger [] cat=new BigInteger [MAX_N]; // Catalan
		cat[0]=BigInteger.ONE;
		for (int i=0;i<cat.length-1;i++) {
			BigInteger bi=BigInteger.valueOf(i);
			cat[i+1]=bi.shiftLeft(2).add(TWO).multiply(cat[i]).divide(bi.add(TWO));
		}
		
		BigInteger [] sCat=new BigInteger [MAX_N]; // Super Catalan
		sCat[0]=BigInteger.ONE;
		sCat[1]=BigInteger.ONE;
		sCat[2]=BigInteger.ONE;
		for (int i=3;i<sCat.length;i++) {
			sCat[i]=THREE
				.multiply(TWO.multiply(BigInteger.valueOf(i))
				.subtract(THREE))
				.multiply(sCat[i-1])
				.subtract(BigInteger.valueOf(i-3).multiply(sCat[i-2]))
				.divide(BigInteger.valueOf(i));
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			System.out.println(sCat[N].subtract(cat[N-1]));
		}
	}

}
