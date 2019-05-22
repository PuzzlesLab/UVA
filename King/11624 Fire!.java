import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class uva {
	
	private static class Data {
		int x, y;

		public Data(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		int [][] moves= {{-1,0},{1,0},{0,-1},{0,1}};
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int X=Integer.parseInt(st.nextToken()), Y=Integer.parseInt(st.nextToken());
			

			LinkedList<Data> fireQueue=new LinkedList<>();
			int [][] fireTime=new int [X][Y];
			
			LinkedList<Data> joeQueue=new LinkedList<>();
			int [][] joeTime=new int [X][Y];
			
			char [][] map=new char [X][Y];
			
			for (int x=0;x<X;x++) { 
				map[x]=br.readLine().toCharArray();
				for (int y=0;y<Y;y++) {
					//Separating these = TLE!!!
					if (map[x][y]=='J') {
						joeQueue.add(new Data(x,y));
						joeTime[x][y]=1;
					} else if (map[x][y]=='F') {
						fireQueue.add(new Data(x,y));
						fireTime[x][y]=1;
					}
				}
			}

			while (!fireQueue.isEmpty()) {
				Data d=fireQueue.removeFirst();
				int mov=fireTime[d.x][d.y]+1;
				for (int [] move : moves) {
					int nx=d.x+move[0], ny=d.y+move[1];
					if (nx>=0 && nx<X && ny>=0 && ny<Y && map[nx][ny]!='#' && fireTime[nx][ny]==0) {
						fireTime[nx][ny]=mov;
						fireQueue.add(new Data(nx,ny));
					}
				}
			}

			int ans=-1;
			
			while (!joeQueue.isEmpty() && ans==-1) {
				Data d=joeQueue.removeFirst();
				int mov=joeTime[d.x][d.y]+1;
				for (int [] move : moves) {
					int nx=d.x+move[0], ny=d.y+move[1];
					if (nx<0 || nx>=X || ny<0 || ny>=Y) {
						ans=joeTime[d.x][d.y];
						break;
					} else if (nx>=0 && nx<X && ny>=0 && ny<Y && map[nx][ny]=='.' && joeTime[nx][ny]==0 && (fireTime[nx][ny]==0 || mov<fireTime[nx][ny])) {
						joeTime[nx][ny]=mov;
						map[nx][ny]='J';
						joeQueue.addLast(new Data(nx,ny));
					}
				}
			}
			
			if (ans!=-1) System.out.println(ans);
			else System.out.println("IMPOSSIBLE");
		}
	}

}
