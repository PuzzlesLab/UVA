import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int base1=Integer.parseInt(st.nextToken());
			int base2=Integer.parseInt(st.nextToken());
			String num=st.nextToken();
			try {
				BigInteger bi=new BigInteger(num, base1);
				String ans=bi.toString(base2).toUpperCase();
				System.out.println(num+" base "+base1+" = "+ans+" base "+base2);
			} catch (Exception e) {
				System.out.println(num+" is an illegal base "+base1+" number");
			}
		}
	}

}
