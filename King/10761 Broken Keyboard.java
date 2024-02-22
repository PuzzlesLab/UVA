import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static void appendId(StringBuilder sb, int id) {
		if (id<10) {
			sb.append("     ");
			sb.append(id);
			sb.append("    ");
		} else if (id<100) {
			sb.append("    ");
			sb.append(id);
			sb.append("    ");
		} else {
			sb.append("   ");
			sb.append(id);
			sb.append("    ");
		}
	}
	
	private static void appendPrintable(StringBuilder sb, int value) {
		if (value<10) {
			sb.append("        ");
			sb.append(value);
			sb.append("       ");
		} else if (value<100) {
			sb.append("       ");
			sb.append(value);
			sb.append("       ");
		} else {
			sb.append("      ");
			sb.append(value);
			sb.append("       ");
		}
	}

	private static void appendAlpha(StringBuilder sb, String list) {
		sb.append(' ');
		sb.append(list);
		for (int i=list.length();i<28;i++) sb.append(' ');
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("finish")) {
			boolean [] broken=new boolean [128];
			for (int i=0;i<s.length();i++) broken[Character.toLowerCase(s.charAt(i))]=true;
			
			int printable=0;
			boolean [] exists=new boolean [128];
			do {
				s=br.readLine();
				boolean flag=true;
				for (int i=0;i<s.length();i++) flag&=!broken[Character.toLowerCase(s.charAt(i))];
				if (flag) {
					printable++;
					for (int i=0;i<s.length();i++) exists[Character.toLowerCase(s.charAt(i))]=true;
				}
				if (s.equals("END")) break;
			} while (true);

			StringBuilder extraList=new StringBuilder();
			for (int i='a';i<='z';i++) if (!exists[i] && !broken[i]) extraList.append((char)i);
			
			StringBuilder sb=new StringBuilder();
			if (testCase==1) {
				sb.append("+----------+----------------+-----------------------------+\n");
				sb.append("| Keyboard | # of printable | Additionally, the following |\n");
				sb.append("|          |      lines     |  letter keys can be broken  |\n");
				sb.append("+----------+----------------+-----------------------------+\n");
			}
			sb.append('|');
			appendId(sb,testCase++);
			sb.append('|');
			appendPrintable(sb,printable);
			sb.append('|');
			appendAlpha(sb,extraList.toString());
			sb.append("|\n");
			sb.append("+----------+----------------+-----------------------------+");
			System.out.println(sb);
		}
	}
}
