import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Word {
		String s;
		String sorted;
		
		public Word(String s) {
			this.s=s;
			this.sorted=sortWord(s);
		}

		public int length() {
			return s.length();
		}
		
		public char charAt(int i) {
			return s.charAt(i);
		}
	}

	private static String sortWord(String w) {
		int [] occur=new int [128];
		for (int i=0;i<w.length();i++) occur[w.charAt(i)]++;
		StringBuilder sb=new StringBuilder();
		for (int i='a';i<='z';i++) for (int i2=0;i2<occur[i];i2++) sb.append((char)i);
		return sb.toString();
	}

	private static String find(ArrayList<Word> words, String w) {
		String wSorted=sortWord(w);
		for (int i=0;i<words.size();i++) {
			Word check=words.get(i);
			if (check.length()!=w.length()) continue;

			char c1=check.charAt(0);
			char c2=check.charAt(check.length()-1);
			char w1=w.charAt(0);
			char w2=w.charAt(w.length()-1);
			if (c1!=w1 || c2!=w2) continue;

			if (check.sorted.equals(wSorted)) return check.s;
		}
		return w;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			ArrayList<Word> words=new ArrayList<>();
			while (st.hasMoreTokens()) words.add(new Word(st.nextToken()));

			StringBuilder sb=new StringBuilder();
			st=new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				sb.append(find(words,st.nextToken()));
				sb.append(' ');
			}
			if (sb.length()>0) sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}
}