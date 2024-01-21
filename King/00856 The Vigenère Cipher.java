import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	private static final String [] DIGITS= {
		"nine","eight","seven", "six", "five",
		"four","three","two", "one", "zero"
	};
	private static ArrayList<String> [] POSS_BY_LENGTH;
	private static char [][] CH_BY_EP=new char[26][26];

	private static void init() {
		POSS_BY_LENGTH=new ArrayList [16];
		for (int i=0;i<POSS_BY_LENGTH.length;i++) POSS_BY_LENGTH[i]=new ArrayList<>();
		for (int i=0;i<DIGITS.length;i++) {
			for (int i2=0;i2<DIGITS.length;i2++) {
				for (int i3=0;i3<DIGITS.length;i3++) {
					StringBuilder sb=new StringBuilder();
					sb.append(DIGITS[i]);
					sb.append(DIGITS[i2]);
					sb.append(DIGITS[i3]);
					POSS_BY_LENGTH[sb.length()].add(sb.toString());
				}
			}
		}
		
		for (int c=0;c<26;c++) for (int p=0;p<26;p++) {
			int e=(c+1+p)%26;
			CH_BY_EP[e][p]=(char)(c+'A');
		}
	}

	private static String lookup(String e, String p) {
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<e.length();i++) sb.append(CH_BY_EP[e.charAt(i)-'A'][p.charAt(i)-'a']);
		return sb.toString();
	};

	public static void main (String [] args) throws Exception {
		init();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean first=true;
		String s;
		while ((s=br.readLine())!=null) {
			StringBuilder sb=new StringBuilder();
			if (first) first=false;
			else sb.append('\n');
			
			int len=s.length();
			if (len<POSS_BY_LENGTH.length) {
				for (int i=0;i<POSS_BY_LENGTH[len].size();i++) {
					String target=POSS_BY_LENGTH[len].get(i);
					sb.append(lookup(s,target));
					sb.append(" -> ");
					sb.append(target);
					sb.append('\n');
				}
			}
			System.out.print(sb.toString());
		}
	}

}
