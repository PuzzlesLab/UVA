import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Node {
		LinkedList<Node> adjList=new LinkedList<>();
		boolean visited;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int W=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());
			
			Node [][] nodes=new Node [H*2][W+1];
			for (int h=0;h<nodes.length;h++) for (int w=0;w<nodes[h].length;w++) nodes[h][w]=new Node();
			
			for (int h=0;h<nodes.length;h+=2) {
				s=br.readLine();
				for (int w=0;w<W;w++) {
					char c=s.charAt(w);
					if (c=='\\') {
						if (h+1<nodes.length && w+1<nodes[h+1].length) {
							nodes[h][w].adjList.add(nodes[h+1][w+1]);
							nodes[h+1][w+1].adjList.add(nodes[h][w]);
						}
						if (h+2<nodes.length) {
							nodes[h+1][w].adjList.add(nodes[h+2][w]);
							nodes[h+2][w].adjList.add(nodes[h+1][w]);
						}
					} else if (c=='/') {
						if (h+1<nodes.length) {
							nodes[h][w].adjList.add(nodes[h+1][w]);
							nodes[h+1][w].adjList.add(nodes[h][w]);
						}
						if (h+2<nodes.length && w+1<nodes[h+1].length) {
							nodes[h+1][w+1].adjList.add(nodes[h+2][w]);
							nodes[h+2][w].adjList.add(nodes[h+1][w+1]);
						}
					}
				}
			}

			int cycleCount=0;
			int longestCycle=0;
			for (int h=0;h<nodes.length;h++) for (int w=0;w<nodes[h].length;w++) if (!nodes[h][w].visited) {
				Node curr=nodes[h][w];
				curr.visited=true;
				int count=1;

				while (true) {
					Node temp=null;
					while (!curr.adjList.isEmpty()) {
						Node next=curr.adjList.removeFirst();
						if (!next.visited) {
							temp=next;
							break;
						}
					}
					if (temp==null) break;

					curr=temp;
					curr.visited=true;
					count++;
				}

				if (count>=4 && nodes[h][w].adjList.contains(curr)) {
					longestCycle=Math.max(count,longestCycle);
					cycleCount++;
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Maze #");
			sb.append(tc++);
			sb.append(":\n");
			if (cycleCount>0) {
				sb.append(cycleCount);
				sb.append(" Cycles; the longest has length ");
				sb.append(longestCycle);
				sb.append('.');
			} else sb.append("There are no cycles.");
			sb.append('\n');
			System.out.println(sb.toString());
		}
	}

}
