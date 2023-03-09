import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

class Main {

	private static int N;
	private static HashSet<Integer> [] AdjList;
	private static boolean [] Visited;
	private static int [] Pair;

	private static int mcbm(int x) {
		if (Visited[x]) return 0;
		
		Visited[x]=true;
		for (int next: AdjList[x]) {
			if (Pair[next]==-1 || mcbm(Pair[next])==1) {
				Pair[next]=x;
				return 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			N=Integer.parseInt(s);

			char [][] board=new char [N][N];
			for (int n=0;n<N;n++) board[n]=br.readLine().toCharArray();
			
			int [][] rowArea=new int [N][N];
			int [][] colArea=new int [N][N];
			for (int x=0;x<N;x++) {
				Arrays.fill(rowArea[x],-1);
				Arrays.fill(colArea[x],-1);
			}

			// Label horizontal area
			int rowAreaMax=0;
			for (int x=0;x<N;x++) for (int y=0;y<N;y++) {
				if (board[x][y]=='X' || rowArea[x][y]!=-1) continue;

				for (int ny=y;ny<N;ny++) {
					if (board[x][ny]=='X') break;
					rowArea[x][ny]=rowAreaMax;
				}
				rowAreaMax++;
			}

			// Label vertical area
			int colAreaMax=0;
			for (int y=0;y<N;y++) for (int x=0;x<N;x++) {
				if (board[x][y]=='X' || colArea[x][y]!=-1) continue;

				for (int nx=x;nx<N;nx++) {
					if (board[nx][y]=='X') break;
					colArea[nx][y]=colAreaMax;
				}
				colAreaMax++;
			}

			AdjList=new HashSet [rowAreaMax];
			for (int n=0;n<rowAreaMax;n++) AdjList[n]=new HashSet<>();
			for (int x=0;x<N;x++) for (int y=0;y<N;y++) if (rowArea[x][y]!=-1) AdjList[rowArea[x][y]].add(colArea[x][y]);

			int ans=0;
			Pair=new int [colAreaMax];
			Arrays.fill(Pair,-1);
			for (int x=0;x<rowAreaMax;x++) {
				Visited=new boolean [rowAreaMax];
				ans+=mcbm(x);
			}
			System.out.println(ans); 
		}
	}

}
