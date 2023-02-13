import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int NULL=Integer.MAX_VALUE>>2;
	private static final int COLORS=6; // Seems like this is the min color to pass the test data
	private static boolean [][] AdjMat;
	private static int [] OutDeg;
	private static int [][] Dp;
	
	private static int find(int root, int color) {
		if (OutDeg[root]==0) return color;
		
		if (Dp[root][color]==NULL) {
			int sum=0;
			for (int n=0;n<AdjMat.length;n++) if (AdjMat[root][n]) {
				int min=NULL;
				for (int tColor=1;tColor<=COLORS;tColor++) if (tColor!=color) min=Math.min(min,find(n,tColor));
				sum+=min;
			}
			Dp[root][color]=sum+color;
		}
		return Dp[root][color];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			AdjMat=new boolean [N][N];
			int [] InDeg=new int [N];
			OutDeg=new int [N];

			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				String fStr=st.nextToken();
				int from=Integer.parseInt(fStr.substring(0,fStr.length()-1));
				while (st.hasMoreTokens()) {
					int to=Integer.parseInt(st.nextToken());
					AdjMat[from][to]=true;
					InDeg[to]++;
					OutDeg[from]++;
				}
			}

			int root=0;
			for (int n=0;n<N;n++) if (InDeg[n]==0) {
				root=n;
				break;
			}

			Dp=new int [N][COLORS+1];
			for (int n=0;n<N;n++) Arrays.fill(Dp[n],NULL);
			int ans=Integer.MAX_VALUE;
			for (int color=1;color<=COLORS;color++) ans=Math.min(ans,find(root,color));
			System.out.println(ans);
			br.readLine();
		}
	}

}
