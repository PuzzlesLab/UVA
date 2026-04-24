import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static final int [][] Deltas={{-1,0},{0,-1},{1,0},{0,1}};
	private static final char [] Directions={'U','L','D','R'};

	private static class State {
		int [][] puzzle;
		char move;
		State parent;
		int zx,zy;
		
		public State(int [][] puzzle, char m, State p, int zx, int zy) {
			this.puzzle=puzzle;
			this.move=m;
			this.parent=p;
			this.zx=zx;
			this.zy=zy;
		}
	}

	private static int[][] copyAry(int [][] puzzle) {
		 int [][] copy=new int [3][3];
		 for (int i=0;i<3;i++) for (int i2=0;i2<3;i2++) copy[i][i2]=puzzle[i][i2];
		 return copy;
	}
	
	private static void swap(int [][] puzzle, int x1, int y1, int x2, int y2) {
		int temp=puzzle[x1][y1];
		puzzle[x1][y1]=puzzle[x2][y2];
		puzzle[x2][y2]=temp;
	}

	private static int toKey(int [][] puzzle) {
		int r=0;
		for (int i=0;i<3;i++) for (int i2=0;i2<3;i2++) r=r*10+puzzle[i][i2];
		return r;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			br.readLine(); // empty.
			
			State init=new State(new int [3][3],'Z',null,-1,-1);
			for (int i=0;i<3;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int i2=0;i2<3;i2++) {
					init.puzzle[i][i2]=Integer.parseInt(st.nextToken());
					if (init.puzzle[i][i2]==0) {
						init.zx=i;
						init.zy=i2;
					}
				}
			}
			
			State last=init;
			HashSet<Integer> visited=new HashSet<>();
			LinkedList<State> q=new LinkedList<>();
			q.add(init);
			visited.add(toKey(init.puzzle));
			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				last=curr;
				
				for (int i=0;i<Deltas.length;i++) {
					int [] d=Deltas[i];
					int nzx=curr.zx+d[0];
					int nzy=curr.zy+d[1];
					if (nzx>=0 && nzx<3 && nzy>=0 && nzy<3) {
						swap(curr.puzzle,curr.zx,curr.zy,nzx,nzy);
						int key=toKey(curr.puzzle);
						if (!visited.contains(key)) {
							visited.add(key);
							State next=new State(copyAry(curr.puzzle),Directions[i],curr,nzx,nzy);
							q.addLast(next);
						}
						swap(curr.puzzle,curr.zx,curr.zy,nzx,nzy);
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Puzzle #");
			sb.append(tc);
			sb.append('\n');
			for (int i=0;i<3;i++) {
				for (int i2=0;i2<3;i2++) {
					sb.append(last.puzzle[i][i2]);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				sb.append('\n');
			}
			Stack<State> stk=new Stack<>();
			while (last.move!='Z') {
				stk.push(last);
				last=last.parent;
			}
			while (!stk.isEmpty()) sb.append(stk.pop().move);
			sb.append('\n');
			System.out.println(sb);
		}
	}

}