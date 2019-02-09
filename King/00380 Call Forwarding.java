import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	public static class Edge {
		int src, start, end, dest;
		
		public Edge(int src, int start, int end, int dest) {
			this.src=src;
			this.start=start;
			this.end=this.start+end;
			this.dest=dest;
		}
	}
	
	public static int dfs(int time, int dest, ArrayList<Edge> [] edgeList, boolean [] visited) {
		if (!visited[dest]) {
			visited[dest]=true;
			for (Edge e : edgeList[dest]) if (time>=e.start && time<=e.end) return dfs(time, e.dest, edgeList, visited);
			return dest;
		} else return 9999;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		System.out.println("CALL FORWARDING OUTPUT");
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			ArrayList<Edge> [] edgeList=new ArrayList [10000];
			for (int i=0;i<edgeList.length;i++) edgeList[i]=new ArrayList<>();
			
			String s;	
			while (!(s=br.readLine()).equals("0000")) {
				StringTokenizer st=new StringTokenizer(s);
				Edge e=new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				edgeList[e.src].add(e);
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("SYSTEM ");
			sb.append(testCase);
			sb.append('\n');
			
			while (!(s=br.readLine()).equals("9000")) {
				StringTokenizer st=new StringTokenizer(s);
				String timeS=st.nextToken();
				String destS=st.nextToken();
				int time=Integer.parseInt(timeS);
				int dest=Integer.parseInt(destS);
				int ans = dfs(time, dest, edgeList, new boolean[10000]);
				
				String ansS=String.valueOf(ans);
				while (ansS.length()<4) ansS="0"+ansS;
				sb.append(String.format("AT %s CALL TO %s RINGS %s\n", timeS, destS, ansS));
			}
			System.out.print(sb.toString());
		}
		System.out.println("END OF OUTPUT");
	}

}
