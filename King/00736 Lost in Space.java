import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	private static class Direction {
		int dx, dy;
		String name;
		public Direction(int dx, int dy, String n) {
			this.dx=dx;
			this.dy=dy;
			this.name=n;
		}
	}

	private static final Direction [] Deltas= {
		new Direction(-1,0,"N"),
		new Direction(-1,1,"NE"),
		new Direction(0,1,"E"),
		new Direction(1,1,"SE"),
		new Direction(1,0,"S"),
		new Direction(1,-1,"SW"),
		new Direction(0,-1,"W"),
		new Direction(-1,-1,"NW"),
	};
	private static char [][] Grid;
	private static String Word;

	private static boolean find(int x, int y, int wi, Direction d) {
		if (wi==Word.length()) return true;

		while (true) {
			x+=d.dx;
			y+=d.dy;
			if (x<0 || x>=Grid.length || y<0 || y>=Grid[x].length) return false;
			if (Grid[x][y]!=' ') break;
		}
		if (Grid[x][y]!=Word.charAt(wi)) return false;
		return find(x,y,wi+1,d);
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		br.readLine(); // Empty
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			Grid=new char [N][N];
			for (int n=0;n<N;n++) {
				String s=br.readLine();
				for (int n2=0;n2<N;n2++) Grid[n][n2]=s.charAt(n2);
			}

			ArrayList<String> toFind=new ArrayList<>();
			while (true) {
				String s=br.readLine();
				if (s==null || s.isEmpty()) break;
				toFind.add(s);
			}
			
			StringBuilder sb=new StringBuilder();
			if (tc>0) sb.append('\n');
			for (int i=0;i<toFind.size();i++) {
				Word=toFind.get(i);
				sb.append('\n');
				sb.append(Word);
				sb.append('\n');

				boolean found=false;
				for (int x=0;x<N;x++) for (int y=0;y<N;y++) {
					if (Grid[x][y]!=Word.charAt(0)) continue;
					for (int d=0;d<Deltas.length;d++) {
						if (find(x,y,1,Deltas[d])) {
							sb.append('(');
							sb.append(x+1);
							sb.append(',');
							sb.append(y+1);
							sb.append(") - ");
							sb.append(Deltas[d].name);
							sb.append('\n');
							found=true;
						}
					}
				}

				if (!found) sb.append("not found\n");
			}
			System.out.print(sb.toString());
		}
	}
}