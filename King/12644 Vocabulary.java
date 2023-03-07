import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static boolean [][] HasEdge;
	private static boolean [] Visited;
	private static int [] Pair;

	private static int [] convertMulti(String s) {
		int [] count=new int [128];
		for (int i=0;i<s.length();i++) count[s.charAt(i)]++;
		return count;
	}

	private static boolean canPair(int [] s, int [] w) {
		for (int i=0;i<w.length;i++) if (s[i]<w[i]) return false;
		return true;
	}

	private static int mcbm(int c) {
		if (Visited[c]) return 0;
		
		Visited[c]=true;
		for (int v=0;v<HasEdge[c].length;v++) if (HasEdge[c][v]) {
			if (Pair[v]==-1 || mcbm(Pair[v])==1) {
				Pair[v]=c;
				return 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int V=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			
			int [][] jack=new int [V][];
			for (int v=0;v<V;v++) jack[v]=convertMulti(br.readLine());
			int [][] jill=new int [C][];
			for (int c=0;c<C;c++) jill[c]=convertMulti(br.readLine());
			
			HasEdge=new boolean [C][V];
			for (int c=0;c<C;c++) for (int v=0;v<V;v++) HasEdge[c][v]=canPair(jack[v],jill[c]);

			Pair=new int [V];
			Arrays.fill(Pair,-1);
			int count=0;
			for (int c=0;c<C;c++) {
				Visited=new boolean[C];
				count+=mcbm(c);
			}
			System.out.println(count);
		}
	}

}
