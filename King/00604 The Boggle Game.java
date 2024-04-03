import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

	private static final int [][] Deltas= {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	private static final boolean [] VOWELS=new boolean [128];
	private static HashSet<String> Result;
	
	private static void find(char [][] grid, int x, int y, char [] curr, boolean [][] visited, int currI, int vI) {
		if (vI>2) return;
		if (currI==4) {
			if (vI==2) Result.add(new String(curr));
			return;
		}
		
		for (int d=0;d<Deltas.length;d++) {
			int nx=x+Deltas[d][0];
			int ny=y+Deltas[d][1];
			if (nx<0 || nx>=grid.length || ny<0 || ny>=grid.length) continue;
			if (visited[nx][ny]) continue;

			visited[nx][ny]=true;
			curr[currI]=grid[nx][ny];
			find(grid,nx,ny,curr,visited,currI+1,vI+(VOWELS[grid[nx][ny]]?1:0));
			visited[nx][ny]=false;
		}
	}

	private static HashSet<String> getWords(char [][] grid) {
		Result=new HashSet<>();
		for (int x=0;x<grid.length;x++) for (int y=0;y<grid.length;y++) {
			char [] temp=new char [4];
			boolean [][] flag=new boolean [4][4];
			temp[0]=grid[x][y];
			flag[x][y]=true;
			find(grid,x,y,temp,flag,1,VOWELS[grid[x][y]]?1:0);
		}
		return Result;
	}

	public static void main (String [] args) throws Exception {
		VOWELS['A']=true;
		VOWELS['E']=true;
		VOWELS['I']=true;
		VOWELS['O']=true;
		VOWELS['U']=true;
		VOWELS['Y']=true;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean first=true;
		while (!(s=br.readLine()).equals("#")) {
			char [][] grid1=new char [4][4];
			char [][] grid2=new char [4][4];
			
			for (int i=0;i<4;i++) {
				StringTokenizer st=new StringTokenizer(s);
				for (int i2=0;i2<4;i2++) grid1[i][i2]=st.nextToken().charAt(0);
				for (int i2=0;i2<4;i2++) grid2[i][i2]=st.nextToken().charAt(0);
				s=br.readLine();
			}

			HashSet<String> w1=getWords(grid1);
			HashSet<String> w2=getWords(grid2);
			w1.retainAll(w2);
			TreeSet<String> ans=new TreeSet<>(w1);
			
			StringBuilder sb=new StringBuilder();
			if (first) first=false;
			else sb.append('\n');
			if (ans.isEmpty()) sb.append("There are no common words for this pair of boggle boards.\n");
			else {
				for (String w: ans) {
					sb.append(w);
					sb.append('\n');
				}
			}
			System.out.print(sb);
		}
	}
}