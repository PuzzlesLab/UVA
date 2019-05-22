import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	private static class Edge {
		Edge prev;
		String dest;
		public Edge(Edge prev, String dest) { this.prev=prev; this.dest=dest;}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		HashSet<String> dictionary=new HashSet<>();
		HashMap<String, char []> dictionaryChars=new HashMap<>();
		while ((s=br.readLine()).length()>0) {
			dictionary.add(s);
			dictionaryChars.put(s,s.toCharArray());
		}
		
		boolean isFirst=true;
		while ((s=br.readLine())!=null) {
			if (s.trim().length()==0) break;
			StringTokenizer st=new StringTokenizer(s);
			String start=st.nextToken();
			String end=st.nextToken();
			Edge solution=null;

			if (dictionary.contains(start) && dictionary.contains(end) && start.length()==end.length()) {
				HashSet<String> visited=new HashSet<>();
				LinkedList<Edge> queue=new LinkedList<>();
				queue.add(new Edge(null, start));
				visited.add(start);
				while (!queue.isEmpty()) {
					Edge e=queue.removeFirst();
					if (e.dest.equals(end)) {
						solution = e;
						break;
					}
					//For the given test data, this is faster than iterating through all the words (TLE) / precomputing the edges (~90% slower).
					char [] tempChars=Arrays.copyOf(dictionaryChars.get(e.dest), dictionaryChars.get(e.dest).length);
					for (int i=0;i<tempChars.length;i++) {
						for (int i2=0;i2<26;i2++) {
							tempChars[i]=(char)((((tempChars[i]-'a')+1)%26)+'a');
							String next=new String(tempChars);
							if (dictionary.contains(next) && !visited.contains(next)) {
								visited.add(next);
								queue.addLast(new Edge(e, next));
							}
						}
					}
				}
			}
			
			if (!isFirst) System.out.println();
			else isFirst=false;
			
			if (solution==null) System.out.println("No solution.");
			else {
				LinkedList<String> stk=new LinkedList<>();
				while (solution!=null) {
					stk.addFirst(solution.dest);
					solution=solution.prev;
				}
				StringBuilder sb=new StringBuilder();
				for (String word : stk) {
					sb.append(word);
					sb.append('\n');
				}
				System.out.print(sb.toString());
			}
		}
	}

}
