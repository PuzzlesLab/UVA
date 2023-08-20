import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BigInteger [] fac=new BigInteger [13];
		fac[0]=BigInteger.ONE;
		for (int i=1;i<fac.length;i++) fac[i]=fac[i-1].multiply(BigInteger.valueOf(i));

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine().trim());
			if (N==0) break;
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] existsCount=new int [10];
			int sum=0;
			for (int n=0;n<N;n++) {
				int num=Integer.parseInt(st.nextToken());
				sum+=num;
				existsCount[num]++;
			}

			BigInteger base=BigInteger.valueOf(sum).multiply(fac[N-1]);
			BigInteger ans=BigInteger.ZERO;
			for (int n=0;n<N;n++) ans=ans.multiply(BigInteger.TEN).add(base);
			for (int n=0;n<10;n++) ans=ans.divide(fac[existsCount[n]]);

			System.out.println(ans);
		}
	}

}
