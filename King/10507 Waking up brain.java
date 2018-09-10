import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main {

/*
Extra tests:
2
0
ABC

0
1
ABC
CD

-1
1
ABC
AB

3
2
ABC
AB
BA

4
2
ABC
AB
BC
*/
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s), M=Integer.parseInt(br.readLine());
			HashSet<Integer> initialAreas=new HashSet<>();
			for (char c : br.readLine().trim().toCharArray()) initialAreas.add((int)(c-'A'));
			boolean [][] edges=new boolean [26][26];
			
			HashSet<Integer> allAreas=new HashSet<>();
			allAreas.addAll(initialAreas);
			int [] edgeCount=new int [26];
			for (int m=0;m<M;m++) {
				s=br.readLine().trim();
				int x=s.charAt(0)-'A', y=s.charAt(1)-'A';
				allAreas.add(x); allAreas.add(y);
				if (x!=y) {
					if (!edges[x][y]) {
						edgeCount[x]++;
						edges[x][y]=true;
					}
					
					if (!edges[y][x]) {
						edgeCount[y]++;
						edges[y][x]=true;
					}
				}
			}
			br.readLine();//empty.
			
			if (allAreas.size()<N) {
				System.out.println("THIS BRAIN NEVER WAKES UP");
				continue;
			}
			allAreas.removeAll(initialAreas);
			int moreThanThreeEdges=0;
			for (int area : allAreas) 
				if (edgeCount[area]>=3) moreThanThreeEdges++;
				else break;
			
			if (moreThanThreeEdges<N-initialAreas.size()) System.out.println("THIS BRAIN NEVER WAKES UP");
			else {
				int [] awakeNeighboursCount=new int [26];
				int year=0;
				boolean [][] edgeVisited=new boolean [26][26];
				while (true) {
					for (int area : initialAreas) for (int next : allAreas) if (edges[area][next] && !edgeVisited[area][next]) {
						awakeNeighboursCount[next]++;
						edgeVisited[area][next]=true;
						edgeVisited[next][area]=true;
					}
					initialAreas.clear();
					
					for (int next : allAreas) if (awakeNeighboursCount[next]>=3) initialAreas.add(next);
					allAreas.removeAll(initialAreas);
					
					if (initialAreas.size()>0) year++;
					else break;
				}
				
				if (allAreas.size() == 0) System.out.println("WAKE UP IN, "+year+", YEARS");
				else System.out.println("THIS BRAIN NEVER WAKES UP");
			}
		}
		
	}

}