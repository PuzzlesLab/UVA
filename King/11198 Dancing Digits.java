import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class State {
		int [] nums;
		int step, key;

		public State(int [] nums, int step) {
			this.nums=nums;
			this.step=step;
			
			this.key=0;
			for (int n=0;n<this.nums.length;n++) this.key=this.key*10+Math.abs(nums[n]);
		}
	}

	public static void main(String[] args) throws Exception {
		final int END=12345678;
		boolean [] prime={false,false,true,true,false,true,false,true,false,false,false,true,false,true,false,false,false,true,false};

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int [] nums=new int [8];
			for (int i=0;i<nums.length;i++) nums[i]=Integer.parseInt(st.nextToken());
			
			int ans=-1;
			State init=new State(nums,0);
			boolean [] visited=new boolean [87654322];
			LinkedList<State> q=new LinkedList<>();
			q.addLast(init);
			visited[init.key]=true;

			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.key==END) {
					if (ans==-1) ans=curr.step;
					else ans=Math.min(ans,curr.step);
					break;
				}
				
				for (int i=0;i<curr.nums.length;i++) {
					boolean flag=curr.nums[i]>0;
					for (int insBefore=0;insBefore<=curr.nums.length;insBefore++) {
						if (insBefore==i || insBefore==i+1) continue; // useless to insert to before self / after self.
						
						int [] nextNums=null;
						if (insBefore==0) { // First.
							if (flag==curr.nums[insBefore]>0) continue;
							if (!prime[Math.abs(curr.nums[i])+Math.abs(curr.nums[insBefore])]) continue;
							nextNums=new int [8];
							int temp=0;
							nextNums[temp++]=curr.nums[i];
							for (int i2=0;i2<curr.nums.length;i2++) if (i!=i2) nextNums[temp++]=curr.nums[i2];
						} else if (insBefore==curr.nums.length) { // Last
							if (flag==curr.nums[insBefore-1]>0) continue;
							if (!prime[Math.abs(curr.nums[i])+Math.abs(curr.nums[insBefore-1])]) continue;
							nextNums=new int [8];
							int temp=0;
							for (int i2=0;i2<curr.nums.length;i2++) if (i!=i2) nextNums[temp++]=curr.nums[i2];
							nextNums[temp++]=curr.nums[i];
						} else { // Middle.
							boolean flag2=flag!=curr.nums[insBefore-1]>0 && prime[Math.abs(curr.nums[i])+Math.abs(curr.nums[insBefore-1])];
							boolean flag3=flag!=curr.nums[insBefore]>0 && prime[Math.abs(curr.nums[i])+Math.abs(curr.nums[insBefore])];
							if (!flag2 && !flag3) continue;

							nextNums=new int [8];
							int temp=0;

							if (i<insBefore) {
								nextNums[insBefore-1]=curr.nums[i];
								for (int i2=0;i2<curr.nums.length;i2++) {
									if (temp==insBefore-1) temp++;
									if (i!=i2) nextNums[temp++]=curr.nums[i2];
								}
							} else {
								nextNums[insBefore]=curr.nums[i];
								for (int i2=0;i2<curr.nums.length;i2++) {
									if (temp==insBefore) temp++;
									if (i!=i2) nextNums[temp++]=curr.nums[i2];
								}
							}
						}
						
						if (nextNums!=null) {
							State nS=new State(nextNums,curr.step+1);
							if (!visited[nS.key]) {
								visited[nS.key]=true;
								q.addLast(nS);
							}
						}
					}
				}
			}
			
			System.out.printf("Case %d: %d\n",tc++,ans);
		}
	}

}