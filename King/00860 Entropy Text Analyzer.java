import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1"));
		HashMap<String, Integer> wordOccur=new HashMap<>();
		HashSet<Character> specialSet=new HashSet<>();
		specialSet.add(',');
		specialSet.add('.');
		specialSet.add(':');
		specialSet.add(';');
		specialSet.add('!');
		specialSet.add('?');
		specialSet.add('"');
		specialSet.add('(');
		specialSet.add(')');
		specialSet.add('\t');
		specialSet.add(' ');
		specialSet.add('\n');
		
		String s;
 		while (!(s=br.readLine()).equals("****END_OF_INPUT****")) {
			wordOccur.clear();
			int lambda=0;
			while (true) {
				StringBuilder sb=new StringBuilder();
				for (char c : (s+"\n").toCharArray()) {
					if (specialSet.contains(c)) {
						if (sb.length()>0) {
							String word=sb.toString();
							wordOccur.put(word, wordOccur.getOrDefault(word,0)+1);
							sb.setLength(0);
							lambda++;
						}
					} else sb.append(Character.toLowerCase(c));
				}
				s=br.readLine();
				if (s.equals("****END_OF_TEXT****")) break;
			}
			
			double et=0.0;
			for (int c : wordOccur.values()) {
				et=et+c*(Math.log10(lambda) - Math.log10(c));
			}
			et=et/lambda;
			double erel=(et/Math.log10(lambda))*100;
			
			et=(int)((et+0.05)*10)/10.0;

			System.out.println(lambda+" "+et+" "+(int)(erel+0.5));
		}
	}

}
