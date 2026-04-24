import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static class State {
		public static final int ON_LIGHT=1;
		public static final int MOVE=2;
		public static final int OFF_LIGHT=3;

		int room,light,step,act,actTarget;
		State parent;
		
		public State(int r, int l, int s) {
			this.room=r;
			this.light=l;
			this.step=s;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String line;
		int tc=1;
		while (!(line=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(line);
			int R=Integer.parseInt(st.nextToken());
			int D=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			
			boolean [][] adjMat=new boolean [R+1][R+1];
			for (int d=0;d<D;d++) {
				st=new StringTokenizer(br.readLine());
				int from=Integer.parseInt(st.nextToken());
				int to=Integer.parseInt(st.nextToken());
				adjMat[from][to]=adjMat[to][from]=true;
			}

			ArrayList<Integer> [] control=new ArrayList [R+1];
			for (int r=0;r<control.length;r++) control[r]=new ArrayList<>();
			for (int s=0;s<S;s++) {
				st=new StringTokenizer(br.readLine());
				control[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
			}

			LinkedList<State> q=new LinkedList<>();
			q.addLast(new State(1,1<<1,0));
			boolean [][] visited=new boolean [R+1][1<<(R+1)];
			visited[1][1<<1]=true;
			State ans=null;
			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.room==R && curr.light==(1<<R)) {
					ans=curr;
					break;
				}

				for (int nextRoom=1;nextRoom<adjMat.length;nextRoom++) if (adjMat[curr.room][nextRoom] && (curr.light&(1<<nextRoom))!=0) {
					// Find next bright room to move.
					State next=new State(nextRoom,curr.light,curr.step+1);
					if (!visited[next.room][next.light]) {
						visited[next.room][next.light]=true;
						next.act=State.MOVE;
						next.actTarget=next.room;
						next.parent=curr;
						q.addLast(next);
					}
				}

				// This room can control other room's light
				if (!control[curr.room].isEmpty()) {
					// toggle light on
					for (int i=0;i<control[curr.room].size();i++) {
						int trgRoom=control[curr.room].get(i);
						if (trgRoom==curr.room) continue; // Don't control own room!
						boolean trgRoomDark=(curr.light&(1<<trgRoom))==0;

						State next=new State(curr.room,curr.light^(1<<trgRoom),curr.step+1);
						if (!visited[next.room][next.light]) {
							visited[next.room][next.light]=true;
							next.act=trgRoomDark?State.ON_LIGHT:State.OFF_LIGHT;
							next.actTarget=trgRoom;
							next.parent=curr;
							q.addLast(next);
						}
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Villa #");
			sb.append(tc++);
			sb.append('\n');
			if (ans==null) sb.append("The problem cannot be solved.\n");
			else {
				sb.append("The problem can be solved in ");
				sb.append(ans.step);
				sb.append(" steps:\n");
				Stack<State> stk=new Stack<>();
				while (ans.act!=0) {
					stk.push(ans);
					ans=ans.parent;
				}
				while (!stk.isEmpty()) {
					ans=stk.pop();
					sb.append("- ");
					if (ans.act==State.ON_LIGHT) sb.append("Switch on light in");
					else if (ans.act==State.OFF_LIGHT) sb.append("Switch off light in");
					else if (ans.act==State.MOVE) sb.append("Move to");
					sb.append(" room ");
					sb.append(ans.actTarget);
					sb.append('.');
					sb.append('\n');
				}
			}
			System.out.println(sb);
			
			br.readLine(); // empty.
		}
	}

}