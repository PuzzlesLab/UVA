import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		final long MOD=100000000000L;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			long curr=1;
			for (int i=N-M+1;i<=N;i++) {
				curr*=i;
				while (curr%10==0) curr/=10;
				curr=curr%MOD;
			}
			System.out.println(curr%10);
		}
	}

}
