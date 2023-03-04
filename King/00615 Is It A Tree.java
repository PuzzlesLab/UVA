import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

class Main {

	private static class Node {
		int incoming;
		HashSet<Node> adjList=new HashSet<>();
		boolean filled;
	}

	private static void fillNodes(Node n) {
		n.filled=true;
		for (Node next: n.adjList) if (!next.filled) fillNodes(next);
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		HashMap<Integer,Node> nodeMap=new HashMap<>();
		int testCase=1;
		while (true) {
			int from=sc.nextInt();
			int to=sc.nextInt();
			if (from<0 || to<0) break;
			if (from==0 || to==0) {
				int zeroCount=0;
				int exceed1Count=0;
				for (Node n: nodeMap.values()) {
					if (n.incoming==0) zeroCount++;
					else if (n.incoming>1) exceed1Count++;
				}
				int treeCount=0;
				for (Node n: nodeMap.values()) if (!n.filled) {
					fillNodes(n);
					treeCount++;
				}
				
				boolean ans=(zeroCount==1 && exceed1Count==0 && treeCount==1) || treeCount==0;
				System.out.printf("Case %d is%s a tree.\n",testCase++,ans?"":" not");

				nodeMap=new HashMap<>();
				continue;
			}
			
			Node n1=nodeMap.get(from);
			if (n1==null) {
				n1=new Node();
				nodeMap.put(from,n1);
			}
			Node n2=nodeMap.get(to);
			if (n2==null) {
				n2=new Node();
				nodeMap.put(to,n2);
			}
			n2.incoming++;
			n1.adjList.add(n2);
			n2.adjList.add(n1);
		}
	}

}
