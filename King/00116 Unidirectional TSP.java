import java.util.Scanner;

class Main {

	private static int [][] Nums;
	private static boolean [][] DpExists;
	private static int [][] Dp;
	private static int [][] Next;

	private static int find(int m, int n) {
		if (!DpExists[m][n]) {
			if (n==Nums[m].length-1) Dp[m][n]=Nums[m][n];
			else {
				int lowestCost=Integer.MAX_VALUE;
				int lowestCostNextRow=-1;
				for (int dm=-1;dm<2;dm++) {
					int nm=m+dm;
					if (nm<0) nm=Nums.length-1;
					else if (nm>=Nums.length) nm=0;
					
					int curr=find(nm,n+1);
					if (curr<lowestCost) {
						lowestCost=curr;
						lowestCostNextRow=nm;
					} else if (curr==lowestCost) lowestCostNextRow=Math.min(lowestCostNextRow, nm);
				}

				Next[m][n]=lowestCostNextRow;
				Dp[m][n]=lowestCost+Nums[m][n];
			}
			DpExists[m][n]=true;
		}
		return Dp[m][n];
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (sc.hasNextInt()) {
			int M=sc.nextInt();
			int N=sc.nextInt();
			Nums=new int [M][N];
			for (int m=0;m<M;m++) for (int n=0;n<N;n++) Nums[m][n]=sc.nextInt();
			
			DpExists=new boolean [M][N];
			Dp=new int [M][N];
			Next=new int [M][N];

			int ans=Integer.MAX_VALUE;
			for (int m=0;m<M;m++) ans=Math.min(ans,find(m,0));

			int lowestCost=Integer.MAX_VALUE;
			int lowestCostRow=Integer.MAX_VALUE;
			for (int row=0;row<Nums.length;row++) {
				if (Dp[row][0]<lowestCost) {
					lowestCost=Dp[row][0];
					lowestCostRow=row;
				}
			}

			StringBuilder sb=new StringBuilder();
			int currRow=lowestCostRow;
			for (int col=0;col<Nums[0].length;col++) {
				sb.append(currRow+1);
				sb.append(' ');
				currRow=Next[currRow][col];
			}
			sb.setLength(sb.length()-1);

			System.out.println(sb.toString());
			System.out.println(ans);
		}
	}

}
