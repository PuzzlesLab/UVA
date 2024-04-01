import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static final int [] CharNum=new int [128];

	private static class Entry {
		String fName, lName;
		String nameNum;
		String num;
		
		public Entry(StringTokenizer st) {
			this.fName=st.nextToken();
			this.lName=st.nextToken();
			this.num=st.nextToken();
			
			StringBuilder sb=new StringBuilder();
			sb.append(CharNum[Character.toUpperCase(this.fName.charAt(0))]);
			for (int i=0;i<this.lName.length();i++) sb.append(CharNum[Character.toUpperCase(this.lName.charAt(i))]);
			this.nameNum=sb.toString();
		}
		
		public String toString() {
			return this.nameNum;
		}
	}

	public static void main (String [] args) throws Exception {
		CharNum['A']=2; CharNum['B']=2; CharNum['C']=2;
		CharNum['D']=3; CharNum['E']=3; CharNum['F']=3;
		CharNum['G']=4; CharNum['H']=4; CharNum['I']=4;
		CharNum['J']=5; CharNum['K']=5; CharNum['L']=5;
		CharNum['M']=6; CharNum['N']=6; CharNum['O']=6;
		CharNum['P']=7; CharNum['Q']=7; CharNum['R']=7; CharNum['S']=7;
		CharNum['T']=8; CharNum['U']=8; CharNum['V']=8;
		CharNum['W']=9; CharNum['X']=9; CharNum['Y']=9; CharNum['Z']=9;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		ArrayList<Entry> entries=new ArrayList<>();
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			if (st.countTokens()==3) {
				entries.add(new Entry(st));
				continue;
			}

			int exactCount=0;
			for (int i=0;i<entries.size();i++) if (entries.get(i).num.equals(s)) exactCount++;
			if (exactCount==1) {
				System.out.println(s);
				continue;
			}
			
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<entries.size();i++) if (entries.get(i).nameNum.startsWith(s)) {
				sb.append(entries.get(i).num);
				sb.append(' ');
			}
			if (sb.length()>0) {
				sb.setLength(sb.length()-1);
				System.out.println(sb);
				continue;
			}

			System.out.println(0);
		}
	}
}