import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static final int [] POS_D_NORTH={-1,0};
	private static final int [] POS_D_SOUTH={1,0};
	private static final int [] POS_D_WEST={0,-1};
	private static final int [] POS_D_EAST={0,1};
	private static final int [][] POS_D={POS_D_NORTH, POS_D_SOUTH, POS_D_WEST, POS_D_EAST};

	// Dice face: top=0, back=1, btm=2, front=3, left=4, right=5.
	private static final int BTM=2;

	// Rotation - New Face-Old Face map
	private static final int [][] ROT_NORTH={{0,3},{1,0},{2,1},{3,2},{4,4},{5,5}};
	private static final int [][] ROT_SOUTH={{0,1},{1,2},{2,3},{3,0},{4,4},{5,5}};
	private static final int [][] ROT_WEST={{0,5},{1,1},{2,4},{3,3},{4,0},{5,2}};
	private static final int [][] ROT_EAST={{0,4},{1,1},{2,5},{3,3},{4,2},{5,0}};
	private static final int [][][] ROT={ROT_NORTH, ROT_SOUTH, ROT_WEST, ROT_EAST};

	private static class State {
		int x,y,cube,step;
		int [] cubeAry;
		
		public State(int x, int y, int c, int s) {
			this.x=x;
			this.y=y;
			this.cube=c;
			this.step=s;
		}
		
		public long getKey(int Y) {
			return (this.x*Y+this.y)*1000000L+this.cube;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		br.readLine(); // empty.
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int X=Integer.parseInt(st.nextToken());
			int Y=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int sx=Integer.parseInt(st.nextToken())-1;
			int sy=Integer.parseInt(st.nextToken())-1;
			st=new StringTokenizer(br.readLine());
			int ex=Integer.parseInt(st.nextToken())-1;
			int ey=Integer.parseInt(st.nextToken())-1;

			boolean [][][][] wall=new boolean [X][Y][X][Y];
			char c='Z';
			while (true) {
				String s=br.readLine();
				if (s==null || s.isEmpty()) break;
				if (s.charAt(0)=='v') c='v';
				else if (s.charAt(0)=='h') c='h';
				else {
					st=new StringTokenizer(s);
					int n=Integer.parseInt(st.nextToken())-1;
					int m=Integer.parseInt(st.nextToken())-1;
					if (c=='v') {
						wall[n][m][n+1][m]=true;
						wall[n+1][m][n][m]=true;
					} else if (c=='h') {
						wall[n][m][n][m+1]=true;
						wall[n][m+1][n][m]=true;
					}
				}
			}
			
			int ans=-1;
			State init=new State(sx,sy,12345,0);
			init.cubeAry=new int [] {0,1,2,3,4,5};

			HashSet<Long> visited=new HashSet<>();
			LinkedList<State> q=new LinkedList<>();
			q.add(init);
			visited.add(init.getKey(Y));
			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.x==ex && curr.y==ey && curr.cubeAry[BTM]==BTM) {
					ans=curr.step;
					break;
				}

				for (int i=0;i<POS_D.length;i++) {
					int nx=curr.x+POS_D[i][0];
					int ny=curr.y+POS_D[i][1];
					if (nx<0||nx>=X||ny<0||ny>=Y) continue;
					if (wall[curr.x][curr.y][nx][ny]) continue;
					
					int nCube=0;
					int [] nCubeAry=new int [6];
					for (int side=0;side<ROT[i].length;side++) {
						nCubeAry[ROT[i][side][0]]=curr.cubeAry[ROT[i][side][1]];
						nCube=nCube*10+nCubeAry[ROT[i][side][0]];
					}

					State nS=new State(nx,ny,nCube,curr.step+1);
					nS.cubeAry=nCubeAry;
					if (!visited.contains(nS.getKey(Y))) {
						visited.add(nS.getKey(Y));
						q.addLast(nS);
					}
				}
			}

			if (tc>0) System.out.println();
			System.out.println(ans==-1?"No solution":ans);
		}
	}

}