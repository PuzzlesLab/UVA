import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<String> Cities;
	private static int [][] ShortestDist;
	private static int [][] Parent;
	private static String [][] IntercityRoute;

	private static String fillSpace(String s, int expectedLength) {
		StringBuilder sb=new StringBuilder(s);
		while (sb.length()<expectedLength) sb.append(' ');
		return sb.toString();
	}

	private static void printPath(StringBuilder sb, int i, int j) {
		if (i!=j) {
			printPath(sb,i,Parent[i][j]);
			sb.append(fillSpace(Cities.get(Parent[i][j]),20));
			sb.append(' ');
			sb.append(fillSpace(Cities.get(j),20));
			sb.append(' ');
			sb.append(fillSpace(IntercityRoute[Parent[i][j]][j],10));
			sb.append(' ');
			sb.append(String.format("%5d",ShortestDist[Parent[i][j]][j]));
			sb.append('\n');
		}

	}

	public static void main(String[] args) throws Exception {
		final int MAX=1000000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		ArrayList<String> edges=new ArrayList<>();
		while (true) {
			s=br.readLine();
			if (s.isEmpty()) break;
			edges.add(s);
		}

		Cities=new ArrayList<>();
		HashMap<String,Integer> cityToIdx=new HashMap<>();
		for (int i=0;i<edges.size();i++) {
			StringTokenizer st=new StringTokenizer(edges.get(i),",");
			String from=st.nextToken();
			if (!cityToIdx.containsKey(from)) {
				cityToIdx.put(from,Cities.size());
				Cities.add(from);
			}
			String to=st.nextToken();
			if (!cityToIdx.containsKey(to)) {
				cityToIdx.put(to,Cities.size());
				Cities.add(to);
			}
		}

		int C=Cities.size();
		ShortestDist=new int [C][C];
		for (int i=0;i<ShortestDist.length;i++) Arrays.fill(ShortestDist[i],MAX);
		IntercityRoute=new String [C][C];
		for (int i=0;i<edges.size();i++) {
			StringTokenizer st=new StringTokenizer(edges.get(i),",");
			int from=cityToIdx.get(st.nextToken());
			int to=cityToIdx.get(st.nextToken());
			String route=st.nextToken();
			int dist=Integer.parseInt(st.nextToken());
			if (dist<ShortestDist[from][to]) {
				ShortestDist[from][to]=ShortestDist[to][from]=dist;
				IntercityRoute[from][to]=IntercityRoute[to][from]=route;
			}
		}

		Parent=new int [C][C];
		for (int i=0;i<Parent.length;i++) for (int j=0;j<Parent.length;j++) Parent[i][j]=i;
		for (int k=0;k<C;k++) for (int i=0;i<C;i++) for (int j=0;j<C;j++) {
			int nDist=ShortestDist[i][k]+ShortestDist[k][j];
			if (nDist<ShortestDist[i][j]) {
				ShortestDist[i][j]=nDist;
				Parent[i][j]=Parent[k][j];
			}
		}

		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s,",");
			int from=cityToIdx.get(st.nextToken());
			int to=cityToIdx.get(st.nextToken());
			StringBuilder sb=new StringBuilder();
			sb.append("\n\n");
			sb.append("From                 To                   Route      Miles\n");
			sb.append("-------------------- -------------------- ---------- -----\n");
			printPath(sb,from,to);
			sb.append("                                                     -----\n");
			sb.append("                                          Total      ");
			sb.append(String.format("%5d", ShortestDist[from][to]));
			System.out.println(sb.toString());
		}
	}

}
