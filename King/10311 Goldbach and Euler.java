import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	
	public static ArrayList<Integer> primes=new ArrayList<>();
	
	public static void main (String [] args) throws Exception {
		boolean [] notPrime=new boolean [100000001];
		notPrime[1]=true;
		for (int i=2;i*i<notPrime.length;i++) if (!notPrime[i]) for (int i2=i*i;i2<notPrime.length;i2+=i) notPrime[i2]=true;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			int Nhalf = N>>1;
			int p1=0, p2=0;
			
			//Goldbach’s conjecture : Every even integer greater than 2 can be expressed as the sum of two primes.
			if (N%2==0 && N>2) {
				for (int next=--Nhalf;next>=2;next--) {
					int n=N-next;
					if (n<0) break;
					if (!notPrime[next] && !notPrime[n]) {
						p1=next;
						p2=n;
						break;
					}
				}
			} else if (N>2 && !notPrime[N-2] && N-2!=Nhalf) {
				//Odd number = either 2 + prime or nothing!
				p1=2;
				p2=N-2;
			}
			
			if (p1==0) System.out.printf("%d is not the sum of two primes!\n", N);
			else System.out.printf("%d is the sum of %d and %d.\n", N, p1, p2);
		}
	}

}