import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static long gcd(long a, long b) { return b == 0 ? a : gcd(b, a % b); }
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int [] nums=new int [N];
			for (int n=0;n<N;n++) nums[n]=Integer.parseInt(st.nextToken());
			
			long btmTop=0;
			long btmBtm=1;
			for (int n=0;n<N;n++) {
				btmTop=nums[n]*btmTop+btmBtm;
				btmBtm*=nums[n];
				
				long gcd=gcd(btmTop,btmBtm);
				btmTop/=gcd;
				btmBtm/=gcd;
			}
			
			long top=N*btmBtm;
			long btm=btmTop;
			long gcd=gcd(top,btm);
			top/=gcd;
			btm/=gcd;
			
			System.out.printf("Case %d: %d/%d\n", testCase, top, btm);
		}
	}

}