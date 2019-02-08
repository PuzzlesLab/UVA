import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	public static ArrayList<String> solution=new ArrayList<>();
	
	public static void dfs(int [] nodes, boolean [] visited, HashSet<Integer> [] edgeList, int curr) {
		if (curr==nodes.length-1) {
			StringBuilder sb=new StringBuilder();
			sb.append('(');
			for (int n : nodes) {
				sb.append(n+1);
				sb.append(',');
			}
			sb.setLength(sb.length()-1);
			sb.append(')');
			solution.add(sb.toString());
		} else {
			for (int next : edgeList[nodes[curr]]) if (!visited[next]) {
				visited[next]=true;
				nodes[curr+1]=next;
				dfs(nodes,visited,edgeList,curr+1);
				visited[next]=false;
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int T=0;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			HashSet<Integer> [] edgeList=new HashSet [N];
			for (int n=0;n<N;n++) edgeList[n]=new HashSet<>();
			
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				for (int n2=0;n2<N;n2++) if (Integer.parseInt(st.nextToken())==1) edgeList[n].add(n2);
			}
			
			solution.clear();
			if (N-1>=M) {
				boolean [] visited=new boolean[N];
				int [] nodes=new int [M+1];
				nodes[0]=0;
				visited[0]=true;
				dfs(nodes,visited,edgeList,0);
			}
			if (solution.size()==0) solution.add("no walk of length "+M);
			
			if (T++>0) System.out.println();
			for (String sol : solution) System.out.println(sol);
			
			br.readLine();
		}
	}

}
