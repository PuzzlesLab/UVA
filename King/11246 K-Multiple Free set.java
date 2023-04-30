import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long N=Long.parseLong(st.nextToken());
			long K=Long.parseLong(st.nextToken());
			
			long ans=0;
			while (N!=0) {
				ans+=N;
				N/=K;
				N*=-1;
			}
			System.out.println(ans);
		}
	}
}
