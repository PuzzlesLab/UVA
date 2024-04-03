import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [][] Deltas={{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	private static char [][] Grid;
	private static String Word;
	
	private static boolean find(int x, int y, int w, int d) {
		if (w==Word.length()) return true;
		if (x<0 || x>=Grid.length) return false;
		if (y<0 || y>=Grid[0].length) return false;
		if (Grid[x][y]!=Word.charAt(w)) return false;
		return find(x+Deltas[d][0],y+Deltas[d][1],w+1,d);
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine();
			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());

			Grid=new char [M][];
			for (int m=0;m<M;m++) Grid[m]=br.readLine().toUpperCase().toCharArray();

			if (tc>0) System.out.println();
			int K=Integer.parseInt(br.readLine());
			for (int k=0;k<K;k++) {
				Word=br.readLine().toUpperCase();
				boolean flag=false;
				int ax=-1;
				int ay=-1;
				for (int x=0;x<M && !flag;x++) for (int y=0;y<N && !flag;y++) for (int d=0;d<Deltas.length && !flag;d++) {
					flag|=find(x,y,0,d);
					if (flag) {
						ax=x+1;
						ay=y+1;
					}
				}
				System.out.printf("%d %d\n",ax,ay);
			}
		}
	}
}