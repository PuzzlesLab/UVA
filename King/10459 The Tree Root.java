import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

class Main {

	private static int N;
	private static ArrayList<Integer> [] AdjList;
	private static ArrayList<Integer> MaxNodes;
	private static int MaxDist;

	private static void findMaxNodesHelper(int from, boolean [] visited, int dist) {
		if (dist>MaxDist) {
			MaxNodes=new ArrayList<>();
			MaxNodes.add(from);
			MaxDist=dist;
		} else if (dist==MaxDist) MaxNodes.add(from);

		for (int i=0;i<AdjList[from].size();i++) {
			int next=AdjList[from].get(i);
			if (!visited[next]) {
				visited[next]=true;
				findMaxNodesHelper(next,visited,dist+1);
			}
		}
	}

	private static void findMaxNodes(int start) {
		boolean [] visited=new boolean [N];
		visited[start]=true;
		MaxNodes=new ArrayList<>();
		MaxDist=0;
		findMaxNodesHelper(start,visited,0); 
	}

	private static TreeSet<Integer> findBestNodes() {
		int [] adjCount=new int [N];
		for (int n=0;n<N;n++) adjCount[n]=AdjList[n].size();
		boolean [] removed=new boolean [N];
		
		TreeSet<Integer> ret=new TreeSet<>();
		int rem=N;
		while (rem>0) { // Disconnect most isolated nodes every iteration until empty.
			ret.clear();
			for (int n=0;n<N;n++) {
				if (adjCount[n]>1 || removed[n]) continue;
				
				removed[n]=true;
				rem--;
				ret.add(n);
			}
			for (int toRemove: ret) for (int n2: AdjList[toRemove]) adjCount[n2]--;
		}

		return ret; // Return the last removed isolated nodes
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (sc.hasNextInt()) {
			N=sc.nextInt();
			AdjList=new ArrayList [N];
			for (int n=0;n<N;n++) {
				AdjList[n]=new ArrayList<>();
				int M=sc.nextInt();
				for (int m=0;m<M;m++) {
					AdjList[n].add(sc.nextInt()-1);
				}
			}

			TreeSet<Integer> bestNodes=findBestNodes();
			
			TreeSet<Integer> worstNodes=new TreeSet<>();
			findMaxNodes(N/2); // start from a random node.
			worstNodes.addAll(MaxNodes);
			findMaxNodes(MaxNodes.get(0)); // start from a max node.
			worstNodes.addAll(MaxNodes);
			
			StringBuilder sb=new StringBuilder();
			sb.append("Best Roots  :");
			for (int n: bestNodes) {
				sb.append(' ');
				sb.append(n+1);
			}
			sb.append('\n');
			sb.append("Worst Roots :");
			for (int n: worstNodes) {
				sb.append(' ');
				sb.append(n+1);
			}
			System.out.println(sb.toString());
		}
	}

}
