import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static HashMap<Integer,Integer> calc(int N) {
		HashMap<Integer,Integer> step=new HashMap<>();
		
		if (N==1) step.put(1,0);
		else if (N==2) {
			step.put(12,0);
			step.put(21,1);
		} else {
			int init=0;
			for (int n=1;n<=N;n++) init=init*10+n;
			
			step.put(init,0);
			LinkedList<Integer> q=new LinkedList<>();
			q.addLast(init);
			while (!q.isEmpty()) {
				int curr=q.removeFirst();
				int [] nums=new int [N];
				int temp=curr;
				for (int n=N-1;n>=0;n--) {
					nums[n]=temp%10;
					temp/=10;
				}
				for (int size=1;size<N-1;size++) for (int cutStart=0;cutStart+size<=N;cutStart++) {
					int cutEnd=cutStart+size;
					
					for (int insert=0;insert<cutStart;insert++) {
						int next=0;
						for (int i=0;i<insert;i++) next=next*10+nums[i];
						for (int i=cutStart;i<cutEnd;i++) next=next*10+nums[i];
						for (int i=insert;i<N;i++) if (i<cutStart || i>=cutEnd) next=next*10+nums[i];
						if (!step.containsKey(next)) {
							step.put(next,step.get(curr)+1);
							q.add(next);
						}
					}

					for (int insert=cutEnd;insert<=N;insert++) {
						int next=0;
						for (int i=0;i<insert;i++) if (i<cutStart || i>=cutEnd) next=next*10+nums[i];
						for (int i=cutStart;i<cutEnd;i++) next=next*10+nums[i];
						for (int i=insert;i<N;i++) next=next*10+nums[i];
						if (!step.containsKey(next)) {
							step.put(next,step.get(curr)+1);
							q.add(next);
						}
					}
				}
			}
		}
		
		return step;
	}

	public static void main(String[] args) throws Exception {
		HashMap<Integer,Integer> [] dp=new HashMap[10];
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int init=0;
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) init=init*10+Integer.parseInt(st.nextToken());

			if (dp[N]==null) dp[N]=calc(N);

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(": ");
			sb.append(dp[N].get(init));
			System.out.println(sb);
		}
	}

}