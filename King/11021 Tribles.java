import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			double [] p=new double [N];
			for (int n=0;n<N;n++) p[n]=Double.parseDouble(br.readLine());
			
			double [] dp=new double [M+1];
			for (int m=1;m<=M;m++) for (int n=0;n<N;n++) dp[m]+=Math.pow(dp[m-1],n)*p[n];
			System.out.printf("Case #%d: %.7f\n",tc,Math.pow(dp[M],K));
		}
	}

}