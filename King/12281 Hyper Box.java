import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final long [] Fib=new long [57]; // fib[47] > 2000000000
	
	private static int greedyCount(long len) {
		int count=0;
		for (int i=Fib.length-1;i>=0;i--) {
			while (len>=Fib[i]) {
				len-=Fib[i];
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) throws Exception {
		Fib[0]=1;
		Fib[1]=1;
		for (int i=2;i<Fib.length;i++) Fib[i]=(Fib[i-2]+Fib[i-1]);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			StringTokenizer st=new StringTokenizer(br.readLine());
			long [] len=new long [N];
			for (int n=0;n<N;n++) len[n]=Long.parseLong(st.nextToken());

			long ans=1;
			for (int n=0;n<N;n++) ans*=greedyCount(len[n]);

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(ans);
			System.out.println(sb);
		}
	}

}
