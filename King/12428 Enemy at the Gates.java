import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static long N;
	private static long M;
	
	private static boolean check(long c) {
		long x=N-c; // Rem nodes.
		long combi=(x*(x-1))>>1; // All mesh edges.
		return M-c > combi; // Remaining roads > all mesh edges.
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Long.parseLong(st.nextToken());
			M=Long.parseLong(st.nextToken());
			
			long min=0;
			long max=N-1;
			while (max>=min) {
				long mid=(min+max)>>1;
				if (check(mid)) max=mid-1;
				else min=mid+1;
			}

			System.out.println(max);
		}
	}

}
