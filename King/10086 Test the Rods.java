import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int T1;
	private static int T2;
	private static int [] M;
	private static int [][][] Cost;
	private static int [][][] Dp;
	private static int [][][] TraceT1;
	private static int [][][] TraceT2;

	private static int compute(int n, int t1Used, int t2Used) {
		if (n==M.length) return (t1Used==T1 && t2Used==T2) ? 0 : 1000000;
		
		if (Dp[n][t1Used][t2Used]==0) {
			int min=Integer.MAX_VALUE;
			for (int t1=0;t1<=M[n];t1++) {
				int t2=M[n]-t1;

				int nextT1=t1Used+t1;
				int nextT2=t2Used+t2;
				if (nextT1<=T1 && nextT2<=T2) {
					int result=Cost[n][t1][0]+Cost[n][t2][1]+compute(n+1,nextT1,nextT2);
					if (min>result) {
						min=result;
						TraceT1[n][t1Used][t2Used]=t1;
						TraceT2[n][t1Used][t2Used]=t2;
					}
				}
			}
			Dp[n][t1Used][t2Used]=min;
		}

		return Dp[n][t1Used][t2Used];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			T1=Integer.parseInt(st.nextToken());
			T2=Integer.parseInt(st.nextToken());
			
			int N=Integer.parseInt(br.readLine());
			M=new int [N];
			Cost=new int [N][][];

			for (int i=0;i<N;i++) {
				M[i]=Integer.parseInt(br.readLine());
				Cost[i]=new int [M[i]+1][2];

				st=new StringTokenizer(br.readLine());
				for (int j=1;j<=M[i];j++) Cost[i][j][0]=Integer.parseInt(st.nextToken());

				st=new StringTokenizer(br.readLine());
				for (int j=1;j<=M[i];j++) Cost[i][j][1]=Integer.parseInt(st.nextToken());
			}

			Dp=new int [N][T1+1][T2+1];
			TraceT1=new int [N][T1+1][T2+1];
			TraceT2=new int [N][T1+1][T2+1];

			// Get lowest cost
			StringBuilder sb=new StringBuilder();
			sb.append(compute(0,0,0));
			sb.append('\n');

			// Get path
			int currT1=0;
			int currT2=0;
			for (int n=0;n<N;n++) {
				sb.append(TraceT1[n][currT1][currT2]);
				sb.append(' ');
				
				int nt1=currT1+TraceT1[n][currT1][currT2];
				int nt2=currT2+TraceT2[n][currT1][currT2];
				
				currT1=nt1;
				currT2=nt2;
			}
			sb.setLength(sb.length()-1);
			sb.append('\n');

			System.out.println(sb.toString());
		}
	}

}
