import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());

			int [] nums=new int [N];
			nums[0]=1;
			nums[1]=2;
			nums[2]=3;
			for (int n=3;n<N;n++) nums[n]=((nums[n-1]+nums[n-2]+nums[n-3])%M)+1;
			//System.out.println(Arrays.toString(nums));
			
			int [] count=new int [K+1];
			int unique=0;
			int ans=Integer.MAX_VALUE;
			LinkedList<Integer> q=new LinkedList<>();
			for (int n=0;n<N;n++) {
				q.addLast(nums[n]);
				if (nums[n]>=1 && nums[n]<=K) {
					count[nums[n]]++;
					if (count[nums[n]]==1) unique++;
				}

				if (unique==K) {
					while (!q.isEmpty()) {
						int front=q.removeFirst();
						if (front>=1 && front<=K) {
							count[front]--;
							if (count[front]==0) {
								ans=Math.min(ans,q.size()+1);
								unique--;
								break;
							}
						}
					}
				}
			}
			if (ans==Integer.MAX_VALUE) {
				System.out.printf("Case %d: sequence nai\n",tc);
			} else {
				System.out.printf("Case %d: %d\n",tc,ans);
			}
		}
	}

}
