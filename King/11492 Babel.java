import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Node {
		String lang1, lang2, word;
		char initial;
		int len;
		
		public Node(String lang1, String lang2, String word) {
			this.lang1=lang1;
			this.lang2=lang2;
			this.word=word;
			this.initial=word.charAt(0);
			this.len=word.length();
		}
		
	}
	
	public static class Edge implements Comparable<Edge> {
		Node next;
		int len;
		String lang;
		
		public Edge(Node next, String lang, int len) {
			this.next=next;
			this.len=len;
			this.lang=lang;
		}
		public int compareTo(Edge e) {
			return this.len-e.len;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int M=Integer.parseInt(br.readLine());
			if (M==0) break;
			StringTokenizer st=new StringTokenizer(br.readLine());
			String O=st.nextToken();
			String D=st.nextToken();
			
			HashMap<String, ArrayList<Node>> langNodes=new HashMap<>();
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				String l1=st.nextToken();
				String l2=st.nextToken();
				String w=st.nextToken();
				if (!langNodes.containsKey(l1)) langNodes.put(l1, new ArrayList<>());
				langNodes.get(l1).add(new Node(l1,l2,w));
				if (!langNodes.containsKey(l2)) langNodes.put(l2, new ArrayList<>());
				langNodes.get(l2).add(new Node(l2,l1,w));
			}
			
			int min=Integer.MAX_VALUE;
			for (Node n : langNodes.getOrDefault(O, new ArrayList<>())) {
				HashMap<String, Integer> lowestDist=new HashMap<>();
				for (String key : langNodes.keySet()) lowestDist.put(key, Integer.MAX_VALUE);
				
				PriorityQueue<Edge> q=new PriorityQueue<>();
				q.offer(new Edge(n,n.lang2,n.len));
				while (!q.isEmpty()) {
					Edge e=q.poll();
					if (e.lang.equals(D)) {
						min=Math.min(min, e.len);
						break;
					}
					for (Node nextNode : langNodes.getOrDefault(e.next.lang2, new ArrayList<>())) {
						if (nextNode.initial!=e.next.initial) {
							int nextDist=e.len+nextNode.len;
							if (lowestDist.get(nextNode.lang2)>nextDist) {
								lowestDist.put(nextNode.lang2, nextDist);
								q.offer(new Edge(nextNode,nextNode.lang2,nextDist));
							}
						}
					}
				}
			}
			
			System.out.println(min!=Integer.MAX_VALUE ? min : "impossivel");
		}
	}

}