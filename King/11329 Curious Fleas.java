import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

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
		int diceX,diceY,moves;
		int faceFleas; // Dice face has flea flag
		int map; // XY, 1 = with flea, 0 = without.

		private static int flatten(int x, int y) {
			return (x<<2)+y;
		}

		public State(int diceX, int diceY, int map, int faceFleas, int moves) {
			this.diceX=diceX;
			this.diceY=diceY;
			this.map=map;
			this.faceFleas=faceFleas;
			this.moves=moves;
		}

		public int diceKey() {
			return State.flatten(this.diceX,diceY);
		}

		public State move(int dIdx) {
			int nx=this.diceX+POS_D[dIdx][0];
			int ny=this.diceY+POS_D[dIdx][1];

			if (nx<0 || nx>=4 || ny<0 || ny>=4) return null;
			
			// Build new face flea after rotation.
			int nextFF=0;
			for (int i=0;i<ROT[dIdx].length;i++) if ((this.faceFleas&(1<<ROT[dIdx][i][1]))!=0) nextFF|=1<<ROT[dIdx][i][0];

			int posBit=1<<State.flatten(nx,ny);
			State next=new State(nx,ny,this.map,nextFF,this.moves+1);
			if ((next.map&posBit)!=0 ^(next.faceFleas&(1<<BTM))!=0) {
				if ((next.map&posBit)!=0) {
					next.map&=~posBit;
					next.faceFleas|=(1<<BTM);
				} else if ((next.faceFleas&(1<<BTM))!=0) {
					next.map|=posBit;
					next.faceFleas&=~(1<<BTM);
				}
			}

			return next;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine();
			
			int map=0;
			boolean [][] hasFlea=new boolean [4][4];
			int [] dice=null;
			for (int i=0;i<hasFlea.length;i++) {
				char [] ch=br.readLine().toCharArray();
				for (int i2=0;i2<hasFlea[i].length;i2++) {
					if (ch[i2]=='D') dice=new int [] {i,i2};
					else if (ch[i2]=='X') map|=1<<State.flatten(i,i2);
				}
			}

			State init=new State(dice[0],dice[1],map,0,0);
			boolean [][][] visited=new boolean [16][1<<16][1<<6];
			LinkedList<State> q=new LinkedList<>();
			q.add(init);
			visited[init.diceKey()][init.map][init.faceFleas]=true;

			int ans=-1;
			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.map==0) {
					ans=curr.moves;
					break;
				}
				for (int i=0;i<4;i++) {
					State nS=curr.move(i);
					if (nS==null) continue;
					if (!visited[nS.diceKey()][nS.map][nS.faceFleas]) {
						visited[nS.diceKey()][nS.map][nS.faceFleas]=true;
						q.add(nS);
					}
				}
			}
			
			System.out.println(ans==-1?"impossible":ans);
		}
	}

}
