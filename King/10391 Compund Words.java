import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

class Main {

	private static boolean match(String s, HashSet<String> wordSet) {
		for (int i=1;i<s.length();i++) {
			String left=s.substring(0,i);
			if (!wordSet.contains(left)) continue;
			String right=s.substring(i,s.length());
			if (wordSet.contains(right)) return true;
		}
		return false;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> wordList=new ArrayList<>();
		HashSet<String> wordSet=new HashSet<>();
		String s;
		while ((s=br.readLine())!=null) {
			if (s.isEmpty()) break;
			wordList.add(s);
			wordSet.add(s);
		}
		
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<wordList.size();i++) {
			s=wordList.get(i);
			if (match(s,wordSet)) {
				sb.append(s);
				sb.append('\n');
			}
		}
		System.out.print(sb);
	}
}
