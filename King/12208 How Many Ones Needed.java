import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	 // Sum of ones from 0 to N-length binary number.
	private static long [] Sum1AtLength=new long [31];

	private static int getPos(long n) {
		for (int i=Sum1AtLength.length-1;i>=0;i--) if (((n>>i)&1)!=0) return i;
		return 0;
	}

	private static long count(long n) {
		if (n<=1) return n;
		/*
		 * Sum of the following:
		 * - Amount of 1s in (X-1)-length binary.
		 * - Amount of 1s at the highest bit in n.
		 * - Amount of 1s in the remaining (X-1) bits.
		 */
		int pos=getPos(n);
		long r=pos>0?Sum1AtLength[pos-1]:0;
		r+=(n-(1L<<pos)+1);
		r+=count(n-(1L<<pos));
		return r;
	}

	public static void main(String[] args) throws Exception {
		Sum1AtLength[0]=1;
    	for (int i=1;i<Sum1AtLength.length;i++) {
    		// (0<prev>, 1<prev>) => <prev>*2
    		// (1<prev>) => 2^i
    		Sum1AtLength[i]=(Sum1AtLength[i-1]<<1)+(1<<i);
    	}

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        int tc=1;
        while (!(s=br.readLine()).equals("0 0")) {
        	StringTokenizer st=new StringTokenizer(s);
        	long min=Long.parseLong(st.nextToken());
        	long max=Long.parseLong(st.nextToken());
        	System.out.printf("Case %d: %d\n",tc++,count(max)-count(Math.max(min-1,0)));
		}

	}

}