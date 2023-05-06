import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			int X=Integer.parseInt(st.nextToken());
			
			// Apply AP sum formula : (N/2)(a+l)
			int sum=(N*(1+N))>>1;
			int removed=(K*(X+X+K-1))>>1;
			System.out.printf("Case %d: %d\n",t,sum-removed);
		}
	}

}
