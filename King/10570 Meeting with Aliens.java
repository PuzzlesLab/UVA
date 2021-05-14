import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int [] nums=new int [N];
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) nums[n]=Integer.parseInt(st.nextToken());
			
			int [] numsSorted=Arrays.copyOf(nums, nums.length);
			Arrays.sort(numsSorted);

			int [][] possibleSeq=new int [N*2][nums.length];
			for (int start=0;start<N;start++) {
				for (int n=0;n<N;n++) possibleSeq[start*2][n]=numsSorted[(start+n)%nums.length];
				for (int n=0;n<N;n++) possibleSeq[start*2+1][n]=numsSorted[nums.length-1-((start+n)%nums.length)];
			}

			int minSwap=Integer.MAX_VALUE;

			for (int i=0;i<possibleSeq.length;i++) {
				int [] currSeq=possibleSeq[i];

				int [] currSeqLoc=new int [N+1];
				for (int n=0;n<N;n++) currSeqLoc[currSeq[n]]=n;
				
				int diff=0;

				# Swap from result to original.
				for (int n=0;n<N;n++) if (currSeq[n]!=nums[n]) {
					int temp=currSeq[n];
					currSeq[n]=nums[n];
					currSeq[currSeqLoc[nums[n]]]=temp;
					
					currSeqLoc[temp]=currSeqLoc[nums[n]];
					currSeqLoc[nums[n]]=n;

					diff++;
				}
				minSwap=Math.min(minSwap, diff);
			}
			
			System.out.println(minSwap);
		}
	}
}
