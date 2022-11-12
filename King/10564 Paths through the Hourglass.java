import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Position {
		int x, y, sum;
		char dir;

		public Position(int x, int y, int sum, char dir) {
			this.x=x;
			this.y=y;
			this.sum=sum;
			this.dir=dir;
		}
	}

	private static long [][][] Dp;
	private static Position [][][] Parent;
	private static int [][] Glass;
	private static int N;
	private static int S;
	
	private static long find(int x, int y, int sum) {
		if (x==Glass.length) return (sum==S) ? 1 : 0;

		if (Dp[x][y][sum]==-1) {
			long count=0;
			int minDy=0, maxDy=0;
			if (x<N-1) minDy=-1; // Contract => y-1, y
			else maxDy=1; // Expand => y, y+1

			boolean left=true;
			for (int dy=minDy;dy<=maxDy;dy++) {
				int ny=y+dy;
				if (x+1==Glass.length) {
					if (sum==S) {
						count++;
						Parent[x][y][sum]=new Position(x+1,ny,sum,left?'L':'R');
					}
					break;
				}
				else if (ny>=0 && ny<Glass[x+1].length) {
					int nSum=sum;
					nSum+=Glass[x+1][ny];
					if (nSum<=S) {
						long currCount=find(x+1,ny,nSum);
						if (currCount>0 && Parent[x][y][sum]==null) Parent[x][y][sum]=new Position(x+1,ny,nSum,left?'L':'R');
						count+=currCount;
					}
				}
				left=!left;
			}
			Dp[x][y][sum]=count;
		}
		return Dp[x][y][sum];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			S=Integer.parseInt(st.nextToken());
			
			Glass=new int [2*N-1][];
			for (int n=0;n<Glass.length;n++) {
				st=new StringTokenizer(br.readLine());
				Glass[n]=new int [st.countTokens()];
				for (int i=0;i<Glass[n].length;i++) Glass[n][i]=Integer.parseInt(st.nextToken());
			}
			
			Dp=new long [Glass.length][N+1][S+1];
			Parent=new Position[Glass.length][N+1][S+1];
			for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++) Arrays.fill(Dp[i][i2],-1);

			long ans=0;
			int firstFound=-1;
			for (int i=0;i<Glass[0].length;i++) {
				long curr=find(0,i,Glass[0][i]);
				if (curr>0 && firstFound==-1) firstFound=i;
				ans+=curr;
			}
			StringBuilder sb=new StringBuilder();
			sb.append(ans);
			sb.append('\n');
			if (firstFound!=-1) {
				sb.append(firstFound);
				sb.append(' ');
				Position p=Parent[0][firstFound][Glass[0][firstFound]];
				while (p.x<Glass.length) {
					sb.append(p.dir);
					p=Parent[p.x][p.y][p.sum];
				}
			}
			System.out.println(sb.toString());
		}
	}

}