import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX_N=24;
		BigInteger [][] valuesCount=new BigInteger [MAX_N+1][(6*MAX_N)+1];
		for (int n=0;n<=MAX_N;n++) Arrays.fill(valuesCount[n],BigInteger.ZERO);
		valuesCount[0][0]=BigInteger.ONE;
		for (int n=1;n<=MAX_N;n++) for (int i=1;i<=6;i++) {
			for (int v=i;v<valuesCount[MAX_N].length;v++) valuesCount[n][v]=valuesCount[n][v].add(valuesCount[n-1][v-i]);
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int X=Integer.parseInt(st.nextToken());

			BigInteger count=BigInteger.ZERO;
			BigInteger total=BigInteger.ZERO;
			for (int n=N;n<valuesCount[N].length;n++) {
				if (n>=X) count=count.add(valuesCount[N][n]);
				total=total.add(valuesCount[N][n]);
			}
			BigInteger gcd=count.gcd(total);
			count=count.divide(gcd);
			total=total.divide(gcd);

			if (total.equals(BigInteger.ONE)) System.out.println(count);
			else System.out.println(count+"/"+total);
		}
	}

}
