import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
	
	private static class Solution implements Comparable<Solution> {
		public String s;
		public int index;
		public Solution (String s, int index) { this.s=s; this.index=index; }
		public int compareTo(Solution that) { return this.index-that.index; }
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			
			char [][] puzzle=new char[x][y];
			for (int i=0;i<x;i++) puzzle[i]=br.readLine().toCharArray();
			
			int [][] index=new int [x][y];
			int indexCount=1;
			for (int i=0;i<x;i++) for (int i2=0;i2<y;i2++) if (puzzle[i][i2]!='*') {
				if (i==0 || i2==0) index[i][i2]=indexCount++;
				else if (puzzle[i-1][i2]=='*' || puzzle[i][i2-1]=='*') index[i][i2]=indexCount++;
			}
			
			TreeSet<Solution> down=new TreeSet<>();
			for (int i=0;i<y;i++) {
				StringBuilder sb=new StringBuilder();
				int tempIdx=0;
				for (int i2=0;i2<x;i2++) {
					if (sb.length()==0 && puzzle[i2][i]!='*') tempIdx=index[i2][i];
					if (puzzle[i2][i]!='*') sb.append(puzzle[i2][i]);
					else if (sb.length()>0) {
						down.add(new Solution(sb.toString(), tempIdx));
						sb.setLength(0);
					}
				}
				if (sb.length()>0) down.add(new Solution(sb.toString(), tempIdx));
			}
			
			TreeSet<Solution> across=new TreeSet<>();
			for (int i=0;i<x;i++) {
				StringBuilder sb=new StringBuilder();
				int tempIdx=0;
				for (int i2=0;i2<y;i2++) {
					if (sb.length()==0 && puzzle[i][i2]!='*') tempIdx=index[i][i2];
					if (puzzle[i][i2]!='*') sb.append(puzzle[i][i2]);
					else if (sb.length()>0) {
						across.add(new Solution(sb.toString(), tempIdx));
						sb.setLength(0);
					}
				}
				if (sb.length()>0) across.add(new Solution(sb.toString(), tempIdx));
			}
			
			StringBuilder ans=new StringBuilder();
			if (testCase>1) ans.append('\n');
			ans.append("puzzle #");
			ans.append(testCase++);
			ans.append(":\n");
			ans.append("Across\n");
			for (Solution sol : across) ans.append(String.format("%3d.%s\n", sol.index, sol.s));
			ans.append("Down\n");
			for (Solution sol : down) ans.append(String.format("%3d.%s\n", sol.index, sol.s));
			System.out.print(ans.toString());
		}
	}

}