import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main(String[] args) throws Exception {
		BigInteger TWO=new BigInteger("2");
		BigInteger [] cat=new BigInteger [1001];
		cat[0]=BigInteger.ONE;
		for (int i=0;i<cat.length-1;i++) {
			BigInteger bi=BigInteger.valueOf(i);
			cat[i+1]=bi.shiftLeft(2).add(TWO).multiply(cat[i]).divide(bi.add(TWO));
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			System.out.println(cat[Integer.parseInt(s)]);
		}
	}

}
