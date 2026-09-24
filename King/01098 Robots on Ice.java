import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int [][] Deltas={{0,-1},{0,1},{-1,0},{1,0}};
	private static int [][] Checks=new int [4][2];
	private static int [] Step;

	private static int R;
	private static int C;
	private static boolean [][] ACVisited;
	
	private static int accessCount(int x, int y, boolean [][] mask) {
		if (x<0 || x>=R || y<0 || y>=C || ACVisited[x][y] || mask[x][y]) return 0;

		ACVisited[x][y]=true;
		int count=1;
		for (int [] d: Deltas) count+=accessCount(x+d[0],y+d[1],mask);
		return count;
	}

	private static int compute(int x, int y, boolean [][] visited, int step, int ns) {
		if (Step[ns]<step) return 0;
		if (Step[ns]==step) {
			if (Checks[ns][0]!=x || Checks[ns][1]!=y) return 0; 
			ns++;
			if (ns==Checks.length) return 1;
		}

		// Check if end node still can go other unvisited nodes.
		for (int i=0;i<ACVisited.length;i++) Arrays.fill(ACVisited[i],false);
		ACVisited[x][y]=true;
		int endToOther=accessCount(Checks[3][0],Checks[3][1],visited);
		if (Step[3]-step!=endToOther) return 0;

		int ans=0;
		for (int [] d: Deltas) {
			int nx=x+d[0];
			int ny=y+d[1];
			if (nx>=0 && nx<R && ny>=0 && ny<C && !visited[nx][ny]) {
				int rs=Math.abs(Checks[ns][0]-nx)+Math.abs(Checks[ns][1]-ny);
				if (rs>(Step[ns]-step)) continue; // Can't reach next checkpoint.

				visited[nx][ny]=true;
				ans+=compute(nx,ny,visited,step+1,ns);
				visited[nx][ny]=false;
			}
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int tc=1;
        String s;
        while (!(s=br.readLine()).equals("0 0")) {
        	StringTokenizer st=new StringTokenizer(s);
        	R=Integer.parseInt(st.nextToken());
        	C=Integer.parseInt(st.nextToken());
        	
        	st=new StringTokenizer(br.readLine());
        	Checks[0][0]=Integer.parseInt(st.nextToken());
        	Checks[0][1]=Integer.parseInt(st.nextToken());
        	Checks[1][0]=Integer.parseInt(st.nextToken());
        	Checks[1][1]=Integer.parseInt(st.nextToken());
        	Checks[2][0]=Integer.parseInt(st.nextToken());
        	Checks[2][1]=Integer.parseInt(st.nextToken());
        	Checks[3][0]=0;
        	Checks[3][1]=1;

        	Step=new int [4];
        	Step[0]=(R*C)>>2;
			Step[1]=(R*C)>>1;
			Step[2]=(3*R*C)>>2;
			Step[3]=R*C;

        	boolean [][] visited=new boolean [R][C];
        	visited[0][0]=true;
    		ACVisited=new boolean [R][C];
        	int ans=compute(0,0,visited,1,0);
        	
        	System.out.printf("Case %d: %d\n",tc++,ans);
        }
	}

}