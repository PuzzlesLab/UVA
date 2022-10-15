import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static HashMap<Integer,String> Words;
	private static ArrayList<Integer> [] AdjList;
	private static int DFSNumMax;
	private static int [] DFSNum;
	private static int [] DFSLow;
	private static boolean [] Visited;
	private static Stack<Integer> Stack;
	private static ArrayList<ArrayList<Integer>> SCC;

	private static void scc(int curr) {
		DFSNum[curr]=DFSLow[curr]=DFSNumMax++;
		Visited[curr]=true;
		Stack.push(curr);
		
		for (int next: AdjList[curr]) {
			if (DFSNum[next]==0) scc(next);
			if (Visited[next]) DFSLow[curr]=Math.min(DFSLow[curr],DFSLow[next]);
		}
		
		if (DFSNum[curr]==DFSLow[curr]) {
			ArrayList<Integer> components=new ArrayList<>();
			while (true) {
				int n=Stack.pop();
				Visited[n]=false;
				components.add(n);
				if (n==curr) break;
			}
			// Skip if word is not equivalent to other word.
			if (components.size()>1) SCC.add(components);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			
			HashMap<String,Integer> wordIdx=new HashMap<>();
			Words=new HashMap<>();
			ArrayList<String> [] lineWords=new ArrayList[N];
			int idxMax=0;
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				lineWords[n]=new ArrayList<>();
				while (st.hasMoreTokens()) lineWords[n].add(st.nextToken());
				for (int w=0;w<lineWords[n].size();w++) {
					s=lineWords[n].get(w);
					if (!wordIdx.containsKey(s)) {
						Words.put(idxMax,s);
						wordIdx.put(s,idxMax++);
					}
				}
			}

			AdjList=new ArrayList[idxMax];
			for (int i=0;i<idxMax;i++) AdjList[i]=new ArrayList<>();
			for (int n=0;n<N;n++) {
				for (int w=1;w<lineWords[n].size();w++) {
					int from=wordIdx.get(lineWords[n].get(0));
					int to=wordIdx.get(lineWords[n].get(w));
					if (from!=to) AdjList[from].add(to);
				}
			}

			DFSNumMax=1;
			DFSNum=new int [idxMax];
			DFSLow=new int [idxMax];
			Visited=new boolean [idxMax];
			Stack=new Stack<>();
			SCC=new ArrayList<>();
			for (int i=0;i<idxMax;i++) if (DFSNum[i]==0) scc(i);

			Visited=new boolean [idxMax];
			PriorityQueue<String> ans=new PriorityQueue<>();
			Stack=new Stack<>();
			for (int i=0;i<SCC.size();i++) { // Find all words connected by SCC.
				ArrayList<Integer> scc=SCC.get(i);
				int first=scc.get(0);
				if (Visited[first]) continue;

				Stack.push(first);
				Visited[first]=true;
				while (!Stack.isEmpty()) {
					int curr=Stack.pop();
					ans.offer(Words.get(curr));
					for (int next: AdjList[curr]) if (!Visited[next]) {
						Visited[next]=true;
						Stack.push(next);
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append(ans.size());
			sb.append('\n');
			while (!ans.isEmpty()) {
				sb.append(ans.poll());
				sb.append(' ');
			}
			if (sb.charAt(sb.length()-1)==' ') sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}