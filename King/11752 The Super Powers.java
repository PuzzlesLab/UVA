import java.math.BigInteger;
import java.util.TreeSet;

class Main {
	
	public static boolean [] notPrime=new boolean [(Short.MAX_VALUE+1)<<1];

	public static void main (String [] args) throws Exception {
		for (int i=2;i*i<notPrime.length;i++) if (!notPrime[i]) for (int i2=i*i;i2<notPrime.length;i2+=i) notPrime[i2]=true;
		
		BigInteger MAX=BigInteger.valueOf(2).pow(64).subtract(BigInteger.ONE);

		TreeSet<BigInteger> ans=new TreeSet<>();
		ans.add(BigInteger.ONE);
		for (int i=2;i<notPrime.length;i++) {
			BigInteger p=BigInteger.valueOf(i);
			BigInteger num=p.pow(4);
			int pow=4;
			while (num.compareTo(MAX)<0) {
				if (notPrime[pow])  ans.add(num);
				num=num.multiply(p);
				pow++;
			}
		}
		
		StringBuilder sb=new StringBuilder();
		for (BigInteger bi : ans) {
			sb.append(bi);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}

}