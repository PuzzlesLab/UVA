import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

class Main {

	private static ArrayList<String> Words=new ArrayList<>();
	private static HashSet<String> WordSet=new HashSet<>();

	private static boolean isMatch(String ref, String target) {
		int rLen=ref.length();
		int tLen=target.length();

		if (rLen==tLen) {
			int match=0;
			for (int i=0;i<rLen;i++) if (ref.charAt(i)==target.charAt(i)) match++;
			if (match==rLen-1) return true;
		} else if (rLen==tLen-1) {
			int tIdx=0;
			int match=0;
			for (int rIdx=0;rIdx<rLen && tIdx<tLen;rIdx++) {
				if (ref.charAt(rIdx)==target.charAt(tIdx)) {
					match++;
				} else rIdx--;
				tIdx++;
			}
			if (match>0 && match==rLen) return true;
		} else if (rLen==tLen+1) {
			int rIdx=0;
			int match=0;
			for (int tIdx=0;rIdx<rLen && tIdx<tLen;tIdx++) {
				if (ref.charAt(rIdx)==target.charAt(tIdx)) {
					match++;
				} else tIdx--;
				rIdx++;
			}
			if (match>0 && match==tLen) return true;
		}
		return false;
	}

	private static ArrayList<String> getMatches(String s) {
		ArrayList<String> result=new ArrayList<>();
		if (WordSet.contains(s)) {
			result.add(s);
			return result;
		}

		for (int i=0;i<Words.size();i++) if (isMatch(Words.get(i),s)) result.add(Words.get(i));
		return result;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			br.readLine();
			
			Words=new ArrayList<>();
			WordSet=new HashSet<>();
			String s;
			while (!(s=br.readLine()).equals("#")) {
				Words.add(s);
				WordSet.add(s);
			}

			StringBuilder sb=new StringBuilder();
			if (n>0) sb.append('\n');
			while (!(s=br.readLine()).equals("#")) {
				ArrayList<String> matches=getMatches(s);
				if (matches.size()==1 && matches.get(0).equals(s)) {
					sb.append(s);
					sb.append(" is correct");
				} else {
					sb.append(s);
					sb.append(':');
					for (int i=0;i<matches.size();i++) {
						sb.append(' ');
						sb.append(matches.get(i));
					}
				}
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}
