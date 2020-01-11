import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static class Data {
		int x, y, pennies;
		
		public Data(int x, int y, int pennies) {
			this.x=x;
			this.y=y;
			this.pennies=pennies;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			int [][] pennies=new int [N][N];
			for (int i=0;i<N;i++) {
				st=new StringTokenizer(br.readLine());
				for (int i2=0;i2<N;i2++) pennies[i][i2]=Integer.parseInt(st.nextToken());
			}
			
			int [][] max=new int [N][N];
			max[0][0]=pennies[0][0];
			LinkedList<Data> q=new LinkedList<>();
			q.add(new Data(0,0,pennies[0][0]));

			while (!q.isEmpty()) {
				Data dat=q.removeFirst();
				if (dat.pennies==max[dat.x][dat.y]) {
					for (int i=Math.max(0,dat.x-K);i<=Math.min(dat.x+K,N-1);i++) if (pennies[dat.x][dat.y] < pennies[i][dat.y]) {
						int total=dat.pennies+pennies[i][dat.y];
						if (total>max[i][dat.y]) {
							max[i][dat.y]=total;
							q.add(new Data(i,dat.y,max[i][dat.y]));
						}
					}
					
					for (int i=Math.max(0,dat.y-K);i<=Math.min(dat.y+K,N-1);i++) if (pennies[dat.x][dat.y] < pennies[dat.x][i]) {
						int total=dat.pennies+pennies[dat.x][i];
						if (total>max[dat.x][i]) {
							max[dat.x][i]=total;
							q.add(new Data(dat.x,i,max[dat.x][i]));
						}
					}
				}
			}
			
			int ans=Integer.MIN_VALUE;
			for (int row=0;row<N;row++) for (int col=0;col<N;col++) ans=Math.max(max[row][col], ans);
			
			if (testCase>0) System.out.println();
			System.out.println(ans);
		}
	}

}