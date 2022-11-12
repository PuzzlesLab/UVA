import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static long [][][][] Dp;
	private static int MAX_X;
	private static int R;
	private static int K;

	private static long find(int x, int y, int peakCount, int prevUp) {
		if (y<0 || y>20 || peakCount>R) return 0;
		if (x==MAX_X) return (y==0 && peakCount==R) ? 1 : 0;
		
		if (Dp[x][y][peakCount][prevUp]==-1) {
			long count=0;
			int nx=x+1;
			int ny1=y-1;
			int ny2=y+1;
			boolean canBePeak=prevUp==1 && y==K;
			count+=find(nx,ny1,peakCount+(canBePeak?1:0),0);
			count+=find(nx,ny2,peakCount,1);
			Dp[x][y][peakCount][prevUp]=count;
		}
		return Dp[x][y][peakCount][prevUp];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			R=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			MAX_X=2*N;
			Dp=new long[MAX_X+1][21][R+1][2];
			for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++) for (int i3=0;i3<Dp[i][i2].length;i3++) Arrays.fill(Dp[i][i2][i3],-1);
			long ans=find(0,0,0,1);
			System.out.println(ans);
		}
	}

}
