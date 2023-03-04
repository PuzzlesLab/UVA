import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.StringTokenizer;

class Main {

	private static class Node {
		HashMap<Node,Integer> edges=new HashMap<>();
	}

	private static class FSol {
		Node n;
		int dist;
		
		public FSol(Node n, int dist) {
			this.n=n;
			this.dist=dist;
		}
	}

	private static FSol findFurthest(Node n, HashSet<Node> visited) {
		FSol ans=new FSol(n,0);
		for (Entry<Node,Integer> e: n.edges.entrySet()) if (!visited.contains(e.getKey())) {
			visited.add(e.getKey());
			FSol curr=findFurthest(e.getKey(),visited);
			curr.dist+=e.getValue();
			if (ans==null || curr.dist>ans.dist) ans=curr;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		HashMap<Integer,Node> nodeMap=new HashMap<>();
		while (true) {
			String s=br.readLine();
			if (s==null || s.length()==0) {
				Node f1=nodeMap.values().iterator().next();
				HashSet<Node> visited=new HashSet<>();
				visited.add(f1);
				FSol sol=findFurthest(f1,visited);

				visited.clear();
				visited.add(sol.n);
				sol=findFurthest(sol.n,visited);
				
				System.out.println(sol.dist);

				nodeMap=new HashMap<>();
				if (s==null) break;
				else continue;
			}
			StringTokenizer st=new StringTokenizer(s);
			int n1Idx=Integer.parseInt(st.nextToken());
			Node n1=nodeMap.get(n1Idx);
			if (n1==null) {
				n1=new Node();
				nodeMap.put(n1Idx,n1);
			}
			int n2Idx=Integer.parseInt(st.nextToken());
			Node n2=nodeMap.get(n2Idx);
			if (n2==null) {
				n2=new Node();
				nodeMap.put(n2Idx,n2);
			}
			int dist=Integer.parseInt(st.nextToken());
			n1.edges.put(n2,dist);
			n2.edges.put(n1,dist);
		}
	}

}
