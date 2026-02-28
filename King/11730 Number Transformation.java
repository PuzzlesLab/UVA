import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static boolean [] NotPrime=new boolean [1001];
	private static ArrayList<Integer> Primes=new ArrayList<>();
	private static ArrayList<Integer> [] PrimeFactors=new ArrayList [1001];
	
	private static void eSieve() {
		NotPrime[0]=true;
		NotPrime[1]=true;
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) Primes.add(i);
	}
	
	private static ArrayList<Integer> getPrimeFactors(int n) {
		if (PrimeFactors[n]==null) {
			ArrayList<Integer> ans=new ArrayList<>();
			for (int i=0;i<Primes.size() && Primes.get(i)<n;i++) {
				int f=Primes.get(i);
				if (n%f==0) ans.add(f);
			}
			PrimeFactors[n]=ans;
		}
		return PrimeFactors[n];
	}

	private static class Tuple {
		int n, dist;
		
		public Tuple(int n, int dist) {
			this.n=n;
			this.dist=dist;
		}
	}

	public static void main(String [] args) throws Exception {
		final int ANS_NULL=1000000;
		eSieve();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int S=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());

			int ans=ANS_NULL;
			boolean [] visited=new boolean [Math.max(S,T)+1];

			LinkedList<Tuple> q=new LinkedList<>();
			q.add(new Tuple(S,0));
			while (!q.isEmpty()) {
				Tuple curr=q.poll();
				if (curr.n==T) ans=Math.min(ans,curr.dist);

				ArrayList<Integer> next=getPrimeFactors(curr.n);
				for (int i=0;i<next.size();i++) {
					int nn=curr.n+next.get(i);
					if (nn>=visited.length || visited[nn]) continue;
					visited[nn]=true;
					q.addLast(new Tuple(nn,curr.dist+1));
				}
			}
			if (ans==ANS_NULL) ans=-1;

			System.out.printf("Case %d: %d\n",tc++,ans);
		}
	}

}
