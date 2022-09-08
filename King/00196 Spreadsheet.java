import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Cell {
		public int value;
		public HashMap<Cell,Integer> from;
		public HashSet<Cell> to;
		
		public Cell() {
			this.from=new HashMap<>();
			this.to=new HashSet<>();
		}
	}

	private static Cell findCell(Cell [][] cells, String id) {
		int col=0;
		int row=0;
		char [] ch=id.toCharArray();
		for (int i=0;i<ch.length;i++) {
			if (ch[i]>='A' && ch[i]<='Z') {
				col=col*26+(ch[i]-'A'+1);
			} else {
				row=row*10+(ch[i]-'0');
			}
		}
		return cells[row][col];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int Y=Integer.parseInt(st.nextToken());
			int X=Integer.parseInt(st.nextToken());
			
			Cell [][] cells=new Cell[X+1][Y+1];
			for (int x=1;x<=X;x++) for (int y=1;y<=Y;y++) cells[x][y]=new Cell();

			for (int x=1;x<=X;x++) {
				st=new StringTokenizer(br.readLine());
				for (int y=1;y<=Y;y++) {
					String s=st.nextToken();
					if (s.charAt(0)=='=') {
						StringTokenizer st2=new StringTokenizer(s.substring(1), "+");
						while (st2.hasMoreTokens()) {
							Cell next=findCell(cells,st2.nextToken());
							cells[x][y].from.put(next,cells[x][y].from.getOrDefault(next,0)+1);
							next.to.add(cells[x][y]);;
						}
					} else {
						cells[x][y].value=Integer.parseInt(s);
					}
				}
			}
			
			LinkedList<Cell> q=new LinkedList<>();
			for (int x=1;x<=X;x++) for (int y=1;y<=Y;y++) if (cells[x][y].from.isEmpty()) q.addLast(cells[x][y]);

			while (!q.isEmpty()) {
				Cell src=q.removeFirst();
				for (Cell dest: src.to) {
					dest.value+=src.value*dest.from.get(src);
					dest.from.remove(src);
					if (dest.from.isEmpty()) {
						q.addLast(dest);
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			for (int x=1;x<=X;x++) {
				for (int y=1;y<=Y;y++) {
					sb.append(cells[x][y].value);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				sb.append('\n');
			}

			System.out.print(sb.toString());
		}
	}

}
