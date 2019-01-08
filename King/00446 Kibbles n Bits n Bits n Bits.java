import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			BigInteger bi1=new BigInteger(st.nextToken(), 16);
			char op=st.nextToken().charAt(0);
			BigInteger bi2=new BigInteger(st.nextToken(), 16);
			BigInteger result=op=='+' ? bi1.add(bi2) : bi1.subtract(bi2);
			
			String s1=bi1.toString(2);
			while (s1.length()<13) s1="0"+s1;
			String s2= bi2.toString(2);
			while (s2.length()<13) s2="0"+s2;
			System.out.printf("%s %c %s = %s\n", s1, op, s2, result.toString());
		}
	}

}
