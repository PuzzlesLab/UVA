import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class State {
		int [] nums;
		int step,lastMove;
		long key;
		
		public State(int [] nums, int step, int lm) {
			this.nums=Arrays.copyOf(nums,nums.length);
			this.step=step;
			this.lastMove=lm;
		}
		
		public void computeKey() {
			this.key=0;
			for (int n=0;n<nums.length;n++) this.key=this.key*12+nums[n];
		}

		public State leftCw() {
			State s=new State(this.nums,this.step+1,1);
			s.nums[11]=this.nums[0];
			s.nums[5]=this.nums[11];
			
			for (int i=0;i<=4;i++) s.nums[i]=this.nums[i+1];
			
			s.computeKey();
			return s;
		}
		
		public State leftCcw() {
			State s=new State(this.nums,this.step+1,2);
			s.nums[0]=this.nums[11];
			s.nums[11]=this.nums[5];

			for (int i=0;i<=4;i++) s.nums[i+1]=this.nums[i];
			
			s.computeKey();
			return s;
		}
		
		public State rightCw() {
			State s=new State(this.nums,this.step+1,3);
			s.nums[11]=this.nums[5];
			for (int i=5;i<=10;i++) s.nums[i]=this.nums[i+1];

			s.computeKey();
			return s;
		}
		
		public State rightCcw() {
			State s=new State(this.nums,this.step+1,4);
			s.nums[5]=this.nums[11];
			for (int i=5;i<=10;i++) s.nums[i+1]=this.nums[i];

			s.computeKey();
			return s;
		}
		
		public State allCw() {
			State s=new State(this.nums,this.step+1,5);
			for (int i=0;i<s.nums.length;i++) s.nums[i]=this.nums[(i+1)%this.nums.length];
			
			s.computeKey();
			return s;
		}
		
		public State allCcw() {
			State s=new State(this.nums,this.step+1,6);
			s.nums[0]=this.nums[this.nums.length-1];
			for (int i=1;i<s.nums.length;i++) s.nums[i]=this.nums[i-1];

			s.computeKey();
			return s;
		}
	}
	
	private static HashMap<Long,Integer> computeForward(int maxDepth) {
		State END=new State(
			new int [] {1,2,3,4,5,6,7,8,9,10,11,12},
			0,
			0
		);
		END.computeKey();

		HashMap<Long,Integer> map=new HashMap<>();
		LinkedList<State> q=new LinkedList<>();
		q.add(END);
		map.put(END.key,END.step);
		
		while (!q.isEmpty()) {
			State curr=q.removeFirst();
			if (curr.step>maxDepth) continue;

			State [] nextStates={
				null,
				curr.lastMove==2?null:curr.leftCw(),
				curr.lastMove==1?null:curr.leftCcw(),
				curr.lastMove==4?null:curr.rightCw(),
				curr.lastMove==3?null:curr.rightCcw(),
				curr.lastMove==6?null:curr.allCw(),
				curr.lastMove==5?null:curr.allCcw(),
			};
			
			for (int i=1;i<nextStates.length;i++) if (nextStates[i]!=null) {
				if (!map.containsKey(nextStates[i].key)) {
					map.put(nextStates[i].key,nextStates[i].step);
					q.add(nextStates[i]);
				}
			}
		}
		return map;
	}
	
	private static int computeBackward(State start, int maxDepth, HashMap<Long,Integer> endStates) {
		HashSet<Long> visited=new HashSet<>();
		LinkedList<State> q=new LinkedList<>();
		q.add(start);
		visited.add(start.key);

		while (!q.isEmpty()) {
			State curr=q.removeFirst();
			if (endStates.containsKey(curr.key)) return curr.step+endStates.get(curr.key); // Found solution.
			if (curr.step>maxDepth) continue;
			
			State [] nextStates={
				null,
				curr.lastMove==2?null:curr.leftCw(),
				curr.lastMove==1?null:curr.leftCcw(),
				curr.lastMove==4?null:curr.rightCw(),
				curr.lastMove==3?null:curr.rightCcw(),
				curr.lastMove==6?null:curr.allCw(),
				curr.lastMove==5?null:curr.allCcw(),
			};

			for (int i=1;i<nextStates.length;i++) if (nextStates[i]!=null) {
				if (!visited.contains(nextStates[i].key)) {
					visited.add(nextStates[i].key);
					q.add(nextStates[i]);
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws Exception {
		HashMap<Long,Integer> dp=computeForward(9);
		// Meet in the middle TLE with 8+11 or 10+9. :/

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] nums=new int [st.countTokens()];
			for (int i=0;i<nums.length;i++) nums[i]=Integer.parseInt(st.nextToken());
			
			State init=new State(nums,0,0);
			init.computeKey();
			System.out.println(computeBackward(init,10,dp));
		}
	}

}