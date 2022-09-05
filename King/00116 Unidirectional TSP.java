import java.util.Arrays;
import java.util.Scanner;

class Main {

	private static int [][] Nums;
	private static int [][] Dp;
	private static int [][] Next;

	private static int find(int m, int n) {
		if (Dp[m][n]==Integer.MAX_VALUE) {
			if (n==Nums[m].length-1) Dp[m][n]=Nums[m][n];
			else {
				int min=Integer.MAX_VALUE;

				int best=Integer.MAX_VALUE;
				int bestNextRow=-1;
				for (int dm=-1;dm<2;dm++) {
					int nm=m+dm;
					if (nm<0) nm=Nums.length-1;
					else if (nm>=Nums.length) nm=0;
					
					int curr=find(nm,n+1);
					if (curr<best) {
						best=curr;
						bestNextRow=nm;
					} else if (curr==best) bestNextRow=Math.min(bestNextRow, nm);
					
					Next[m][n]=bestNextRow;
					min=Math.min(min,curr+Nums[m][n]);
				}
				Dp[m][n]=min;
			}
		}
		return Dp[m][n];
	}

	private static String trace() {
		int minV=Integer.MAX_VALUE;
		int minRow=Integer.MAX_VALUE;
		for (int row=0;row<Nums.length;row++) {
			if (Dp[row][0]<minV) {
				minV=Dp[row][0];
				minRow=row;
			}
		}

		int [] solution=new int [Nums[0].length];
		int currRow=minRow;
		for (int col=0;col<Nums[0].length;col++) {
			solution[col]=currRow+1;
			currRow=Next[currRow][col];
		}

		StringBuilder sb=new StringBuilder();
		for (int n=0;n<Nums[0].length;n++) {
			sb.append(solution[n]);
			sb.append(' ');
		}
		sb.setLength(sb.length()-1);
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (sc.hasNextInt()) {
			int M=sc.nextInt();
			int N=sc.nextInt();
			Nums=new int [M][N];
			for (int m=0;m<M;m++) for (int n=0;n<N;n++) Nums[m][n]=sc.nextInt();
			
			Dp=new int [M][N];
			Next=new int [M][N];
			for (int m=0;m<M;m++) Arrays.fill(Dp[m],Integer.MAX_VALUE);
			for (int m=0;m<M;m++) Arrays.fill(Dp[m],Integer.MAX_VALUE);

			int ans=Integer.MAX_VALUE;
			for (int m=0;m<M;m++) ans=Math.min(ans,find(m,0));

			System.out.println(trace());
			System.out.println(ans);
		}
	}

}
