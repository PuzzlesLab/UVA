import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [] Indexes;
	private static int [] ExistsCount;
	private static long [][] Dp;
	
	private static long calc(int remI, int remGet) {
		if (remGet==0) return 1;
		if (remI==-1 || remGet<0) return 0;

		if (Dp[remI][remGet]==-1) {
			long ans=0;
			for (int i=0;i<=ExistsCount[Indexes[remI]];i++) ans+=calc(remI-1,remGet-i);
			Dp[remI][remGet]=ans;
		}
		return Dp[remI][remGet];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			if (N==0) break;

			st=new StringTokenizer(br.readLine());
			ExistsCount=new int [N+1];
			Indexes=new int [N];
			int uniqueCount=0;
			for (int n=0;n<N;n++) {
				int num=Integer.parseInt(st.nextToken());
				ExistsCount[num]++;
				if (ExistsCount[num]==1) Indexes[uniqueCount++]=num;
			}

			Dp=new long [uniqueCount][N+1];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
			st=new StringTokenizer(br.readLine());

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(":\n");
			for (int m=0;m<M;m++) {
				int r=Integer.parseInt(st.nextToken());
				sb.append(calc(uniqueCount-1,r));
				sb.append('\n');
			}
			System.out.print(sb);
		}
	}

}
