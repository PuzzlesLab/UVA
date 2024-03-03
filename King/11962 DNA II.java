import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main (String [] args) throws Exception {
		BigInteger [] map=new BigInteger [128];
		map['A']=BigInteger.valueOf(0);
		map['C']=BigInteger.valueOf(1);
		map['G']=BigInteger.valueOf(2);
		map['T']=BigInteger.valueOf(3);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			String s=br.readLine();
			
			BigInteger bi=BigInteger.ZERO;
			for (int i=0;i<s.length();i++) bi=bi.shiftLeft(2).add(map[s.charAt(i)]);
			System.out.printf("Case %d: (%d:%s)\n",t,s.length(),bi);
		}
	}
}