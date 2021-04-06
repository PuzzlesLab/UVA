import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	
	public static class Row {
		HashMap<String, Character> locks=new HashMap<>();
		private boolean exclusive=false;
		
		public boolean canEnter(String trID, char mode) {
			if (mode=='X') return this.locks.size() == 0 || (this.locks.size() == 1 && this.locks.containsKey(trID));
			if (locks.containsKey(trID)) return true;
			if (this.exclusive) return false;
			return true;
		}

		public void addLock(String trID, char mode) {
			if (!this.locks.containsKey(trID) || mode=='X') locks.put(trID, mode);
			if (mode=='X') this.exclusive=true;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());

		for (int testCase=0;testCase<testCaseCount;testCase++) {
			HashMap<String, Row> rowMap=new HashMap<>();
			HashSet<String> blockedTRID=new HashSet<>();
			br.readLine(); //empty
			String s;
			StringBuilder sb=new StringBuilder();
			if (testCase>0) sb.append('\n');
			while (!(s=br.readLine()).equals("#")) {
				StringTokenizer st=new StringTokenizer(s);
				char mode=st.nextToken().charAt(0);
				String trID=st.nextToken();
				String rowID=st.nextToken();
				
				if (blockedTRID.contains(trID)) sb.append("IGNORED\n");
				else {
					if (!rowMap.containsKey(rowID)) rowMap.put(rowID,new Row());
					Row row=rowMap.get(rowID);
					if (row.canEnter(trID,mode)) {
						row.addLock(trID,mode);
						sb.append("GRANTED\n");
					} else {
						blockedTRID.add(trID);
						sb.append("DENIED\n");
					}
				}
			}
			System.out.print(sb.toString());
		}

		
	}
}
