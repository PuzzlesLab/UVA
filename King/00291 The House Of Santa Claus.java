import java.util.ArrayList;

class Main {

	private static ArrayList<String> Solutions;
	private static int [][] AdjList;
	private static boolean [][] Visited;

	private static void dfs(int [] nodes, int nodeCount) {
		if (nodeCount==nodes.length) {
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<nodes.length;i++) sb.append(nodes[i]);
			Solutions.add(sb.toString());
		} else {
			int curr=nodes[nodeCount-1];
			for (int i=0;i<AdjList[curr].length;i++) {
				int next=AdjList[curr][i];
				if (!Visited[curr][next]) {
					Visited[curr][next]=true;
					Visited[next][curr]=true;
					nodes[nodeCount]=next;
					dfs(nodes,nodeCount+1);
					Visited[curr][next]=false;
					Visited[next][curr]=false;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		AdjList=new int [][] {
			{},
			{2,3,5},
			{1,3,5},
			{1,2,4,5},
			{3,5},
			{1,2,3,4},
		};
		Visited=new boolean [6][6];
		Solutions=new ArrayList<>();
		
		int [] nodes=new int [9];
		nodes[0]=1;
		dfs(nodes,1);

		StringBuilder sb=new StringBuilder();
		for (int i=0;i<Solutions.size();i++) {
			sb.append(Solutions.get(i));
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}

}
