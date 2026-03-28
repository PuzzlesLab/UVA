import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class zz {

	private static boolean [] NotPrime=new boolean [10000000];
	private static int [] PrimeNum=new int [664579];
	private static int PrimeNumCount=0;
	private static int [] FirstPF=new int [NotPrime.length];
	private static int [] PFCount=new int [NotPrime.length];
	private static int [] PFCulSum;

	private static void eSieve() {
		NotPrime[0]=NotPrime[1]=true;
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) {
				FirstPF[i2]=i;
				NotPrime[i2]=true;
			}
		}

		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) PrimeNum[PrimeNumCount++]=i;
	}
	
	private static int reverse(int n) {
		int rev=0;
		while (n>0) {
			rev=rev*10+(n%10);
			n/=10;
		}
		return rev;
	}

	private static int calcPFCount(int n) {
		if (n<=1) return 0;
		if (!NotPrime[n]) return 1;

		if (PFCount[n]==-1) PFCount[n]=1+calcPFCount(n/FirstPF[n]);
		return PFCount[n];
	}
	
	private static void updateSummation(ArrayList<Integer> reversePrimes) {
		PFCulSum=new int [reversePrimes.size()];
		for (int i=0;i<PFCulSum.length;i++) {
			if (i>0) PFCulSum[i]=PFCulSum[i-1];
			PFCulSum[i]+=calcPFCount(reversePrimes.get(i));
		}
	}

	public static void main(String [] args) throws Exception {
		eSieve();
		Arrays.fill(PFCount,-1);

		ArrayList<Integer> reversePrimes=new ArrayList<>();
		for (int i=0;i<PrimeNumCount && PrimeNum[i]<1000000;i++) {
			int rev=reverse(PrimeNum[i]);
			while (rev<1000000) rev*=10;
			reversePrimes.add(rev);
		}
		Collections.sort(reversePrimes);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean dirty=true;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			char op=st.nextToken().charAt(0);
			int d=Integer.parseInt(st.nextToken());

			if (op=='q') {
				if (dirty) {
					updateSummation(reversePrimes);
					dirty=false;
				}
				System.out.println(PFCulSum[d]);
			}
			else if (op=='d') {
				reversePrimes.remove(Collections.binarySearch(reversePrimes,d));
				dirty=true;
			}
		}
	}

}