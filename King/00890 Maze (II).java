import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static int [][] Deltas={{0,-1},{1,0},{0,1},{-1,0}};

	private static class Line {
		char op;
		int n;
		
		public Line(char c) {
			this.op=c;
		}

		public Line(char c, int n) {
			this.op=c;
			this.n=n;
		}
	}

	private static class Tuple {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public String toString() {
			return this.x+","+this.y;
		}
	}

	private static boolean allVisited(int x, int y, boolean [][] visited) {
		for (int i=0;i<Deltas.length;i++) {
			int dx=x+Deltas[i][0];
			int dy=y+Deltas[i][1];
			if (dx<0 || dx>=visited.length || dy<0 || dy>=visited[0].length) continue;
			if (!visited[dx][dy]) return false;
		}
		return true;
	}

	private static void reverse(LinkedList<Tuple> list, int n) {
		LinkedList<Tuple> q=new LinkedList<>();
		while (list.size()>=n) q.addLast(list.removeLast());
		while (!q.isEmpty()) list.addLast(q.removeFirst());
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		String s=br.readLine();
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(s);
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int cx=Integer.parseInt(st.nextToken())-1;
			int cy=Integer.parseInt(st.nextToken())-1;
			
			LinkedList<Line> lines=new LinkedList<>();
			while (true) {
				s=br.readLine();
				if (s==null || s.isEmpty()) break;
				st=new StringTokenizer(s);
				char op=st.nextToken().charAt(0);
				if (!Character.isAlphabetic(op)) break;
				if (op=='F') lines.add(new Line(op,Integer.parseInt(st.nextToken())));
				else lines.add(new Line(op));
			}

			boolean [][] visited=new boolean [M][N];
			boolean [][][] wall=new boolean [M][N][4];
			for (int i=0;i<wall.length;i++) for (int i2=0;i2<wall[i].length;i2++) Arrays.fill(wall[i][i2],true);

			LinkedList<Tuple> list=new LinkedList<>();
			list.add(new Tuple(cx,cy));
			visited[cx][cy]=true;
			while (!list.isEmpty() && !lines.isEmpty()) {
				Tuple t=list.getLast();
				if (allVisited(t.x,t.y,visited)) {
					list.removeLast();
					continue;
				}

				Line l=lines.removeFirst();
				if (l.op=='F') reverse(list,l.n);
				else {
					int dIdx=0;
					if (l.op=='L') dIdx=0;
					else if (l.op=='U') dIdx=1;
					else if (l.op=='R') dIdx=2;
					else if (l.op=='D') dIdx=3;

					int nx=t.x+Deltas[dIdx][0];
					int ny=t.y+Deltas[dIdx][1];

					wall[t.x][t.y][dIdx]=false;
					list.addLast(new Tuple(nx,ny));
					visited[nx][ny]=true;
					wall[nx][ny][(dIdx+2)%Deltas.length]=false;
				}
			}

			char [][] maze=new char [M+1][(N<<1)+1];
			for (int i=0;i<maze.length;i++) for (int i2=0;i2<maze[i].length;i2++) maze[i][i2]=((i2&1)==1)?'_':' ';
			for (int i=1;i<maze.length;i++) for (int i2=0;i2<maze[i].length;i2++) maze[i][i2]=((i2&1)==1)?'_':'|';
			for (int x=0;x<wall.length;x++) for (int y=0;y<wall[x].length;y++) {
				for (int d=0;d<wall[x][y].length;d++) if (!wall[x][y][d]) {
					int nx=wall.length-x;
					int ny=(y<<1)+1;

					if (d==0) maze[nx][ny-1]=' '; // L
					else if (d==1) maze[nx-1][ny]=' '; // U
					else if (d==2) maze[nx][ny+1]=' '; // R
					else if (d==3) maze[nx][ny]=' '; // D
				}
			}
			
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<maze.length;i++) {
				StringBuilder currRow=new StringBuilder();
				for (int i2=0;i2<maze[i].length;i2++) currRow.append(maze[i][i2]);
				while (currRow.length()>0 && currRow.charAt(currRow.length()-1)==' ') currRow.setLength(currRow.length()-1);
				
				sb.append(currRow);
				sb.append('\n');
			}
			System.out.println(sb.toString());
		}
	}
}
