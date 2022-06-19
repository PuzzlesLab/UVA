import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

class Main {

	private static class Edge {
		int p1, p2, cost;
		
		public Edge(int p1, int p2, int cost) {
			this.p1=p1;
			this.p2=p2;
			this.cost=cost;
		}
		
		public String toString() {
			return this.cost+"";
		}
	}

	private static ArrayList<Edge> [] NodeEdges;

	public static boolean findRoads(int curr, int end, boolean [] visited, LinkedList<Edge> roads) {
		if (curr==end) return true;

		visited[curr]=true;
		for (Edge road: NodeEdges[curr]) if (!roads.contains(road)) {
			int next=road.p1==curr?road.p2:road.p1;
			if (!visited[next] && findRoads(next,end,visited,roads)) {
				roads.addFirst(road);
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCaseCount=sc.nextInt();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			if (testCase>0) System.out.println();

			int iN=sc.nextInt();
			int iM=sc.nextInt();

			NodeEdges=new ArrayList[iN];
			for (int i=0;i<NodeEdges.length;i++) NodeEdges[i]=new ArrayList<>();
			for (int m=0;m<iM;m++) {
				Edge edge=new Edge(sc.nextInt()-1,sc.nextInt()-1,sc.nextInt());
				NodeEdges[edge.p1].add(edge);
				NodeEdges[edge.p2].add(edge);
			}

			int K=sc.nextInt();
			for (int k=0;k<K;k++) {
				int id1=sc.nextInt()-1;
				int id2=sc.nextInt()-1;
				int cost=sc.nextInt();

				LinkedList<Edge> roads=new LinkedList<>();
				// Find the only path (as stated by question) & record down the used edges.
				findRoads(id1,id2,new boolean [iN],roads);
				int testCost=cost;
				for (Edge road: roads) testCost-=road.cost; // Calc unused cost.

				//Just good!
				if (testCost==0) System.out.printf("Yes %d\n", roads.size());
				// Unique path too high cost
				// We need to travel useless edges (go and back) to spend the cost, so the remaining cost should be even.
				// Only 1 edge but we can't travel it for multiple times!
				else if (testCost<0 || testCost%2==1 || roads.size()<=1) System.out.println("No");
				else {
					testCost/=2; // Half of path (mirror)
					roads.removeLast(); // Remove last edge from possible edges since can't travel for more than once.
					
					int [] costs=new int [roads.size()];
					int idx=0;
					for (Edge road: roads) costs[idx++]=road.cost;

					int [] dp=new int [testCost+1];
					final int NOT_FOUND=100000;
					Arrays.fill(dp,NOT_FOUND);
					dp[0]=0;
					// Possible to take the same edge for multiple times.
					for (int i=0;i<costs.length;i++) for (int i2=0;i2<=testCost-costs[i];i2++) {
						if (dp[i2]<NOT_FOUND) dp[i2+costs[i]]=Math.min(dp[i2]+1,dp[i2+costs[i]]);
					}
					
					if (dp[testCost]<NOT_FOUND) System.out.printf("Yes %d\n", dp[testCost]*2+roads.size()+1);
					else System.out.println("No");
				}
			}
		}
	}

}
