import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int [] Pow11= {1,11,121,1331,14641,161051,1771561};
	private static int [][] Heat;
	private static int [][] EndC;
	private static int [] K;
	private static int [] Dp;
	private static boolean [] DpFlag;

	private static int hash(int [] d) {
		int r=0;
		for (int i=0;i<d.length;i++) r+=d[i]*Pow11[i];
		return r;
	}

	private static int find(int [] remChem, int remCount) {
		if (remCount<=1) return 0;

		if (!DpFlag[hash(remChem)]) {
			int ans=10000000;
			for (int c1=0;c1<remChem.length;c1++) if (remChem[c1]>0) {
				for (int c2=0;c2<remChem.length;c2++) if (remChem[c2]>0) {
					if (c1==c2 && remChem[c1]<2) continue;
	
					int n=EndC[c1][c2];
					remChem[c1]--;
					remChem[c2]--;
					remChem[n]++;
					ans=Math.min(ans,Heat[c1][c2]+find(remChem,remCount-1));
					remChem[n]--;
					remChem[c1]++;
					remChem[c2]++;
				}
			}
			Dp[hash(remChem)]=ans;
			DpFlag[hash(remChem)]=true;
		}
		return Dp[hash(remChem)];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int M=Integer.parseInt(br.readLine());
			Heat=new int [M][M];
			EndC=new int [M][M];
			for (int m=0;m<M;m++) for (int m2=0;m2<M;m2++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				EndC[m][m2]=Integer.parseInt(st.nextToken())-1;
				Heat[m][m2]=Integer.parseInt(st.nextToken());
			}
			K=new int [Integer.parseInt(br.readLine())];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int k=0;k<K.length;k++) K[k]=Integer.parseInt(st.nextToken())-1;

			int [] remChem=new int [6];
			for (int k=0;k<K.length;k++) remChem[K[k]]++;

			Dp=new int [Pow11[remChem.length]];
			DpFlag=new boolean [Dp.length];
			System.out.println(find(remChem,K.length));
			br.readLine();
		}
	}

}
