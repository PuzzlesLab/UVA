import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	private static class Edge {
		char src, dest, age;
		int weight;
		public Edge(char src, char dest, char age, int w) {this.src=src; this.dest=dest; this.age=age; this.weight=w;}
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int [] map=new int [128];
			Arrays.fill(map, Integer.MAX_VALUE);
			
			char [] mapIntToChar=new char [128];
			int nodeCount=0;
			
			ArrayList<Edge> tempEdges=new ArrayList<>();
			int N=Integer.parseInt(s);
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				char age=st.nextToken().charAt(0);
				boolean bidirectional=st.nextToken().charAt(0)=='B';
				char src=st.nextToken().charAt(0);
				char dest=st.nextToken().charAt(0);
				int weight=Integer.parseInt(st.nextToken());
				
				tempEdges.add(new Edge(src,dest,age,weight));
				if (bidirectional) tempEdges.add(new Edge(dest,src,age,weight));
				
				if (map[src]==Integer.MAX_VALUE) {
					mapIntToChar[nodeCount]=src;
					map[src]=nodeCount++;
				}
				if (map[dest]==Integer.MAX_VALUE) {
					mapIntToChar[nodeCount]=dest;
					map[dest]=nodeCount++;
				}
			}
			
			ArrayList<Edge> [] edgesOnNode=new ArrayList [nodeCount];
			for (int n=0;n<nodeCount;n++) edgesOnNode[n]=new ArrayList<>();
			for (Edge e : tempEdges) edgesOnNode[map[e.src]].add(e);
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			int src=st.nextToken().charAt(0), srcInt=map[src];
			int dest=st.nextToken().charAt(0), destInt=map[dest];
			
			String ans="You will never meet.";
			if (srcInt!=Integer.MAX_VALUE && destInt!=Integer.MAX_VALUE) {
				int [] distFromSrc=new int [nodeCount];
				Arrays.fill(distFromSrc, Integer.MAX_VALUE);
				distFromSrc[srcInt]=0;
				for (int n=0;n<nodeCount-1;n++) for (int i=0;i<nodeCount;i++) if (distFromSrc[i]!=Integer.MAX_VALUE)
					for (Edge e : edgesOnNode[i]) if (e.age=='Y') distFromSrc[map[e.dest]]=Math.min(distFromSrc[map[e.dest]], e.weight+distFromSrc[i]);
				
				int [] distFromDest=new int [nodeCount];
				Arrays.fill(distFromDest, Integer.MAX_VALUE);
				distFromDest[destInt]=0;
				for (int n=0;n<nodeCount-1;n++) for (int i=0;i<nodeCount;i++) if (distFromDest[i]!=Integer.MAX_VALUE)
					for (Edge e : edgesOnNode[i]) if (e.age=='M') distFromDest[map[e.dest]]=Math.min(distFromDest[map[e.dest]], e.weight+distFromDest[i]);
	
				int lowestDist=Integer.MAX_VALUE;
				char lowestChar=0;
				ArrayList<Character> lowestChars=new ArrayList<>();
				for (int n=0;n<nodeCount;n++) if (distFromSrc[n]!=Integer.MAX_VALUE && distFromDest[n]!=Integer.MAX_VALUE) {
					int dist=distFromSrc[n]+distFromDest[n];
					if (dist<lowestDist) {
						lowestDist=dist;
						lowestChar=mapIntToChar[n];
						lowestChars.clear();
						lowestChars.add(lowestChar);
					} else if (dist==lowestDist) lowestChars.add(mapIntToChar[n]);
				}
				
				if (lowestChar!=0) {
					StringBuilder sb=new StringBuilder();
					sb.append(lowestDist);
					Collections.sort(lowestChars);
					for (char c : lowestChars) {
						sb.append(' ');
						sb.append(c);
					}
					ans=sb.toString();
				}
			}
			System.out.println(ans);
		}
	}

}