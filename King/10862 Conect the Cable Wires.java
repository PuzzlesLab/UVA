import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {
	
	public static void main (String [] args) throws Exception {
		BigInteger three=new BigInteger("3");
		BigInteger [] seq=new BigInteger [2001];
		seq[1]=BigInteger.ONE;
		seq[2]=three;
		for (int i=3;i<seq.length;i++) seq[i]=three.multiply(seq[i-1]).subtract(seq[i-2]);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;
			System.out.println(seq[N].toString());			
		}
	}

}