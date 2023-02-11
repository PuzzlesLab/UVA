import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int NO_ANS=1000000;
	private static final int MAX_T=200;
	private static int N;
	private static int T;
	private static int [] tTime;
	private static boolean [][] hasT1;
	private static boolean [][] hasT2;
	private static int [][] Dp;

	private static int find(int currN, int currT) {
		if (currT==T) return currN==N-1 ? 0 : NO_ANS;
		
		if (Dp[currN][currT]==-1) {
			int ans=NO_ANS;
			ans=Math.min(ans,1+find(currN,currT+1)); // Stay at current station
			if (hasT1[currN][currT] && currN<N-1 && currT+tTime[currN]<=T) ans=Math.min(ans,find(currN+1,currT+tTime[currN])); // Go train 1
			if (hasT2[currN][currT] && currN>0 && currT+tTime[currN-1]<=T) ans=Math.min(ans,find(currN-1,currT+tTime[currN-1])); // Go train 2
			Dp[currN][currT]=ans;
		}
		return Dp[currN][currT];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		while (true) {
			N=Integer.parseInt(br.readLine());
			if (N==0) break;
			T=Integer.parseInt(br.readLine());
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			tTime=new int [N-1];
			for (int n=0;n<tTime.length;n++) tTime[n]=Integer.parseInt(st.nextToken());
			
			int M1=Integer.parseInt(br.readLine());
			hasT1=new boolean [N][MAX_T+1];
			st=new StringTokenizer(br.readLine());
			for (int m1=0;m1<M1;m1++) {
				int start=Integer.parseInt(st.nextToken());
				if (start>T) continue;
				hasT1[0][start]=true;
				int currN=1;
				for (int n=0;n<tTime.length;n++) if (start+tTime[n]<=MAX_T) {
					start+=tTime[n];
					hasT1[currN][start]=true;
					currN++;
				}
			}

			int M2=Integer.parseInt(br.readLine());
			hasT2=new boolean [N][MAX_T+1];
			st=new StringTokenizer(br.readLine());
			for (int m2=0;m2<M2;m2++) {
				int start=Integer.parseInt(st.nextToken());
				if (start>T) continue;
				hasT2[N-1][start]=true;
				int currN=N-2;
				for (int n=tTime.length-1;n>=0;n--) if (start+tTime[n]<=MAX_T) {
					start+=tTime[n];
					hasT2[currN][start]=true;
					currN--;
				}
			}
			
			Dp=new int [N][T+1];
			for (int n=0;n<N;n++) Arrays.fill(Dp[n],-1);

			StringBuilder sb=new StringBuilder();
			sb.append("Case Number ");
			sb.append(testCase++);
			sb.append(": ");
			int ans=find(0,0);
			sb.append(ans==NO_ANS ? "impossible" : ans);
			System.out.println(sb.toString());
		}
	}

}
