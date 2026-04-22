import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class State {
		int game;
		char moveC;
		int moveI;
		State parent;
		
		public State(int game, char sDif, int sIdx, State p) {
			this.game=game;
			this.parent=p;
			this.moveC=sDif;
			this.moveI=sIdx;
		}
	}

	private static void shift(int [] ary, int i, int i2, int i3) {
		int temp=ary[i3];
		ary[i3]=ary[i2];
		ary[i2]=ary[i];
		ary[i]=temp;
	}
	
	private static int toInt(int [] ary) {
		int r=0;
		for (int i=0;i<ary.length;i++) r=r*10+ary[i];
		return r;
	}

	private static HashMap<Integer,State> initialize() {
		// Shift opposite as we starts from 123456789.
		final int [][] hShifts={{2,1,0},{5,4,3},{8,7,6}};
		final int [][] vShifts={{0,3,6},{1,4,7},{2,5,8}};

		LinkedList<State> q=new LinkedList<>();
		q.addLast(new State(123456789,'Z',0,null));
		HashMap<Integer,State> visited=new HashMap<>();
		visited.put(123456789,q.getFirst());

		while (!q.isEmpty()) {
			State curr=q.removeFirst();
			
			int temp=curr.game;
			int [] ary=new int [9];
			for (int i=8;i>=0;i--) {
				ary[i]=temp%10;
				temp/=10;
			}
			
			// H shifts
			for (int i=0;i<hShifts.length;i++) {
				shift(ary,hShifts[i][0],hShifts[i][1],hShifts[i][2]);
				int n=toInt(ary);
				if (!visited.containsKey(n)) {
					State nS=new State(n,'H',i+1,curr);
					visited.put(n,nS);
					q.addLast(nS);
				}
				shift(ary,hShifts[i][0],hShifts[i][1],hShifts[i][2]); // Shift back
				shift(ary,hShifts[i][0],hShifts[i][1],hShifts[i][2]); // Shift back
			}

			// V shifts
			for (int i=0;i<vShifts.length;i++) {
				shift(ary,vShifts[i][0],vShifts[i][1],vShifts[i][2]);
				int n=toInt(ary);
				if (!visited.containsKey(n)) {
					State nS=new State(n,'V',i+1,curr);
					visited.put(n,nS);
					q.addLast(nS);
				}
				shift(ary,vShifts[i][0],vShifts[i][1],vShifts[i][2]); // Shift back
				shift(ary,vShifts[i][0],vShifts[i][1],vShifts[i][2]); // Shift back
			}
		}
		
		return visited;
	}

	public static void main(String[] args) throws Exception {
		HashMap<Integer,State> dp=initialize();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int init=0;
			
			for (int i=0;i<3;i++) {
				StringTokenizer st=new StringTokenizer(s);
				for (int i2=0;i2<3;i2++) init=init*10+Integer.parseInt(st.nextToken());
				if (i<2) s=br.readLine();
			}

			State sol=dp.getOrDefault(init, null);
			if (sol==null) System.out.println("Not solvable");
			else {
				int steps=0;
				StringBuilder sb=new StringBuilder();
				while (sol!=null) {
					if (sol.moveC=='Z') break;
					sb.append(sol.moveC);
					sb.append(sol.moveI);
					steps++;
					sol=sol.parent;
				}
				System.out.printf("%d %s\n",steps,sb);
			}
		}
	}

}