import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class Main {

	private static final int [][] Deltas={{0,-1},{0,1},{-1,0},{1,0}};

	private static class Position {
		int x, y;
		public Position(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	private static class Sector {
		int bGroup, pGroup, fGroup;
	}

	private static void fill(char [][] map, int [][] sectorId, boolean [][] visited, int x, int y) {
		Stack<Position> stk=new Stack<>();
		stk.push(new Position(x,y));
		visited[x][y]=true;
		
		while (!stk.isEmpty()) {
			Position curr=stk.pop();
			for (int [] delta: Deltas) {
				int nx=curr.x+delta[0];
				int ny=curr.y+delta[1];
				if (nx>=0 && nx<map.length && ny>=0 && ny<map.length && !visited[nx][ny] && sectorId[nx][ny]==sectorId[x][y] && map[nx][ny]==map[x][y]) {
					stk.push(new Position(nx,ny));
					visited[nx][ny]=true;
				}
			}
		}
	}

	private static boolean isFighting(char [][] map, int [][] sectorId, int x, int y) {
		boolean [][] visited=new boolean [map.length][map.length];
		boolean existB=false;
		boolean existP=false;

		Stack<Position> stk=new Stack<>();
		stk.push(new Position(x,y));
		visited[x][y]=true;
		while (!stk.isEmpty()) {
			Position curr=stk.pop();
			existB|=map[curr.x][curr.y]=='B';
			existP|=map[curr.x][curr.y]=='P';
			
			if (existB && existP) return true;

			for (int [] delta: Deltas) {
				int nx=curr.x+delta[0];
				int ny=curr.y+delta[1];
				if (nx>=0 && nx<map.length && ny>=0 && ny<map.length && !visited[nx][ny] && sectorId[nx][ny]==sectorId[x][y] && (map[nx][ny]=='B' || map[nx][ny]=='P')) {
					stk.push(new Position(nx,ny));
					visited[nx][ny]=true;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;

			char [][] map=new char [N][];
			for (int n=0;n<N;n++) map[n]=br.readLine().toCharArray();

			int [][] sectorId=new int [N][N];

			int sectorIdMax=1;
			for (int x=0;x<N;x++) for (int y=0;y<N;y++) if (map[x][y]!='.' && sectorId[x][y]==0) {
				sectorId[x][y]=sectorIdMax++;

				Stack<Position> stk=new Stack<>();
				stk.push(new Position(x,y));
				while (!stk.isEmpty()) {
					Position curr=stk.pop();
					for (int [] delta: Deltas) {
						int nx=curr.x+delta[0];
						int ny=curr.y+delta[1];
						if (nx>=0 && nx<N && ny>=0 && ny<N && map[nx][ny]!='.' && sectorId[nx][ny]==0) {
							stk.push(new Position(nx,ny));
							sectorId[nx][ny]=sectorId[curr.x][curr.y];
						}
					}
				}
			}

			boolean [][] visited=new boolean [N][N];
			ArrayList<Sector> sectors=new ArrayList<>();
			for (int currSectorId=1;currSectorId<sectorIdMax;currSectorId++) {
				Sector sector=new Sector();
				sectors.add(sector);
				for (int x=0;x<N;x++) for (int y=0;y<N;y++) if (sectorId[x][y]==currSectorId && !visited[x][y]) {
					Stack<Position> stk=new Stack<>();
					stk.push(new Position(x,y));
	
					while (!stk.isEmpty()) {
						Position curr=stk.pop();
						if (visited[curr.x][curr.y]) continue;
	
						visited[curr.x][curr.y]=true;
						char ch=map[curr.x][curr.y];

						if (ch=='P' || ch=='B') {
							fill(map,sectorId,visited,curr.x,curr.y);
							
							if (ch=='P') sector.pGroup++;
							if (ch=='B') sector.bGroup++;
							if (isFighting(map,sectorId,curr.x,curr.y)) sector.fGroup++;
						}
	
						for (int [] delta: Deltas) {
							int nx=curr.x+delta[0];
							int ny=curr.y+delta[1];
							if (nx>=0 && nx<N && ny>=0 && ny<N && sectorId[nx][ny]==currSectorId) {
								stk.push(new Position(nx,ny));
							}
						}
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			int fGroupSum=0;
			for (int i=0;i<sectors.size();i++) {
				Sector sector=sectors.get(i);
				sb.append("Sector #");
				sb.append(i+1);
				sb.append(": contain ");
				sb.append(sector.bGroup);
				sb.append(" freedom fighter group(s) & ");
				sb.append(sector.pGroup);
				sb.append(" enemy group(s)\n");
				fGroupSum+=sector.fGroup;
			}
			sb.append("Total ");
			sb.append(fGroupSum);
			sb.append(" group(s) are in fighting position.\n");
			System.out.println(sb.toString());
		}
	}

}
