import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	private static class Mapping implements Comparable<Mapping> {
		char c;
		String code;
		
		public int compareTo(Mapping m) {
			return this.c-m.c;
		}
	}

	private static List<String> Ans;

	private static void find(Mapping [] mappings, LinkedList<Mapping> selected, StringBuilder curr, String match) {
		if (curr.length()>match.length()) return;

		if (curr.toString().equals(match)) {
			StringBuilder currAns=new StringBuilder();
			for (Mapping mapping: selected) currAns.append(mapping.c);
			Ans.add(currAns.toString());
		} else {
			for (Mapping mapping: mappings) {
				// Original
				curr.append(mapping.code);
				if (match.startsWith(curr.toString())) {
					selected.addLast(mapping);
					find(mappings,selected,curr,match);
					selected.removeLast();
				}
				curr.setLength(curr.length()-mapping.code.length());

				// Precede 0
				curr.append('0');
				curr.append(mapping.code);
				if (match.startsWith(curr.toString())) {
					selected.addLast(mapping);
					find(mappings,selected,curr,match);
					selected.removeLast();
				}
				curr.setLength(curr.length()-mapping.code.length()-1);
			}
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Mapping [] mappings=new Mapping[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				mappings[n]=new Mapping();
				mappings[n].c=st.nextToken().charAt(0);
				mappings[n].code=st.nextToken();
			}
			Arrays.sort(mappings);
			String match=br.readLine();

			Ans=new ArrayList<>();
			find(mappings,new LinkedList<>(),new StringBuilder(),match);
			Collections.sort(Ans);

			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(testCase++);
			sb.append('\n');
			for (int i=0;i<Math.min(100, Ans.size());i++) {
				sb.append(Ans.get(i));
				sb.append('\n');
			}
			System.out.println(sb);
		}
	}

}