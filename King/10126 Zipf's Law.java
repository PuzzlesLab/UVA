import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

class Main {

	private static void compute(String s, HashMap<String,Integer> count) {
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			if (Character.isAlphabetic(c)) sb.append(c);
			else if (sb.length()>0) {
				String word=sb.toString().toLowerCase();
				count.put(word,count.getOrDefault(word, 0)+1);
				sb.setLength(0);
			}
		}
		if (sb.length()>0) {
			String word=sb.toString().toLowerCase();
			count.put(word,count.getOrDefault(word, 0)+1);
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			
			ArrayList<String> lines=new ArrayList<>();
			while (!(s=br.readLine()).equals("EndOfText")) lines.add(s);
			
			HashMap<String,Integer> count=new HashMap<>();
			for (int i=0;i<lines.size();i++) {
				s=lines.get(i);
				compute(s,count);
			}
			
			ArrayList<String> ans=new ArrayList<>();
			for (Entry<String,Integer> entry: count.entrySet()) if (entry.getValue()==N) ans.add(entry.getKey());
			Collections.sort(ans);
			
			StringBuilder sb=new StringBuilder();
			if (tc++>1) sb.append('\n');
			if (ans.isEmpty()) {
				sb.append("There is no such word.\n");
			} else {
				for (int i=0;i<ans.size();i++) {
					sb.append(ans.get(i));
					sb.append('\n');
				}
			}

			if (sb.length()>0) sb.setLength(sb.length()-1);
			System.out.println(sb);
		}
	}
}