import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {
	
	public static void main (String [] args) throws Exception {
		BigInteger [] seq=new BigInteger[801];
		seq[1]=BigInteger.ZERO;
		seq[2]=BigInteger.ONE;
		seq[3]=new BigInteger("2");
		seq[4]=new BigInteger("9");
		seq[5]=new BigInteger("44");
		for (int i=6;i<seq.length;i++) seq[i]=seq[i-1].multiply(BigInteger.valueOf(i)).add((i%2)==0 ? BigInteger.ONE : BigInteger.valueOf(-1));
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("-1")) System.out.println(seq[Integer.parseInt(s)]);
	}

}