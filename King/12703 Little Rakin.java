import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

class Main {

	private static final int MAX=1001;
	private static ArrayList<Integer> Primes;
	private static BigInteger [] Fib;
	
	private static void initialize() {
		Fib=new BigInteger [MAX];
		Fib[0]=BigInteger.ONE;
		Fib[1]=BigInteger.ONE;
		for (int i=2;i<Fib.length;i++) Fib[i]=Fib[i-1].add(Fib[i-2]);

		Primes=new ArrayList<>();
		boolean [] notPrime=new boolean [MAX];
		for (int i=2;i*i<notPrime.length;i++) {
			if (!notPrime[i]) for (int i2=i*i;i2<notPrime.length;i2+=i) notPrime[i2]=true;
		}
		for (int i=2;i<notPrime.length;i++) if (!notPrime[i]) Primes.add(i);
	}

	private static Map<Integer,BigInteger> getPrimeFactors(int n, BigInteger multi) {
		HashMap<Integer,BigInteger> map=new HashMap<>();
		for (int i=0;i<Primes.size();i++) {
			int f=Primes.get(i);
			while (n%f==0) {
				n/=f;
				map.put(f,map.getOrDefault(f,BigInteger.ZERO).add(multi));
			}
			if (n==1) break;
		}
		return new TreeMap<>(map);
	}

	public static void main(String[] args) throws Exception {
		initialize();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			
			StringBuilder sb=new StringBuilder();
			if (A==B) {
				Map<Integer,BigInteger> factors=getPrimeFactors(A,Fib[N]);
				for (Entry<Integer,BigInteger> entry: factors.entrySet()) {
					sb.append(entry.getKey());
					sb.append(' ');
					sb.append(entry.getValue());
					sb.append('\n');
				}
			} else {
				Map<Integer,BigInteger> factorsA=getPrimeFactors(A,Fib[N-2]);
				Map<Integer,BigInteger> factorsB=getPrimeFactors(B,Fib[N-1]);
				TreeSet<Integer> factors=new TreeSet<>();
				factors.addAll(factorsA.keySet());
				factors.addAll(factorsB.keySet());
				
				for (int factor: factors) {
					sb.append(factor);
					sb.append(' ');
					sb.append(factorsA.getOrDefault(factor,BigInteger.ZERO).add(factorsB.getOrDefault(factor,BigInteger.ZERO)));
					sb.append('\n');
				}
			}

			System.out.println(sb.toString());
		}
	}

}
