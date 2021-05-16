import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static boolean feasible(int N, int M, char [] balls, int [][] delta) {
		boolean flag=true;

		char [][] map=new char [N][M];
		int currBall=0;
		int x=0,y=0,dir=0;
		while (true) {
			map[x][y]=balls[currBall++];
			
			if (currBall==balls.length) break;
			
			int nx=0;
			int ny=0;
			for (int i=0;i<4;i++) {
				nx=x+delta[dir][0];
				ny=y+delta[dir][1];
				if (nx>=0 && nx<N && ny>=0 && ny<M && map[nx][ny]==0) break;
				dir=(dir+1)%delta.length;
			}

			x=nx;
			y=ny;
		}
		
		for (int col=0;col<map[0].length;col++) for (int row=0;row<map.length-1;row++) {
			flag &= map[row][col] == map[row+1][col];
		}
		
		return flag;
	}

	public static void main (String [] args) throws Exception {
		int [][] delta= {{0,1},{1,0},{0,-1},{-1,0}};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			char [] balls=br.readLine().toCharArray();

			int ans=Integer.MAX_VALUE;
			for (int n=2;n<=balls.length/2;n++) if (balls.length%n==0) {
				int M=balls.length/n;
				if (feasible(n,M,balls,delta)) ans=Math.min(ans,n+M);
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(": ");
			sb.append(ans==Integer.MAX_VALUE ? -1 : ans);
			System.out.println(sb.toString());
		}
	}
}