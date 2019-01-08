import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

class Main {

	public static ArrayList<Edge> allEdges;
	public static ArrayList<Edge> [] edgeList;
	public static boolean [] travelledNodes;
	public static HashSet<String> edgeSet;
	
	public static class Edge {
		int src;
		int dest;
		boolean travelled;
	}
	
	public static void floodfill (Edge e, int start) {
		if (!e.travelled) {
			travelledNodes[e.src]=true;
			travelledNodes[e.dest]=true;
			e.travelled=true;
			for (Edge ex : edgeList[e.src]) {
				floodfill(ex,e.src);
			}
			for (Edge ex : edgeList[e.dest]) {
				floodfill(ex,ex.dest);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		if (testCaseCount==0) {
			return;
		}
		br.readLine(); //blank line.
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int n=br.readLine().charAt(0)-'A'+1;
			
			travelledNodes=new boolean [n];
			allEdges=new ArrayList<>();
			edgeList=new ArrayList [n];
			edgeSet=new HashSet<>();
			for (int i=0;i<n;i++) {
				edgeList[i]=new ArrayList<>();
			}
			
			while (true) {
				String s=br.readLine();
				if (s==null || s.isEmpty()) {
					break;
				}
				Edge e=new Edge();
				e.src=s.charAt(0)-'A';
				e.dest=s.charAt(1)-'A';
				String key=e.src+"_"+e.dest;
				if (!edgeSet.contains(key)) { //bidirectional edge.
					allEdges.add(e);
					edgeList[e.src].add(e);
					edgeList[e.dest].add(e);
					
					edgeSet.add(key);
					edgeSet.add(e.dest+"_"+e.src);
				}
			}
			
			int count=0;
			for (int i=0;i<allEdges.size();i++) {
				Edge e=allEdges.get(i);
				if (!e.travelled) {
					floodfill(e,-1);
					count++;
				}
			}
			for (int i=0;i<n;i++) {
				if (!travelledNodes[i]) {
					count++;
				}
			}
			
			if (testCase>0) {
				System.out.println();
			}
			System.out.println(count);
		}
	}
	
}