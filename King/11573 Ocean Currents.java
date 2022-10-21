import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static final int [][] Deltas={
		{-1,0},
		{-1,1},
		{0,1},
		{1,1},
		{1,0},
		{1,-1},
		{0,-1},
		{-1,-1}
	};

	private static class Context {
		int r, c, energy;
		public Context(int r, int c, int e) {
			this.r=r;
			this.c=c;
			this.energy=e;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int [][] flow=new int [R][C];
			for (int r=0;r<R;r++) {
				s=br.readLine();
				for (int c=0;c<C;c++) flow[r][c]=s.charAt(c)-'0';
			}

			int N=Integer.parseInt(br.readLine());
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				int r1=Integer.parseInt(st.nextToken())-1;
				int c1=Integer.parseInt(st.nextToken())-1;
				int r2=Integer.parseInt(st.nextToken())-1;
				int c2=Integer.parseInt(st.nextToken())-1;
				int [][] cost=new int [R][C];
				for (int r=0;r<R;r++) Arrays.fill(cost[r],Integer.MAX_VALUE);

				LinkedList<Context> q=new LinkedList<>();
				q.add(new Context(r1,c1,0));
				cost[r1][c1]=0;
				int ans=Integer.MAX_VALUE;
				while (!q.isEmpty()) {
					Context ctx=q.removeFirst();
					if (ctx.r==r2 && ctx.c==c2) {
						ans=ctx.energy;
						break;
					}
					for (int d=0;d<Deltas.length;d++) {
						int nr=ctx.r+Deltas[d][0];
						int nc=ctx.c+Deltas[d][1];
						if (nr>=0 && nr<R && nc>=0 && nc<C) {
							int energy=ctx.energy+(flow[ctx.r][ctx.c]==d?0:1);
							if (energy<cost[nr][nc]) {
								cost[nr][nc]=energy;
								if (ctx.energy==energy) q.addFirst(new Context(nr,nc,energy));
								else q.addLast(new Context(nr,nc,energy));
							}
						}
					}
				}
				
				System.out.println(ans);
			}
		}
	}

}
