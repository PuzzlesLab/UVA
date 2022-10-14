import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] AdjList;
	private static int [] DfsNum;
	private static int [] DfsLow;
	private static boolean [] Visited;
	private static int DfsMax;
	private static int SCC;
	private static Stack<Integer> Stack;

	private static void scc(int curr) {
		DfsNum[curr]=DfsMax++;
		DfsLow[curr]=DfsNum[curr];
		Visited[curr]=true;
		Stack.push(curr);
		
		for (int next: AdjList[curr]) {
			if (DfsNum[next]==0) scc(next);
			if (Visited[next]) DfsLow[curr]=Math.min(DfsLow[curr], DfsLow[next]);
		}
		
		if (DfsNum[curr]==DfsLow[curr]) {
			SCC++;
			while (true) {
				int n=Stack.pop();
				Visited[n]=false;
				if (n==curr) break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int P=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			if (P==0 && T==0) break;
			
			HashMap<String, Integer> nameIdx=new HashMap<>();
			AdjList=new ArrayList [P];
			String [] names=new String[P];
			for (int p=0;p<P;p++) {
				names[p]=br.readLine();
				nameIdx.put(names[p] ,p);
				AdjList[p]=new ArrayList<>();
			}

			for (int t=0;t<T;t++) AdjList[nameIdx.get(br.readLine())].add(nameIdx.get(br.readLine()));

			DfsNum=new int [P];
			DfsLow=new int [P];
			Visited=new boolean [P];
			DfsMax=1;
			SCC=0;
			Stack=new Stack<>();
			for (int p=0;p<P;p++) if (DfsNum[p]==0) scc(p);
			
			System.out.println(SCC);
		}
	}

}