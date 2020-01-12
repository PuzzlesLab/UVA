import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static class Data {
		int x, y;
		long w;
		public Data(int x, int y, long w) {
			this.x=x;
			this.y=y;
			this.w=w;
		}
	}
	public static void main (String [] args) throws Exception {
		int [][] deltas = {{0,1},{1,0}};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int W=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());
			
			boolean [][] wolf=new boolean [W+1][H+1];
			
			int N=Integer.parseInt(br.readLine());
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				int x= Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				wolf[x][y]=true;
			}
			 
			long [][] ways=new long[W+1][H+1];
			ways[0][0]=1;
			
			LinkedList<Data> q=new LinkedList<>();
			q.add(new Data(0,0,1));
			while (!q.isEmpty()) {
				Data dat=q.removeFirst();
				if (ways[dat.x][dat.y]==dat.w) {
					for (int [] delta : deltas) {
						int dx=dat.x+delta[0], dy=dat.y+delta[1];
						if (dx<=W && dy<=H && !wolf[dx][dy]) {
							ways[dx][dy]+=ways[dat.x][dat.y];
							q.addLast(new Data(dx,dy,ways[dx][dy]));
						}
					}
				}
			}
			
			if (ways[W][H]==0) System.out.println("There is no path.");
			else if (ways[W][H]==1) System.out.println("There is one path from Little Red Riding Hood's house to her grandmother's house.");
			else System.out.printf("There are %d paths from Little Red Riding Hood's house to her grandmother's house.\n", ways[W][H]);
		}
	}

}