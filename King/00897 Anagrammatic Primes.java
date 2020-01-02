import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class Main {
	
	public static HashSet<Integer> primes=new HashSet<>();
	public static ArrayList<Integer> anaPrimes=new ArrayList<>();
	
	public static boolean permutateAndCheck(int [] digits, boolean [] filled, int curr, int len) {
		if (len==digits.length) return primes.contains(curr);
		else {
			boolean flag=true;
			for (int i=0;i<digits.length;i++) if (!filled[i]) {
				filled[i]=true;
				flag &= permutateAndCheck(digits,filled,curr*10+digits[i],len+1);
				filled[i]=false;
			}
			return flag;
		}
	}
	
	public static boolean isAnaPrime(int num) {
		if (num==2) return true;
		int [] digits=new int [(int)(Math.log10(num)+1)];
		int digitPos=0;
		while (num>0) {
			int mod=num%10;
			digits[digitPos++]=mod;
			if (mod%2==0) return false;
			num/=10;
		}
		return permutateAndCheck(digits, new boolean [digits.length], 0, 0);
	}
	
	public static void main (String [] args) throws Exception {
		boolean [] notPrime=new boolean [1000]; //Tested full values, the max ana-prime is 991 only.
		for (int i=2;i*i<notPrime.length;i++) if (!notPrime[i]) for (int i2=i*i;i2<notPrime.length;i2+=i) notPrime[i2]=true;
		for (int i=2;i<notPrime.length;i++) if (!notPrime[i]) primes.add(i);
		for (int i : primes) if (isAnaPrime(i)) anaPrimes.add(i);
		Collections.sort(anaPrimes);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int nextPrime=0;
			
			if (N<=anaPrimes.get(anaPrimes.size()-1)) {
				int limit=1;
				while (limit<=N) limit*=10;

				for (int test : anaPrimes) if (test>N && test<limit) {
					nextPrime=test;
					break;
				}
			}
			
			System.out.println(nextPrime);
		}
	}

}