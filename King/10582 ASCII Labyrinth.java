import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int [][] Deltas={{0,1},{1,0},{0,-1},{-1,0}};

	public static class Block {
		boolean up, down, left, right;
		boolean visited;
		private char mode;
		int x, y;
		
		public Block(int x, int y) {
			this.x=x;
			this.y=y;
		}

		public void rotate() {
			if (this.mode=='-') {
				this.up=true;
				this.down=true;
				this.left=false;
				this.right=false;
			} else if (this.mode=='|') {
				this.up=false;
				this.down=false;
				this.left=true;
				this.right=true;
			} else if (this.mode=='N') {
				this.up=true;
				this.down=false;
				this.left=true;
				this.right=false;
			} else if (this.mode=='J') {
				this.up=true;
				this.down=false;
				this.left=false;
				this.right=true;
			} else if (this.mode=='L') {
				this.up=false;
				this.down=true;
				this.left=false;
				this.right=true;
			} else if (this.mode=='P') {
				this.up=false;
				this.down=true;
				this.left=true;
				this.right=false;
			}
			this.calcMode();
		}

		private void calcMode() {
			if (!up && !down && !left && !right) this.mode='X';
			else if (left && right) this.mode='-';
			else if (up && down) this.mode='|';
			else if (down && left) this.mode='N';
			else if (left && up) this.mode='J';
			else if (up && right) this.mode='L';
			else if (right && down) this.mode='P';
		}

		public boolean isLine() {
			return (this.left && this.right) || (this.up && this.down);
		}
		
		public boolean isRightAngled() {
			return (this.left || this.right) && (this.up || this.down);
		}

		public int formsCount() {
			if (this.isLine()) return 2;
			else if (this.isRightAngled()) return 4;
			return 1;
		}

		public String toString() {
			return String.valueOf(this.mode);
		}
	}

	private static boolean isConnected(Block block1, Block block2) {
		if (block1.x==block2.x && block1.y+1==block2.y) return block1.right && block2.left;
		else if (block1.x==block2.x && block1.y==block2.y+1) return block1.left && block2.right;
		else if (block1.y==block2.y && block1.x+1==block2.x) return block1.down && block2.up;
		else if (block1.y==block2.y && block1.x==block2.x+1) return block1.up && block2.down;
		return false;
	}

	private static int find(Block [][] blocks, int cx, int cy) {
		if (cx==blocks.length-1 && cy==blocks[0].length-1) {
			 // End block (N-1,M-1) must be connect to right/down
			return (blocks[cx][cy].down || blocks[cx][cy].right) ? 1 : 0;
		}

		Block currBlock=blocks[cx][cy];
		int sum=0;
		for (int [] delta: Deltas) {
			int nx=cx+delta[0];
			int ny=cy+delta[1];
			if (nx<0 || nx>=blocks.length || ny<0 || ny>=blocks[0].length) continue;

			Block nextBlock=blocks[nx][ny];
			if (nextBlock.mode=='X' || blocks[nx][ny].visited) continue;

			currBlock.visited=true;

			// Do rotations and try continue.
			int rotateCount=nextBlock.formsCount();
			for (int d=0;d<rotateCount;d++) {
				if (isConnected(currBlock, nextBlock)) sum+=find(blocks,nx,ny);
				nextBlock.rotate();
			}

			currBlock.visited=false;
		}
		return sum;
	}
	
	private static int findHelper(Block [][] blocks) {
		int sum=0;
		Block startBlock=blocks[0][0];
		startBlock.visited=true;
		int rotateCount=startBlock.formsCount();
		for (int d=0;d<rotateCount;d++) {
			 // Start block (0,0) must be connect to left/top
			if (startBlock.left || startBlock.up) sum+=find(blocks,0,0);
			startBlock.rotate();
		}
		return sum;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			br.readLine(); // Useless
			
			Block [][] blocks=new Block[N][M];
			for (int n=0;n<N;n++) {
				String [] lines=new String [3];
				for (int i=0;i<lines.length;i++) lines[i]=br.readLine();
				br.readLine(); //Useless
				
				for (int m=0;m<M;m++) {
					int startCol=m*4+1;
					blocks[n][m]=new Block(n,m);
					blocks[n][m].up=lines[0].charAt(startCol+1)=='*';
					blocks[n][m].left=lines[1].charAt(startCol)=='*';
					blocks[n][m].right=lines[1].charAt(startCol+2)=='*';
					blocks[n][m].down=lines[2].charAt(startCol+1)=='*';
					blocks[n][m].calcMode();
				}
			}

			int ans=0;
			if (blocks[0][0].mode=='X' || blocks[N-1][M-1].mode=='X') ans=0;
			else {
				blocks[0][0].visited=true;
				ans=findHelper(blocks);
			}
			System.out.printf("Number of solutions: %d\n", ans);
		}
	}

}
