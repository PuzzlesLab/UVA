import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int UP=60;
	private static final int HOLD=30;
	private static final int SINK=20;
	private static final int INVALID=100000;
	private static int [][] Dp;
	
	public static int compute(int [][] windStr, int x, int y) {
		if (y==windStr[0].length) return x==windStr.length-1 ? 0 : INVALID;

		if (Dp[x][y]==0) {
			int sol=INVALID;
			if (x>0) sol=Math.min(sol,compute(windStr,x-1,y+1)+UP-windStr[x][y]);
			sol=Math.min(sol,compute(windStr,x,y+1)+HOLD-windStr[x][y]);
			if (x<windStr.length-1) sol=Math.min(sol,compute(windStr,x+1,y+1)+SINK-windStr[x][y]);
			Dp[x][y]=sol;
		}
		return Dp[x][y];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine(); // Empty line
			int X=Integer.parseInt(br.readLine())/100;
			int [][] windStr=new int [10][X];
			for (int i=0;i<windStr.length;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int i2=0;i2<X;i2++) windStr[i][i2]=Integer.parseInt(st.nextToken());
			}
			
			Dp=new int [10][X];
			System.out.println(compute(windStr,9,0));
			System.out.println();
		}
	}

}
