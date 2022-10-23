import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Context {
		int r, c, time;

		public Context(int r, int c, int time) {
			this.r=r;
			this.c=c;
			this.time=time;
		}
	}

	private static class Knight {
		int r, c;
		public Knight(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}

	private static int calcTime(int r, int c, int [][][] noMTime, int [][][] mTime) {
		// Calculate the total time needed for all knights to reach r,c with avoiding monster
		// Find the knight with worst penalty if avoided monster.
		int totalTime=0;
		int worstPenalty=0;
		for (int k=0;k<4;k++) {
			if (noMTime[k][r][c]==Integer.MAX_VALUE) return Integer.MAX_VALUE;
			totalTime+=noMTime[k][r][c];

			if (mTime[k][r][c]!=Integer.MAX_VALUE) worstPenalty=Math.max(noMTime[k][r][c]-mTime[k][r][c], worstPenalty);
		}

		// We let this knight to step to the monster point to maximize time reduction.
		return totalTime-worstPenalty;
	}

	public static void main(String[] args) throws Exception {
		final int [][] deltas={
			{-2,-1}, {-2,1}, {-1,-2}, {-1,2},
			{1,-2}, {1,2}, {2,-1}, {2,1},
		};

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			String firstLine=s;
			StringTokenizer st=new StringTokenizer(br.readLine());
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int K=4;

			Knight [] knights=new Knight[K];
			st=new StringTokenizer(br.readLine());
			for (int k=0;k<K;k++) knights[k]=new Knight(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1);

			st=new StringTokenizer(br.readLine());
			int mr=Integer.parseInt(st.nextToken())-1;
			int mc=Integer.parseInt(st.nextToken())-1;

			int [][][] noMTime=new int [K][R][C];
			int [][][] mTime=new int [K][R][C];
			for (int k=0;k<K;k++) for (int r=0;r<R;r++) {
				Arrays.fill(noMTime[k][r],Integer.MAX_VALUE);
				Arrays.fill(mTime[k][r],Integer.MAX_VALUE);
			}

			for (int k=0;k<K;k++) {
				Knight knight=knights[k];
				LinkedList<Context> q;

				// Avoid monster
				q=new LinkedList<>();
				q.addLast(new Context(knight.r,knight.c,0));
				noMTime[k][knight.r][knight.c]=0;
				while (!q.isEmpty()) {
					Context ctx=q.removeFirst();
					int nt=ctx.time+1;
					for (int [] delta: deltas) {
						int nr=ctx.r+delta[0];
						int nc=ctx.c+delta[1];
						if (nr==mr && nc==mc) continue;
						if (nr>=0 && nr<R && nc>=0 && nc<C && noMTime[k][nr][nc]==Integer.MAX_VALUE) {
							noMTime[k][nr][nc]=nt;
							q.addLast(new Context(nr,nc,nt));
						}
					}
				}

				// Allow monster
				q=new LinkedList<>();
				q.addLast(new Context(knight.r,knight.c,0));
				mTime[k][knight.r][knight.c]=0;
				while (!q.isEmpty()) {
					Context ctx=q.removeFirst();
					int nt=ctx.time+1;
					for (int [] delta: deltas) {
						int nr=ctx.r+delta[0];
						int nc=ctx.c+delta[1];
						if (nr>=0 && nr<R && nc>=0 && nc<C && mTime[k][nr][nc]==Integer.MAX_VALUE) {
							mTime[k][nr][nc]=nt;
							q.addLast(new Context(nr,nc,nt));
						}
					}
				}
			}

			int ans=Integer.MAX_VALUE;
			for (int r=0;r<R;r++) for (int c=0;c<C;c++) {
				if (r==mr && c==mc) continue;
				ans=Math.min(ans,calcTime(r,c,noMTime,mTime));
			}

			StringBuilder sb=new StringBuilder();
			sb.append(firstLine);
			sb.append('\n');
			if (ans==Integer.MAX_VALUE) sb.append("Meeting is impossible.");
			else {
				sb.append("Minimum time required is ");
				sb.append(ans);
				sb.append(" minutes.");
			}
			System.out.println(sb.toString());
		}
	}

}
