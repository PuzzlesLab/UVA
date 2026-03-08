import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Main {

	private static boolean [] NotPrime=new boolean [1000001];
	private static int [] Primes=new int [78498];
	private static int PrimeCount=0;
	
	private static void eSieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		
		NotPrime[0]=true;
		NotPrime[1]=true;
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) Primes[PrimeCount++]=i;
	}

	private static long find(ArrayList<Integer> pF, long min) {
		PriorityQueue<Long> q=new PriorityQueue<>();
		long start=1;
		for (int i=0;i<pF.size();i++) start*=pF.get(i);
		q.offer(start);

		while (!q.isEmpty()) {
			long curr=q.poll();
			if (curr>min) return curr;
			for (int i=0;i<pF.size();i++) {
				long next=curr*pF.get(i);
				if (next<2000000) q.offer(next);
			}
		}
		return -1;
	}

	public static void main(String [] args) throws Exception {
		eSieve();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			long N=Integer.parseInt(s);
			
			long temp=N;
			ArrayList<Integer> pF=new ArrayList<>();
			for (int i=0;i<Primes.length && temp>0 && temp>=Primes[i];i++) {
				if (temp%Primes[i]==0) {
					pF.add(Primes[i]);
					while (temp%Primes[i]==0) temp/=Primes[i];
				}
			}
			if (pF.isEmpty()) System.out.println("Not Exist!");
			else {
				long ans=find(pF,N);
				System.out.println(ans==-1?"Not Exist!":ans);
			}
		}
	}

}