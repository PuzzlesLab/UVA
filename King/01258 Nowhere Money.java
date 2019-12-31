import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

class Main {
	
	public static void main (String [] args) throws Exception {
		BigInteger MAX=new BigInteger("5000000000000000000");
		ArrayList<BigInteger> fibs=new ArrayList<>();
		fibs.add(BigInteger.ONE);
		fibs.add(BigInteger.ONE);
		while (true) {
			BigInteger currFib=fibs.get(fibs.size()-1).add(fibs.get(fibs.size()-2));
			fibs.add(currFib);
			if (currFib.compareTo(MAX)>=0) break;
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			BigInteger bi=new BigInteger(s);
			ArrayList<Integer> term=new ArrayList<>();
			ArrayList<BigInteger> termV=new ArrayList<>();
			
			for (int i=fibs.size()-1;i>=1;i--) {
				BigInteger currPow=fibs.get(i);
				if (bi.compareTo(currPow)>=0) {
					bi = bi.subtract(currPow);
					term.add(i);
					termV.add(currPow);
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append(s);
			sb.append("\n");
			for (int t : term) {
				sb.append(t);
				sb.append(' ');
			}
			sb.append('\n');
			for (BigInteger b : termV) {
				sb.append(b.toString());
				sb.append(' ');
			}
			sb.append('\n');
			
			System.out.println(sb.toString());
		}
	}

}