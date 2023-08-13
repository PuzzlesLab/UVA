import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int MAX=1048577;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [82025];
	private static int PrimeCount=0;
	
	private static void initialize() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static boolean isCPrime(int n) {
		for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=n;i++) {
			if (n%Prime[i]==0 && !NotPrime[n/Prime[i]]) return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		initialize();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int M=Integer.parseInt(s);

			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] nums=new int [M];
			for (int m=0;m<M;m++) nums[m]=Integer.parseInt(st.nextToken());

			int ans=0;
			for (int m=0;m<M;m++) if (isCPrime(nums[m])) ans++;
			System.out.println(ans);
		}
	}

}