import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Data {
		int x, y, direction, move;
		public Data (int x, int y, int direction, int move) { this.x=x; this.y=y; this.direction=direction; this.move=move;}
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int [] directionDeltas = {-1,1};
		int [][] moveDeltas = {{-1,0},{0,-1},{1,0},{0,1}};
		int [][] checks = {{0,0},{-1,0},{0,-1},{-1,-1}};
		//0-north, 1=south, 2=west, 3=east
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int X=Integer.parseInt(st.nextToken());
			int Y=Integer.parseInt(st.nextToken());
			
			boolean [][] map=new boolean [X][Y];
			for (int x=0;x<X;x++) {
				st=new StringTokenizer(br.readLine());
				for (int y=0;y<Y;y++) map[x][y]=st.nextToken().equals("0");
			}
			
			st=new StringTokenizer(br.readLine());
			int sx=Integer.parseInt(st.nextToken()), sy=Integer.parseInt(st.nextToken());
			int ex=Integer.parseInt(st.nextToken()), ey=Integer.parseInt(st.nextToken());
			int direction=0;
			switch (st.nextToken()) {
				case "north": 	direction=0;
								break;
				case "west": 	direction=1;
								break;
				case "south": 	direction=2;
								break;
				case "east": 	direction=3;
								break;
			}
			
			int ans=-1;
			boolean [][][] visited=new boolean [X+1][Y+1][4];
			LinkedList<Data> queue=new LinkedList<>();
			queue.add(new Data(sx,sy,direction,0));
			visited[sx][sy][direction]=true;
			while (!queue.isEmpty()) {
				Data curr=queue.removeFirst();
				
				if (curr.x==ex && curr.y==ey) {
					ans=curr.move;
					break;
				}
				for (int directionDelta : directionDeltas) {
					int newDirection=Math.floorMod(curr.direction+directionDelta,4);
					if (!visited[curr.x][curr.y][newDirection]) {
						visited[curr.x][curr.y][newDirection]=true;
						queue.add(new Data(curr.x,curr.y,newDirection,curr.move+1));
					}
				}
				for (int i=1;i<=3;i++) {
					int nextX=curr.x+moveDeltas[curr.direction][0]*i;
					int nextY=curr.y+moveDeltas[curr.direction][1]*i;
					boolean flag=nextX>0 && nextX<X && nextY>0 && nextY<Y;
					if (flag && !visited[nextX][nextY][curr.direction]) {
						for (int [] check : checks) flag &= map[nextX+check[0]][nextY+check[1]];
						if (flag) {
							visited[nextX][nextY][curr.direction]=true;
							queue.addLast(new Data(nextX,nextY,curr.direction,curr.move+1));
						};
					}
					if (!flag) break;
				}
			}
			
			System.out.println(ans);
		}
	}

}