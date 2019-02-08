import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		boolean [] notPrime=new boolean [1001];
		int [] primeNumbers=new int [500];
		int primeNumbersCount=0;
		for (int i=2;i<=100;i++) if (!notPrime[i]) for (int i2=i*i;i2<=1000;i2+=i) notPrime[i2]=true;
		
		for (int i=1;i<notPrime.length;i++) if (!notPrime[i]) primeNumbers[primeNumbersCount++]=i;
		
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> primes=new ArrayList<>();
			for (int i=0;i<primeNumbersCount && primeNumbers[i]<=N;i++) primes.add(primeNumbers[i]);
			
			int cut=(C<<1)-primes.size()%2;
			int start=Math.max(0,(primes.size()-cut)/2);
			int end=Math.min(primes.size(), start+cut);
			
			StringBuilder sb=new StringBuilder();
			sb.append(N);
			sb.append(' ');
			sb.append(C);
			sb.append(':');
			for (int i=start;i<end;i++) {
				sb.append(' ');
				sb.append(primes.get(i));
			}
			sb.append('\n');
			System.out.println(sb.toString());
		}
	}

}