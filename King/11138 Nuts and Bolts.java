import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static boolean [][] AdjMat;
	private static int [] Match;
	private static boolean [] Visited;

	private static int mcbm(int l) {
		if (Visited[l]) return 0;
		
		Visited[l]=true;
		for (int r=0;r<AdjMat[l].length;r++) if (AdjMat[l][r]) {
			if (Match[r]==-1 || mcbm(Match[r])==1) {
				Match[r]=l;
				return 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int L=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());

			AdjMat=new boolean [L][R];
			for (int l=0;l<L;l++) {
				st=new StringTokenizer(br.readLine());
				for (int r=0;r<R;r++) AdjMat[l][r]=Integer.parseInt(st.nextToken())==1;
			}
			
			Match=new int [R];
			Arrays.fill(Match,-1);
			int fit=0;
			for (int l=0;l<L;l++) {
				Visited=new boolean[L];
				fit+=mcbm(l);
			}
			System.out.printf("Case %d: a maximum of %d nuts and bolts can be fitted together\n",testCase,fit);
		}
	}

}
