import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Main {

	private static final BigInteger TWO=new BigInteger("2");
	private static final SecureRandom Random=new SecureRandom();
	private static HashMap<BigInteger,Integer> Ans;
	
	private static BigInteger f(BigInteger x, BigInteger b, BigInteger n) {
		return x.multiply(x).mod(n).add(b).mod(n);
	}

	private static BigInteger rho(BigInteger bi) {
		if (bi.mod(TWO).equals(BigInteger.ZERO)) return TWO;
		BigInteger b=new BigInteger(bi.bitLength(),Random);
		BigInteger x=new BigInteger(bi.bitLength(),Random);
		BigInteger y=x;
		while (true) {
			x=f(x,b,bi);
			y=f(f(y,b,bi),b,bi);
			BigInteger d=x.subtract(y).gcd(bi);
			if (!d.equals(BigInteger.ONE)) {
				return d;
			}
		}
	}

	private static void pRho(BigInteger bi) {
		if (bi.equals(BigInteger.ONE)) return;
		if (bi.isProbablePrime(12)) {
			Ans.put(bi,Ans.getOrDefault(bi,0)+1);
			return;
		}
		BigInteger divisor=rho(bi);
		pRho(divisor);
		pRho(bi.divide(divisor));
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			BigInteger bi=new BigInteger(br.readLine());
			Ans=new HashMap<>();
			pRho(bi);
			
			ArrayList<BigInteger> factors=new ArrayList<>();
			factors.addAll(Ans.keySet());
			Collections.sort(factors);
			
			StringBuilder sb=new StringBuilder();
			sb.append(bi);
			sb.append(" = ");
			for (int i=0;i<factors.size();i++) {
				BigInteger f=factors.get(i);
				sb.append(f);
				int count=Ans.get(f);
				if (count>1) {
					sb.append('^');
					sb.append(count);
				}
				sb.append(" * ");
			}
			sb.setLength(sb.length()-3);
			System.out.println(sb);
		}
	}

}
