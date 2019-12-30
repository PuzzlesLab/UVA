import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		int [] fib=new int [39]; //40th fib will exceed 100000000.
		fib[0]=1;
		fib[1]=2;
		for (int i=2;i<fib.length;i++) fib[i]=fib[i-2]+fib[i-1];
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int Q=Integer.parseInt(br.readLine());
			
			StringBuilder sb=new StringBuilder();
			int q=Q;
			for (int i=fib.length-1;i>=0;i--) {
				if (q>=fib[i]) {
					q-=fib[i];
					sb.append('1');
				} else if (sb.length()>0) sb.append('0');
			}
			
			System.out.printf("%d = %s (fib)\n", Q, sb.toString());
		}
	}

}