import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static class State {
		int [] game;
		int step, move;
		String key;
		State parent;
		
		public State(int [] g, int s, int m) {
			this.game=g;
			this.step=s;
			this.move=m;
			this.buildKey();
		}
		
		public State(State s) {
			this.game=Arrays.copyOf(s.game,s.game.length);
			this.step=s.step;
			this.key=s.key;
			this.parent=s;
		}
		
		private void buildKey() {
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<this.game.length;i++) {
				if (this.game[i]==10) sb.append('a');
				else sb.append(this.game[i]);
			}
			this.key=sb.toString();
		}

		public State leftClockwise() {
			State nS=new State(this);
			nS.game[0]=this.game[10];
			nS.game[1]=this.game[11];
			for (int i=2;i<=11;i++) nS.game[i]=this.game[i-2];
			nS.game[nS.game.length-3]=nS.game[9];
			nS.game[nS.game.length-2]=nS.game[10];
			nS.game[nS.game.length-1]=nS.game[11];

			nS.buildKey();
			return nS;
		}

		public State rightClockwise() {
			State nS=new State(this);
			nS.game[nS.game.length-2]=this.game[12];
			nS.game[nS.game.length-1]=this.game[13];
			for (int i=14;i<nS.game.length;i++) nS.game[i-2]=this.game[i];
			nS.game[9]=nS.game[nS.game.length-3];
			nS.game[10]=nS.game[nS.game.length-2];
			nS.game[11]=nS.game[nS.game.length-1];

			nS.buildKey();
			return nS;
		}

		public State leftAntiClockwise() {
			State nS=new State(this);
			nS.game[10]=this.game[0];
			nS.game[11]=this.game[1];
			for (int i=0;i<=9;i++) nS.game[i]=this.game[i+2];
			nS.game[nS.game.length-3]=nS.game[9];
			nS.game[nS.game.length-2]=nS.game[10];
			nS.game[nS.game.length-1]=nS.game[11];

			nS.buildKey();
			return nS;
		}
		
		public State rightAntiClockwise() {
			State nS=new State(this);
			nS.game[12]=this.game[nS.game.length-2];
			nS.game[13]=this.game[nS.game.length-1];
			for (int i=14;i<nS.game.length;i++) nS.game[i]=this.game[i-2];
			nS.game[9]=nS.game[nS.game.length-3];
			nS.game[10]=nS.game[nS.game.length-2];
			nS.game[11]=nS.game[nS.game.length-1];

			nS.buildKey();
			return nS;
		}

	}

	private static final State END=new State(new int [] {0,3,4,3,0,5,6,5,0,1,2,1,0,7,8,7,0,9,10,9,0,1,2,1},0,0);
	private static final int [] MOVES=new int [] {1,2,3,4};
	private static State MidState=null;

	private static HashMap<String,State> computeBack(State init) {
		HashMap<String,State> ans=new HashMap<>();
		LinkedList<State> q=new LinkedList<>();
		q.addLast(init);
		ans.put(init.key,init);

		while (!q.isEmpty()) {
			State curr=q.removeFirst();
			if (curr.step>8) break;
			State [] nextStates=new State [] {
				curr.move==3? null : curr.leftClockwise(),
				curr.move==4? null : curr.rightClockwise(),
				curr.move==1? null : curr.leftAntiClockwise(),
				curr.move==2? null : curr.rightAntiClockwise(),
			};
			for (int i=0;i<nextStates.length;i++) if (nextStates[i]!=null && !ans.containsKey(nextStates[i].key)) {
				ans.put(nextStates[i].key,nextStates[i]);
				nextStates[i].step++;
				nextStates[i].move=MOVES[i];
				q.addLast(nextStates[i]);
			}
		}
		
		return ans;
	}

	private static void computeForward(State init, HashMap<String,State> endStates) {
		HashMap<String,State> ans=new HashMap<>();
		LinkedList<State> q=new LinkedList<>();
		q.addLast(init);
		ans.put(init.key,init);

		while (!q.isEmpty()) {
			State curr=q.removeFirst();
			if (curr.step>8) break;
			if (endStates!=null && endStates.containsKey(curr.key)) {
				MidState=curr;
				break;
			}

			State [] nextStates=new State [] {
				curr.move==3? null : curr.leftClockwise(),
				curr.move==4? null : curr.rightClockwise(),
				curr.move==1? null : curr.leftAntiClockwise(),
				curr.move==2? null : curr.rightAntiClockwise(),
			};
			for (int i=0;i<nextStates.length;i++) if (nextStates[i]!=null && !ans.containsKey(nextStates[i].key)) {
				ans.put(nextStates[i].key,nextStates[i]);
				nextStates[i].step++;
				nextStates[i].move=MOVES[i];
				q.addLast(nextStates[i]);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		HashMap<String,State> endToX=computeBack(END);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			int [] nums=new int [24];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<nums.length;i++) nums[i]=Integer.parseInt(st.nextToken());
			
			State init=new State(nums,0,0);
			if (init.key.equals(END.key)) {
				System.out.println("PUZZLE ALREADY SOLVED");
				continue;
			}
			
			MidState=null;
			computeForward(init,endToX);

			if (MidState==null) System.out.println("NO SOLUTION WAS FOUND IN 16 STEPS");
			else {
				State rState=MidState;
				String midKey=rState.key;
				StringBuilder sb=new StringBuilder();

				Stack<State> stk=new Stack<>();
				while (rState.move!=0) {
					stk.push(rState);
					rState=rState.parent;
				}
				while (!stk.isEmpty()) sb.append(stk.pop().move);
				
				State lState=endToX.get(midKey);
				while (lState.move!=0) {
					sb.append(((lState.move-1+2)%4)+1); // reverse move.
					lState=lState.parent;
				}
				System.out.println(sb);
			}
		}
	}

}