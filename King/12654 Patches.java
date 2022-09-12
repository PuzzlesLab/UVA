import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main { 

	private static int C;
	private static int [] HolePos;
	private static int [] PatchLength;
	private static int [][] Dp;
	
	private static int compute(int n, int remPatch) {
		if (remPatch==0) return 0;
		
		if (Dp[n][remPatch]==0) {
			int min=Integer.MAX_VALUE;
			for (int p=0;p<PatchLength.length;p++) {
				int coverUntil=HolePos[n]+PatchLength[p];
	
				int holesCovered=0;
				int nextHoleIdx=n;
				int delta=0;
				while (holesCovered<remPatch) {
					if (coverUntil<HolePos[nextHoleIdx]+delta) break; // Can't cover anymore
					else {
						nextHoleIdx=(nextHoleIdx+1)%HolePos.length;
						holesCovered++;
						if (nextHoleIdx==0) delta+=C; // Another circle
					}
				}
				min=Math.min(min,PatchLength[p]+compute(nextHoleIdx,remPatch-holesCovered));
			}
			Dp[n][remPatch]=min;
		}
		return Dp[n][remPatch];
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			PatchLength=new int [2];
			PatchLength[0]=Integer.parseInt(st.nextToken());
			PatchLength[1]=Integer.parseInt(st.nextToken());
			
			HolePos=new int [N];
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) HolePos[n]=Integer.parseInt(st.nextToken());
			
			Dp=new int [N+1][N+1];
			int min=Integer.MAX_VALUE;
			for (int i=0;i<N;i++) min=Math.min(min,compute(i,N)); // Start from different hole.
			System.out.println(min);
		}
	}

}
