import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb=new StringBuilder();
		while ((s=br.readLine())!=null) {
			for (char c : s.toLowerCase().toCharArray()) sb.append(c);
			sb.append('\n');
		}
		s=sb.toString().replace("-\n", "");
		StringTokenizer st=new StringTokenizer(s);
		TreeSet<String> set=new TreeSet<>();
		
		
		while (st.hasMoreTokens()) {
			sb=new StringBuilder();
			s=st.nextToken();
			for (char c : s.toCharArray()) {
				if (Character.isAlphabetic(c) || c=='-') sb.append(c);
				else {
					if (sb.length()>0) set.add(sb.toString());
					sb.setLength(0);
				}
			}
			if (sb.length()>0) set.add(sb.toString());
		}
		
		sb=new StringBuilder();
		for (String ss : set) {
			sb.append(ss);
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

}
