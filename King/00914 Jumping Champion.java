import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
	
	public static TreeSet<Integer> primes=new TreeSet<>();
	
	public static void main (String [] args) throws Exception {
		boolean [] notPrime=new boolean [1000001];
		for (int i=2;i*i<notPrime.length;i++) if (!notPrime[i]) for (int i2=i*i;i2<notPrime.length;i2+=i) notPrime[i2]=true;
		for (int i=2;i<notPrime.length;i++) if (!notPrime[i]) primes.add(i);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int min=Integer.parseInt(st.nextToken());
			int max=Integer.parseInt(st.nextToken());
			
			HashMap<Integer, Integer> counter=new HashMap<>();
			TreeSet<Integer> subset = new TreeSet<>(primes.subSet(min, max+1));
			Integer lastNum=subset.pollFirst();
			if (lastNum!=null) {
				for (int n : subset) {
					int diff=n-lastNum;
					lastNum=n;
					counter.put(diff,counter.getOrDefault(diff,0)+1);
				}
			}
			
			int maxCounter=-1;
			for (int v : counter.values()) maxCounter=Math.max(maxCounter, v);
			
			int maxCounterCount=0;
			int maxCounterKey=-1;
			for (int key : counter.keySet()) if (counter.get(key)==maxCounter) {
				maxCounterKey=key;
				maxCounterCount++;
			}
			
			if (maxCounterKey==-1 || maxCounterCount>1) System.out.println("No jumping champion");
			else System.out.println("The jumping champion is "+maxCounterKey);
		}
	}

}