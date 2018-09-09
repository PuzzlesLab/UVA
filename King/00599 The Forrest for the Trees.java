import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			boolean [][] edges=new boolean [26][26];
			String s;
			while ((s=br.readLine()).charAt(0)!='*') {
				edges[s.charAt(1)-'A'][s.charAt(3)-'A']=true;
				edges[s.charAt(3)-'A'][s.charAt(1)-'A']=true;
			}
			
			boolean [] nodeExists=new boolean [26];
			StringTokenizer st=new StringTokenizer(br.readLine(), ",");
			while (st.hasMoreTokens()) nodeExists[st.nextToken().charAt(0)-'A']=true;
			
			int acronsCount=0, treeCount=0;
			boolean [] visited=new boolean[26];
			for (int i=0;i<26;i++) if (nodeExists[i] && !visited[i]) {
				int edgesCount=0;
				for (int e=0;e<26;e++) if (edges[i][e]) edgesCount++;
				if (edgesCount==0) {
					visited[i]=true;
					acronsCount++;
				} else {
					boolean tree=true;
					LinkedList<Integer> queue=new LinkedList<>();
					queue.add(i);
					
					boolean [][] edgeVisited=new boolean[26][26];
					while (queue.size()>0) {
						int currI=queue.removeFirst();
						if (visited[currI]) {
							tree=false;
							break;
						} else {
							visited[currI]=true;
							for (int e=0;e<26;e++) if (edges[currI][e] && !edgeVisited[currI][e] && !edgeVisited[e][currI]) {
								queue.add(e);
								edgeVisited[currI][e]=true;
								edgeVisited[e][currI]=true;
							}
						}
					}
					if (tree) treeCount++;
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("There are ");
			sb.append(treeCount);
			sb.append(" tree(s) and ");
			sb.append(acronsCount);
			sb.append(" acorn(s).");
			System.out.println(sb.toString());
		}
		
	}

}
