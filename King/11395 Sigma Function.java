import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

class Main {

	private final static long MAX=1000050000001L;
	private static long [] Odd=new long [1707148];
	
	public static void main(String[] args) throws Exception {
		// Hint from CP4 Vol 2.
		long pow2=1;
		int oddI=0;
		HashSet<Long> added=new HashSet<>();
		while (pow2<MAX) {
			for (long i=1;true;i++) {
				long n=pow2*i*i;
				if (n>MAX) break;
				if (!added.contains(n)) {
					Odd[oddI++]=n;
					added.add(n);
				}
			}
			pow2<<=1;
		}
		Arrays.sort(Odd);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			long N=Long.parseLong(br.readLine());
			if (N==0) break;

			long ans=0;
			for (int i=0;i<Odd.length;i++) { // Use binary search for faster.
				if (Odd[i]>N) {
					ans=N-i;
					break;
				}
			}
			System.out.println(ans);
		}
	}

}
