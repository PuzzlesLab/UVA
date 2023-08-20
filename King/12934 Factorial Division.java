import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int TC=1;
		while ((s=br.readLine())!=null) {
			BigInteger k=new BigInteger(s);
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(TC++);
			sb.append(": ");
			if (k.compareTo(BigInteger.ONE)<=0) sb.append("Impossible");
			else {
				BigInteger n=BigInteger.valueOf(2);
				boolean found=false;
				while (!found) {
					BigInteger lim=n.multiply(n.subtract(BigInteger.ONE));
					if (lim.compareTo(k)>0) break;

					BigInteger fac=n;
					BigInteger m=n.subtract(BigInteger.ONE);
					while (m.compareTo(BigInteger.ONE)>=0 && fac.compareTo(k)<=0) {
						if (fac.equals(k)) {
							sb.append(n);
							sb.append(' ');
							sb.append(m);
							found=true;
							break;
						}
						fac=fac.multiply(m);
						m=m.subtract(BigInteger.ONE);
					}
					
					n=n.add(BigInteger.ONE);
				}
				if (!found) {
					sb.append(k);
					sb.append(' ');
					sb.append(k.subtract(BigInteger.ONE));
				}
			}
			System.out.println(sb.toString());
		}
	}

}
