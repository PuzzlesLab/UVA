import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int MAX_ANS=100000;
	private static final int SIZE=15;
	private static char [][] Map;
	private static int [] RowCols;
	private static int Ans;

	private static void dfs(int curr, int rowCount, int colMask) {
		if (rowCount>=Ans) return;
		if (curr==SIZE) {
			int colCount=0;
			for (int i=0;i<SIZE;i++) if ((colMask&(1<<i))!=0) colCount++;
	        Ans=Math.min(Ans,Math.max(rowCount,colCount));
			return;
		}

		dfs(curr+1,rowCount+1,colMask);
		dfs(curr+1,rowCount,colMask|RowCols[curr]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s="";
		while (true) {
			s=br.readLine();
			if (s.equals("END")) break;

			Map=new char[SIZE][SIZE];
			Map[0]=s.toCharArray();
			int idx=1;
			while (idx<SIZE) {
				s=br.readLine();
				Map[idx++]=s.toCharArray();
			}

			RowCols=new int [SIZE];
			for (int i=0;i<Map.length;i++) {
				for (int i2=0;i2<Map[i].length;i2++) if (Map[i][i2]=='#') RowCols[i]|=(1<<i2);
			}

			Ans=MAX_ANS;
			dfs(0,0,0);
			System.out.println(Ans);
		}
	}

}