import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			
			boolean [][] bomb=new boolean [R][C];
			int rl=Integer.parseInt(br.readLine());
			for (int i=0;i<rl;i++) {
				st=new StringTokenizer(br.readLine());
				int r=Integer.parseInt(st.nextToken());
				int cl=Integer.parseInt(st.nextToken());
				for (int i2=0;i2<cl;i2++) {
					int zz=Integer.parseInt(st.nextToken());
					bomb[r][zz]=true;
					//System.out.println("BBBB "+r+" - "+zz);
				}
			}
			
			st=new StringTokenizer(br.readLine());
			int sx=Integer.parseInt(st.nextToken());
			int sy=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			int dx=Integer.parseInt(st.nextToken());
			int dy=Integer.parseInt(st.nextToken());
			
			int [][] dist=new int [R][C];
			for (int r=0;r<R;r++) for (int c=0;c<C;c++) dist[r][c]=-1;
			ArrayDeque<int []> queue=new ArrayDeque<>();
			dist[sx][sy]=0;
			queue.offer(new int [] {sx, sy});
			while (dist[dx][dy]==-1 && !queue.isEmpty()) {
				int [] pos=queue.poll();
				int r=pos[0], c=pos[1];
				
				if (r>0 && !bomb[r-1][c] && dist[r-1][c]==-1) {
					queue.offer(new int [] {r-1,c});
					dist[r-1][c]=dist[r][c]+1;
				}
				if (r<R-1 && !bomb[r+1][c] && dist[r+1][c]==-1) {
					queue.offer(new int [] {r+1,c,dist[r][c]});
					dist[r+1][c]=dist[r][c]+1;
				}
				if (c>0 && !bomb[r][c-1] && dist[r][c-1]==-1) {
					queue.offer(new int [] {r,c-1,dist[r][c]});
					dist[r][c-1]=dist[r][c]+1;
				}
				if (c<C-1 && !bomb[r][c+1] && dist[r][c+1]==-1) {
					queue.offer(new int [] {r,c+1,dist[r][c]});
					dist[r][c+1]=dist[r][c]+1;
				}
			}
			
			System.out.println(dist[dx][dy]);
		}
	}

}
