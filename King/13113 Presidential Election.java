import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static class Candidate implements Comparable<Candidate> {
		int index;
		long votes;

		public Candidate(int idx) {
			this.index=idx;
		}
		
		public int compareTo(Candidate c) {
			if (this.votes==c.votes) return this.index-c.index;
			return ((Long)this.votes).compareTo(c.votes);
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			
			Candidate [] candidates=new Candidate[A];
			for (int a=0;a<A;a++) candidates[a]=new Candidate(a+1);

			long totalVotes=0;
			for (int b=0;b<B;b++) {
				st=new StringTokenizer(br.readLine());
				double [] percent=new double[A];
				for (int a=0;a<A;a++) percent[a]=Double.parseDouble(st.nextToken());
				long total=Integer.parseInt(st.nextToken());
				for (int a=0;a<A;a++) candidates[a].votes+=Math.round(total*percent[a])/100;
				totalVotes+=total;
			}
			Arrays.sort(candidates);
			
			if (testCase>0) System.out.println();
			Candidate lastCan=candidates[candidates.length-1];
			System.out.printf("%d %d\n", lastCan.index, lastCan.votes);
			if (lastCan.votes<=totalVotes*0.501) {
				Candidate lastCan2=candidates[candidates.length-2];
				System.out.printf("%d %d\n", lastCan2.index, lastCan2.votes);
			}
			
		}
	}
}