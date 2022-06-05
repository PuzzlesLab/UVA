import java.util.Scanner;

class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (sc.hasNextInt()) {
			int N=sc.nextInt();
			int M=sc.nextInt();
			
			int [] nums=new int[N+M];
			int total=0;
			int total2=0;
			for (int i=0;i<nums.length;i++) {
				nums[i]=sc.nextInt();;
				total+=nums[i];
				nums[i]+=50;
				total2+=nums[i];
			}

			N=Math.min(N,M); // Pick the smaller one will do, since the expr is relative.
			boolean [][] dp=new boolean [nums.length+1][total2+1];
			dp[0][0]=true;
			for (int i=0;i<nums.length;i++) {
				for (int used=Math.min(i+1,N);used>=1;used--) {
					for (int sum=dp[i].length-1-nums[i];sum>=0;sum--) {
						if (dp[used-1][sum]) dp[used][sum+nums[i]]=true;
					}
				}
			}
			
			int min=Integer.MAX_VALUE;
			int max=Integer.MIN_VALUE;
			for (int i=0;i<dp[N].length;i++) if (dp[N][i]) {
				int value=i-N*50;
				min=Math.min(min,value*(total-value));
				max=Math.max(max,value*(total-value));
			}

			System.out.printf("%d %d\n",max,min);
		}
	}

}
