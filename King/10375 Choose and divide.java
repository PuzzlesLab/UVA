import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		MathContext mc=new MathContext(20);
		
		BigInteger [] fac=new BigInteger[10000];
		fac[0]=BigInteger.ONE;
		for (int i=1;i<fac.length;i++) fac[i]=fac[i-1].multiply(BigInteger.valueOf(i));
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String str;
		while ((str=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(str);
			int p=Integer.parseInt(st.nextToken());
			int q=Integer.parseInt(st.nextToken());
			int r=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			
			BigInteger num=fac[p].divide(fac[p-q]).divide(fac[q]);
			BigInteger den=fac[r].divide(fac[r-s]).divide(fac[s]);

			double d=new BigDecimal(num).divide(new BigDecimal(den), mc).doubleValue();
			System.out.printf("%.5f\n",d);
		}
	}

}