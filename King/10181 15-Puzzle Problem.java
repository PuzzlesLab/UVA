import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static final int [][] Deltas={{0,1},{0,-1},{1,0},{-1,0}};
	private static final char [] Moves={'R','L','D','U'};
	private static final int BLANK=15;
	private static final String UNSOLVABLE = "This puzzle is not solvable.";

	private static HashSet<Long> Visited=null;
	private static String Sol=null;

	private static int minStep(int [] puzzle) {
		int count=0;
		for (int i=0;i<4;i++) for (int i2=0;i2<4;i2++) {
			int v=puzzle[(i<<2)+i2];
			if (v==BLANK) continue;

			int eX=v>>2;
			int eY=v%4;
			count+=Math.abs(eX-i)+Math.abs(eY-i2);
		};
		return count;
	}

	private static long hash(int [] puzzle) {
		long state=0;
		for (int i=0;i<4;i++) for (int i2=0;i2<4;i2++) state=(state<<4)|puzzle[(i<<2)+i2];
		return state;
	}

	private static boolean isFinal(int [] puzzle) {
		for (int i=0;i<puzzle.length;i++) if (puzzle[i]!=i) return false;
		return true;
	}
	
	private static void swap(int [] puzzle, int x1, int y1, int x2, int y2) {
		int idx1=(x1<<2)+y1;
		int idx2=(x2<<2)+y2;
		int temp=puzzle[idx1];
		puzzle[idx1]=puzzle[idx2];
		puzzle[idx2]=temp;
	}

	private static void search(int [] curr, int empty, int step, char [] moves) {
		if (Sol!=null) return;
		if (isFinal(curr)) {
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<step;i++) sb.append(moves[i]);
			Sol=sb.toString();
			return;
		}
		if (step==moves.length) return;
		int ex=empty>>2;
		int ey=empty%4;

		for (int i=0;i<Deltas.length;i++) {
			int nx=ex+Deltas[i][0];
			int ny=ey+Deltas[i][1];
			if (nx>=0 && nx<4 && ny>=0 && ny<4) {
				swap(curr,ex,ey,nx,ny);
				long state=hash(curr);
				if (Visited.contains(state)) {
					swap(curr,ex,ey,nx,ny);
					continue;
				} else {
					int estNext=step+1+minStep(curr);
					if (estNext>=moves.length) {
						swap(curr,ex,ey,nx,ny);
						continue;
					}
					Visited.add(state);
					moves[step]=Moves[i];
					search(curr,(nx<<2)+ny,step+1,moves);
					swap(curr,ex,ey,nx,ny);
					moves[step]='Z';
					Visited.remove(state);
				}
			}
		}
	}
	
	private static boolean solvable(int [] puzzle, int empty) {
		// https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/
		// Half of the combinations are unsolvable, so we prune them first!
		int rowInvert=4-(empty>>2);
		int inversions=0;
		for (int i=0;i<puzzle.length;i++) {
			for (int i2=i+1;i2<puzzle.length;i2++) {
				if (puzzle[i]==BLANK || puzzle[i2]==BLANK) continue;
				if (puzzle[i]>puzzle[i2]) inversions++;
			}
		}

		boolean rowEven=(rowInvert&1)==0; // second last, forth last.
		boolean invertEven=(inversions&1)==0;
		return rowEven ^ invertEven;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			int [] puzzle=new int [16];
			int empty=-1;
			for (int i=0;i<4;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int i2=0;i2<4;i2++) {
					int idx=(i<<2)+i2;
					puzzle[idx]=Integer.parseInt(st.nextToken());
					if (puzzle[idx]==0) puzzle[idx]=BLANK;
					else puzzle[idx]--;
					if (empty==-1 && puzzle[idx]==BLANK) empty=idx;
				}
			}

			if (!solvable(puzzle,empty)) {
				System.out.println(UNSOLVABLE);
				continue;
			}

			Visited=new HashSet<>();
			int minStep=minStep(puzzle);
			Sol=null;
			for (int i=minStep;i<=50;i++) {
				search(puzzle,empty,0,new char [i+1]);
				if (Sol!=null) break;
			}
			if (Sol==null) Sol=UNSOLVABLE;
			System.out.println(Sol);
		}
	}

}
