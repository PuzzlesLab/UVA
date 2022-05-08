import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	/*
	 * Explanation :
	 * Given a "automaton identifier" as lookup table,
	 * Find the original cells that fits the transformation into given transformed cells.
	 * 
	 * 
	 * Cell Index   :  0     1    2
	 * Lookup Pos   : Left Cell Right
	 */

	private static char [] ResultCells;
	private static char [] LookupTable;

	private static char [] i2bc(int num) {
		char [] ch=new char [3];
		for (int i=ch.length-1;i>=0;i--) {
			ch[i]=(char)((num%2)+'0');
			num/=2;
		}
		return ch;
	}

	private static boolean find(char [] cells, int currPos) {
		if (currPos==cells.length) return true;
		for (int i=0;i<LookupTable.length;i++) if (ResultCells[currPos]==LookupTable[i]) {
			int prev=currPos>=1 ? currPos-1 : cells.length-1-currPos;
			int next=(currPos+1)%cells.length;
			
			boolean fillPrev=cells[prev]==0; // Filled if index >= 1
			boolean fillCurr=cells[currPos]==0; // Filled by last call
			boolean fillNext=cells[next]==0; // Filled if ended
			
			// Check if current search of cell matches the
			// combination that gives matching result
			char [] expected=i2bc(i);
			if (!fillPrev && cells[prev]!=expected[0]) continue;
			if (!fillCurr && cells[currPos]!=expected[1]) continue;
			if (!fillNext && cells[next]!=expected[2]) continue;
			
			if (fillPrev) cells[prev]=expected[0];
			if (fillCurr) cells[currPos]=expected[1];
			if (fillNext) cells[next]=expected[2];
			
			if (find(cells,currPos+1)) return true;

			if (fillPrev) cells[prev]=0;
			if (fillCurr) cells[currPos]=0;
			if (fillNext) cells[next]=0;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			String id=Integer.toBinaryString(Integer.parseInt(st.nextToken()));
			while (id.length()<8) {
				StringBuilder sb=new StringBuilder();
				sb.append("0");
				sb.append(id);
				id=sb.toString();
			}
			int N=Integer.parseInt(st.nextToken());
			
			ResultCells=st.nextToken().toCharArray();
			LookupTable=id.toCharArray();
			char [] cells=new char [N];
			Arrays.fill(cells, (char)0);
			System.out.println(find(cells, 0) ? "REACHABLE" : "GARDEN OF EDEN");
		}
	}

}
