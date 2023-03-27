import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long N=Integer.parseInt(st.nextToken());
			long M=Integer.parseInt(st.nextToken());
			
			long S1=(N*(1+N))>>1;
			long S2=(M-N-1)*N;
			System.out.println(S1+S2);
		}
	}
}
