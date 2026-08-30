import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int TARGET=20;
	private static int [] Scores;
	private static int [][] Dp;

	private static int find(int start, int mask) {
		if (start==Scores.length) return 0;
		if (Dp[start][mask]==-1) {
			int max=0;
			for (int n1=start;n1<Scores.length;n1++) if ((mask&(1<<n1))==0) {
				for (int n2=n1+1;n2<Scores.length;n2++) if ((mask&(1<<n2))==0) {
					for (int n3=n2+1;n3<Scores.length;n3++) if ((mask&(1<<n3))==0) {
						if (Scores[n1]+Scores[n2]+Scores[n3]>=TARGET) {
							int newMask=mask;
							newMask|=(1<<n1);
							newMask|=(1<<n2);
							newMask|=(1<<n3);
							max=Math.max(max,1+find(n1+1,newMask));
						}
					}
				}
			}
			Dp[start][mask]=max;
		}
		return Dp[start][mask];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Scores=new int [N];

			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) Scores[i]=Integer.parseInt(st.nextToken());
			
			Dp=new int [N][1<<N];
			for (int n=0;n<N;n++) Arrays.fill(Dp[n],-1);
			int ans=find(0,0);
			System.out.printf("Case %d: %d\n",tc++,ans);
		}
	}

}