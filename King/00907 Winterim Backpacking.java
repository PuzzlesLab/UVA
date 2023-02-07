import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken())+1;
			int K=Integer.parseInt(st.nextToken())+1;
			
			int [] dist=new int [N];
			int [] distSum=new int [N];
			for (int n=0;n<dist.length;n++) dist[n]=Integer.parseInt(br.readLine());
			for (int n=0;n<distSum.length;n++) distSum[n]=(n>0 ? distSum[n-1] : 0)+dist[n];

			int [][] dp=new int [N][K];
			for (int n=0;n<dist.length;n++) Arrays.fill(dp[n],Integer.MAX_VALUE);
			for (int n=0;n<dist.length;n++) dp[n][0]=distSum[n];
			
			for (int toCamp=1;toCamp<N;toCamp++) {
				for (int day=1;day<K;day++) {
					for (int fromCamp=0;fromCamp<toCamp;fromCamp++) {
						dp[toCamp][day]=Math.min(dp[toCamp][day],Math.max(dp[fromCamp][day-1], distSum[toCamp]-distSum[fromCamp]));
					}
				}
			}

			System.out.println(dp[N-1][K-1]);
		}
	}

}
