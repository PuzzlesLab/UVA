import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX_N=101;

		BigInteger TWO=new BigInteger("2");
		BigInteger [] cat=new BigInteger [MAX_N];
		cat[0]=BigInteger.ONE;
		for (int i=0;i<cat.length-1;i++) {
			BigInteger bi=BigInteger.valueOf(i);
			cat[i+1]=bi.shiftLeft(2).add(TWO).multiply(cat[i]).divide(bi.add(TWO));
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			int N=(Integer.parseInt(br.readLine())>>1)-1;
			BigInteger bi=BigInteger.valueOf((N<<1)+1);

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(cat[N].multiply(bi));
			System.out.println(sb);
		}
	}

}