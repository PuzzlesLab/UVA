import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static class State {
		int robot,blocked,step;
		int move1,move2;
		State parent;

		public State (int r, int b, int s, State st) {
			this.robot=r;
			this.blocked=b;
			this.step=s;
			this.parent=st;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken())-1;
			int T=Integer.parseInt(st.nextToken())-1;

			State init=new State(S,0,0,null);
			st=new StringTokenizer(br.readLine());
			for (int m=0;m<M;m++) {
				int n=Integer.parseInt(st.nextToken())-1;
				init.blocked|=1<<n;
			}
			
			ArrayList<Integer> [] adjList=new ArrayList [N];
			for (int n=0;n<N;n++) adjList[n]=new ArrayList<>();
			for (int n=0;n<N-1;n++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				if (n1==n2) continue;
				adjList[n1].add(n2);
				adjList[n2].add(n1);
			}

			State ans=null;

			boolean [][] visited=new boolean [N][1<<N];
			LinkedList<State> q=new LinkedList<>();
			q.add(init);
			visited[init.robot][init.blocked]=true;

			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.robot==T) {
					ans=curr;
					break;
				}
				
				// Try to move robot.
				for (int i=0;i<adjList[curr.robot].size();i++) {
					int nRobot=adjList[curr.robot].get(i);
					if ((curr.blocked&(1<<nRobot))==0) { // next node not blocked.
						State nS=new State(nRobot,curr.blocked,curr.step+1,curr);
						if (!visited[nS.robot][nS.blocked]) {
							visited[nS.robot][nS.blocked]=true;
							nS.move1=curr.robot+1;
							nS.move2=nRobot+1;
							q.add(nS);
						}
					}
				}

				for (int n=0;n<N;n++) if ((curr.blocked&(1<<n))!=0) {
					// Try to move block.
					for (int i=0;i<adjList[n].size();i++) {
						int nBlock=adjList[n].get(i);
						if (nBlock==curr.robot) continue; // Blocked by robot.
						if ((curr.blocked&(1<<nBlock))==0) { // next node not blocked.
							State nS=new State(curr.robot,curr.blocked,curr.step+1,curr);
							nS.blocked&=~(1<<n);
							nS.blocked|=(1<<nBlock);
							if (!visited[nS.robot][nS.blocked]) {
								visited[nS.robot][nS.blocked]=true;
								nS.move1=n+1;
								nS.move2=nBlock+1;
								q.add(nS);
							}
						}
					}
				}

			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			if (ans==null) sb.append("-1\n");
			else {
				sb.append(ans.step);
				sb.append('\n');
				Stack<State> stk=new Stack<>();
				while (ans.move1!=0) {
					stk.push(ans);
					ans=ans.parent;
				}
				while (!stk.isEmpty()) {
					ans=stk.pop();
					sb.append(ans.move1);
					sb.append(' ');
					sb.append(ans.move2);
					sb.append('\n');
				}
			}
			System.out.println(sb);
		}
	}

}