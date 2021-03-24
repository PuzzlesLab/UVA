import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	private static class Cell {
		int ix, iy;
		
		public Cell(int x, int y) {
			this.ix=x;
			this.iy=y;
		}
	}

	private static String coorToStr(String prefix, int x, int y) {
		StringBuilder sb=new StringBuilder();
		if (prefix!=null) sb.append(prefix);
		sb.append('(');
		sb.append(x);
		sb.append(',');
		sb.append(y);
		sb.append(')');
		return sb.toString();
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			if (R==0 || C==0) break;
			
			/* Linked list is more logical to use in this case
			   but give worse execution time in the judge with java.util.LinkedList :/
			 */
			ArrayList<ArrayList<Cell>> table=new ArrayList<>();
			for (int r=0;r<R;r++) {
				ArrayList<Cell> row=new ArrayList<>();
				for (int c=0;c<C;c++) row.add(new Cell(r+1,c+1));
				table.add(row);
			}

			int OP=Integer.parseInt(br.readLine());
			for (int i=0;i<OP;i++) {
				st=new StringTokenizer(br.readLine());
				String op=st.nextToken();
				ArrayList<Integer> opArgs=new ArrayList<>();
				
				if (!op.equals("EX")) {
					int A=Integer.parseInt(st.nextToken());
					for (int a=0;a<A;a++) opArgs.add(Integer.parseInt(st.nextToken()));
					Collections.sort(opArgs);
				} else {
					while (st.hasMoreTokens()) opArgs.add(Integer.parseInt(st.nextToken()));
				}

				switch (op) {
					case "DR": {
						for (int idx=0;idx<opArgs.size();idx++) {
							int row = opArgs.get(idx)-1-idx;
							table.remove(row);
						}
						R-=opArgs.size();
						break;
					}
					case "DC": {
						for (int idx=0;idx<opArgs.size();idx++) {
							int col=opArgs.get(idx)-1-idx;
							for (ArrayList<Cell> row : table) row.remove(col);
						}
						C-=opArgs.size();
						break;
					}
					case "IR": {
						for (int idx=0;idx<opArgs.size();idx++) {
							int row=opArgs.get(idx)-1+idx;
							ArrayList<Cell> currRow=new ArrayList<>(C);
							for (int c=0;c<C;c++) currRow.add(null);
							table.add(row, currRow);
						}
						R+=opArgs.size();
						break;
					}
					case "IC": {
						for (int idx=0;idx<opArgs.size();idx++) {
							int col=opArgs.get(idx)-1+idx;
							for (ArrayList<Cell> row : table) row.add(col, null);
						}
						C+=opArgs.size();
						break;
					}
					case "EX": {
						int x1=opArgs.get(0)-1, y1=opArgs.get(1)-1;
						int x2=opArgs.get(2)-1, y2=opArgs.get(3)-1;
						Cell c1=table.get(x1).get(y1);
						Cell c2=table.get(x2).get(y2);
						table.get(x1).set(y1, c2);
						table.get(x2).set(y2, c1);
						break;
					}
				}

			}

			HashMap<String, String> moveMap=new HashMap<>();
			for (int r=0;r<R ;r++) for (int c=0;c<C;c++) {
				Cell cell=table.get(r).get(c);
				if (cell != null) {
					String key=coorToStr(null,cell.ix,cell.iy);
					String value=coorToStr("moved to ",r+1,c+1);
					moveMap.put(key, value);
				}
			}
			
			StringBuilder sb=new StringBuilder();
			if (testCase>1) sb.append('\n');
			sb.append("Spreadsheet #");
			sb.append(testCase++);
			sb.append('\n');
			int Q=Integer.parseInt(br.readLine());
			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				int fr=Integer.parseInt(st.nextToken());
				int fc=Integer.parseInt(st.nextToken());
				String key=coorToStr(null,fr,fc);

				sb.append("Cell data in ");
				sb.append(key);
				sb.append(" ");
				sb.append(moveMap.getOrDefault(key, "GONE"));
				sb.append('\n');
			}
			
			System.out.print(sb.toString());
		}
	}
}