import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	private static int Answer=0;
	
	private static void fill(int [][] grid, boolean [] rowFilled, boolean [] colFilled, int count, int currRow, int currAns) {
		if (count==grid.length) Answer=Integer.min(Answer,currAns);
		else {
			if (currRow==-1) { //Alice
				for (int i=0;i<grid.length;i++) if (!rowFilled[i]) {
					rowFilled[i]=true;
					fill(grid,rowFilled,colFilled,count,i,currAns);
					rowFilled[i]=false;
				}
			} else { //Bob
				int minValue=Integer.MAX_VALUE;
				for (int i=0;i<grid.length;i++) if (!colFilled[i]) minValue=Integer.min(minValue,grid[currRow][i]);
				for (int i=0;i<grid.length;i++) if (!colFilled[i] && grid[currRow][i]==minValue) {
					colFilled[i]=true;
					fill(grid,rowFilled,colFilled,count+1,-1,currAns+grid[currRow][i]);
					colFilled[i]=false;
				}
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			int [][] grid=new int [N][N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int n2=0;n2<N;n2++) grid[n][n2]=Integer.parseInt(st.nextToken());
			}
			
			Answer=Integer.MAX_VALUE;
			fill(grid,new boolean [N],new boolean [N],0,-1,0);
			System.out.println(Answer);
		}
	}

}