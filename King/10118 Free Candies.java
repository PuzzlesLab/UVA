import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [][] Piles;
	private static int [][][][] Dp;
	private static boolean [][][][] DpFlag;

	private static int compute(int [] nLevel, int exists) {
		if (!DpFlag[nLevel[0]][nLevel[1]][nLevel[2]][nLevel[3]]) {
			int ans=0;
			if (Integer.bitCount(exists)<5) { // Max 5 in basket
				for (int pick=0;pick<4;pick++) {
					if (nLevel[pick]==Piles[pick].length) continue;
					int candy=Piles[pick][nLevel[pick]++];
					if ((exists&(1<<candy))!=0) {
						ans=Math.max(ans,1+compute(nLevel,exists^(1<<candy)));
					} else {
						ans=Math.max(ans,compute(nLevel,exists|(1<<candy)));
					}
					nLevel[pick]--;
				}
			}
			DpFlag[nLevel[0]][nLevel[1]][nLevel[2]][nLevel[3]]=true;
			Dp[nLevel[0]][nLevel[1]][nLevel[2]][nLevel[3]]=ans;
		}

		return Dp[nLevel[0]][nLevel[1]][nLevel[2]][nLevel[3]];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s=br.readLine()).equals("0")) {
        	int N=Integer.parseInt(s);
        	Piles=new int [4][N];
        	for (int n=0;n<N;n++) {
        		StringTokenizer st=new StringTokenizer(br.readLine());
        		for (int i=0;i<Piles.length;i++) Piles[i][n]=Integer.parseInt(st.nextToken());
        	}
        	
        	Dp=new int [N+1][N+1][N+1][N+1];
        	DpFlag=new boolean [N+1][N+1][N+1][N+1];
        	System.out.println(compute(new int [4],0));
        }
	}

}