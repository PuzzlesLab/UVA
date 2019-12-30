import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {
	
	public static void main (String [] args) throws Exception {
		BigInteger [] seq=new BigInteger[51];
		seq[1]=new BigInteger("2"); //0, 1
		seq[2]=new BigInteger("3"); //00, 01, 10
		seq[3]=new BigInteger("5"); //000, 001, 010, 100, 101
		seq[4]=new BigInteger("8"); //0000, 0001, 0010, 0100, 0101, 1000, 1001, 1010 .. fib!
		for (int i=5;i<seq.length;i++) seq[i]=seq[i-1].add(seq[i-2]);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) System.out.printf("Scenario #%d:\n%s\n\n",t,seq[Integer.parseInt(br.readLine())].toString());			
	}

}