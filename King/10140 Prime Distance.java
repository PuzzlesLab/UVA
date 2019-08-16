import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		boolean [] notPrime=new boolean [10000001];
		for (int i=2;i*i<notPrime.length;i++) for (int i2=i*i;i2<notPrime.length;i2+=i) notPrime[i2]=true;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int min=Integer.parseInt(st.nextToken());
			int max=Integer.parseInt(st.nextToken());
			
			long minLeft=0, minRight=Integer.MAX_VALUE, maxLeft=0, maxRight=0;
			long lastPrime=0;
			for (long i=Math.max(min,2);i<=max;i++) {
				boolean InotPrime=false;
				if (i<notPrime.length) InotPrime=notPrime[(int)i];
				else InotPrime=!BigInteger.valueOf(i).isProbablePrime(11);
				
				if (!InotPrime) {
					if (lastPrime!=0) {
						long diff=i-lastPrime;
						if (diff<minRight-minLeft) {
							minLeft=lastPrime;
							minRight=i;
						}
						if (diff>maxRight-maxLeft) {
							maxLeft=lastPrime;
							maxRight=i;
						}
					}
					lastPrime=i;
				}
			}
			
			if (minLeft==0) System.out.println("There are no adjacent primes.");
			else System.out.printf("%d,%d are closest, %d,%d are most distant.\n", minLeft, minRight, maxLeft, maxRight);
		}
	}

}