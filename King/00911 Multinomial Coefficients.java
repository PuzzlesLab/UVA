import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BigInteger [] fac=new BigInteger[1001];
		fac[0]=BigInteger.ONE;
		for (int i=1;i<fac.length;i++) fac[i]=fac[i-1].multiply(BigInteger.valueOf(i));
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			int K=Integer.parseInt(br.readLine());
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			BigInteger ans=fac[N];
			for (int i=0;i<K;i++) ans=ans.divide(fac[Integer.parseInt(st.nextToken())]);
			
			System.out.println(ans);
		}
	}

}