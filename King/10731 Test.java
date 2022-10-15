import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

	private static ArrayList<Integer> [] AdjList;
	private static TreeSet<Integer> Choices;
	private static int [] DFSNum;
	private static int [] DFSLow;
	private static int DFSNumMax;
	private static boolean [] Visited;
	private static Stack<Integer> Stack;
	private static PriorityQueue<String> SCC;

	private static void scc (int curr) {
		DFSNum[curr]=DFSLow[curr]=DFSNumMax++;
		Visited[curr]=true;
		Stack.push(curr);
		
		for (int next: AdjList[curr]) {
			if (DFSNum[next]==0) scc(next);
			if (Visited[next]) DFSLow[curr]=Math.min(DFSLow[curr],DFSLow[next]);
		}

		if (DFSNum[curr]==DFSLow[curr]) {
			PriorityQueue<Character> components=new PriorityQueue<>();
			while (true) {
				int n=Stack.pop();
				Visited[n]=false;
				components.offer((char)(n+'A'));
				if (n==curr) break;
			}
			StringBuilder sb=new StringBuilder();
			while (!components.isEmpty()) {
				sb.append(components.poll());
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			SCC.offer(sb.toString());
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);

			AdjList=new ArrayList [26];
			Choices=new TreeSet<>();
			for (int i=0;i<AdjList.length;i++) AdjList[i]=new ArrayList<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int [] nodes=new int [6];
				for (int i=0;i<nodes.length;i++) {
					nodes[i]=st.nextToken().charAt(0)-'A';
					Choices.add(nodes[i]);
				}
				for (int i=0;i<nodes.length-1;i++) if (nodes[i]!=nodes[nodes.length-1]) AdjList[nodes[i]].add(nodes[nodes.length-1]);
			}

			DFSNumMax=1;
			DFSNum=new int [26];
			DFSLow=new int [26];
			Visited=new boolean [26];
			Stack=new Stack<>();
			SCC=new PriorityQueue<>();
			for (int n: Choices) if (DFSNum[n]==0) scc(n);
			
			StringBuilder sb=new StringBuilder();
			if (tc++>1) sb.append('\n');
			while (!SCC.isEmpty()) {
				sb.append(SCC.poll());
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}
