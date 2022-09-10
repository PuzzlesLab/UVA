import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Cell{
		String pos;
		int value;
		HashMap<Cell,Integer> from;
		HashSet<Cell> to;
		String raw;

		public Cell(String pos) {
			this.pos=pos;
			this.from=new HashMap<>();
			this.to=new HashSet<>();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int X=Integer.parseInt(st.nextToken());
			int Y=Integer.parseInt(st.nextToken());
			
			Cell [][] cells=new Cell[X][Y];
			for (int x=0;x<X;x++) for (int y=0;y<Y;y++) {
				StringBuilder sb=new StringBuilder();
				sb.append((char)(x+'A'));
				sb.append(y);
				cells[x][y]=new Cell(sb.toString());
			}

			for (int x=0;x<X;x++) for (int y=0;y<Y;y++) {
				Cell curr=cells[x][y];
				String raw=br.readLine().trim();
				curr.raw=raw;
				char [] ch=raw.toCharArray();

				StringBuilder sb=new StringBuilder();
				ArrayList<String> tokens=new ArrayList<>();
				for (int i=0;i<ch.length;i++) {
					char c=ch[i];
					if (c=='-' || c=='+') {
						if (sb.length()>0) {
							tokens.add(sb.toString());
							sb.setLength(0);
						}
					}
					sb.append(c);
				}
				if (sb.length()>0) tokens.add(sb.toString());

				for (int i=0;i<tokens.size();i++) {
					s=tokens.get(i);
					int sign=s.charAt(0)=='-' ? -1: 1;
					if (s.charAt(0)=='+' || s.charAt(0)=='-') s=s.substring(1); // Remove sign
					char c=s.charAt(0);
					if (c>='A' && c<='Z') {
						int row=c-'A';
						int col=Integer.parseInt(s.substring(1));
						if (row<X && col<Y) {
							curr.from.put(cells[row][col],curr.from.getOrDefault(cells[row][col],0)+sign);
							cells[row][col].to.add(curr);
						}
					} else {
						curr.value+=sign*Integer.parseInt(s);
					}
				}
			}

			LinkedList<Cell> q=new LinkedList<>();
			for (int x=0;x<X;x++) for (int y=0;y<Y;y++) {
				Cell curr=cells[x][y];
				if (curr.from.isEmpty() && !curr.to.isEmpty()) q.addLast(curr);
			}
			
			HashSet<Cell> visited=new HashSet<>();
			while (!q.isEmpty()) {
				Cell curr=q.removeFirst();
				visited.add(curr);
				for (Cell next: curr.to) {
					next.value+=curr.value*next.from.get(curr);
					next.from.remove(curr);
					if (next.from.isEmpty()) q.addLast(next);
				}
			}
			
			StringBuilder sb=new StringBuilder();
			for (int x=0;x<X;x++) for (int y=0;y<Y;y++) {
				Cell curr=cells[x][y];
				if (!curr.from.isEmpty()) {
					sb.append(curr.pos);
					sb.append(": ");
					sb.append(curr.raw);
					sb.append('\n');
				}
			}

			if (sb.length()>0) { // Exists cycle
				System.out.println(sb.toString());
				continue; // Process next test case
			}
			
			// All values resolved.
			sb.append(" ");
			for (int y=0;y<Y;y++) sb.append(String.format("%6d",y));
			sb.append('\n');
			
			for (int x=0;x<X;x++) {
				sb.append((char)(x+'A'));
				for (int y=0;y<Y;y++) {
					sb.append(String.format("%6d",cells[x][y].value));
				}
				sb.append('\n');
			}
			
			System.out.println(sb.toString());
		}
	}

}
