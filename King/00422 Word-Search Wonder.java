import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static class Tuple {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	private static int [][] Deltas={{-1,-1},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	private static char [][] Grid;
	private static String Word;
	private static Tuple Start;
	private static Tuple End;

	private static void find(int x, int y, int w, int d) {
		if (End!=null) return;
		if (w==Word.length()) {
			End=new Tuple(x-Deltas[d][0],y-Deltas[d][1]);
			return;
		}
		if (x<0 || x>=Grid.length || y<0 || y>=Grid.length) return;
		if (Grid[x][y]!=Word.charAt(w)) return;
		find(x+Deltas[d][0],y+Deltas[d][1],w+1,d);
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		while (N!=0) {
			Grid=new char [N][];
			for (int n=0;n<N;n++) Grid[n]=br.readLine().toCharArray();
			
			StringBuilder sb=new StringBuilder();
			while (true) {
				String s=br.readLine();
				try {
					N=Integer.parseInt(s);
					break;
				} catch (Exception e) {}
				
				if (s.length()>N) {
					sb.append("Not found\n");
					continue;
				}
				
				Word=s;
				End=null;
				Start=null;
				for (int x=0;x<N && End==null;x++) for (int y=0;y<N && End==null;y++) for (int d=0;d<Deltas.length;d++) {
					find(x,y,0,d);
					if (End!=null) Start=new Tuple(x,y);
				}
				
				if (End!=null) {
					sb.append(Start.x+1);
					sb.append(',');
					sb.append(Start.y+1);
					sb.append(' ');
					sb.append(End.x+1);
					sb.append(',');
					sb.append(End.y+1);
				} else sb.append("Not found");
				sb.append('\n');
			}
			System.out.print(sb);
		}
	}
}