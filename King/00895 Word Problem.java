import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	
	private static HashMap<String, Integer> Dict=new HashMap<>();
	private static HashSet<String> Generated=new HashSet<>();
	private static int Answer=0;
	
	public static void recurse(char [] chars, boolean [] taken, StringBuilder curr) {
		for (int i=0;i<chars.length;i++) if (!taken[i]) {
			taken[i]=true;
			StringBuilder sb=new StringBuilder(curr);
			sb.append(chars[i]);
			String s=sb.toString();
			if (!Generated.contains(s) && Dict.containsKey(s)) {
				Generated.add(sb.toString());
				Answer+=Dict.get(s);
			}
			recurse(chars,taken,sb);
			taken[i]=false;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("#")) Dict.put(s, Dict.getOrDefault(s,0)+1);
		while (!(s=br.readLine()).equals("#")) {
			StringTokenizer st=new StringTokenizer(s);
			char [] chars=new char[st.countTokens()];
			for (int i=0;st.hasMoreTokens();i++) chars[i]=st.nextToken().charAt(0);
			Generated=new HashSet<>();
			Answer=0;
			recurse(chars,new boolean[chars.length],new StringBuilder());
			System.out.println(Answer);
		}

	}

}