import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static class Job {
		int id, priority;
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			LinkedList<Job> queue=new LinkedList<>();
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) {
				Job j=new Job();
				j.id=n;
				j.priority=Integer.parseInt(st.nextToken());
				queue.addLast(j);
			}
			int ans=0;
			while (true) {
				int maxPriority=0;
				for (int i=0;i<queue.size();i++) maxPriority=Math.max(maxPriority,queue.get(i).priority);
				Job firstJob=queue.removeFirst();
				if (maxPriority>firstJob.priority) queue.addLast(firstJob);
				else {
					ans++;
					if (firstJob.id==M) break;
				}
			}
			
			System.out.println(ans);
		}
	}

}