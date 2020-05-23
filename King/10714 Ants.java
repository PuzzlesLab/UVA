import java.util.Arrays;
import java.util.Scanner;

class Main {
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCaseCount=sc.nextInt();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int L=sc.nextInt();
			int N=sc.nextInt();
			
			int [] ants=new int[N];
			for (int n=0;n<N;n++) ants[n]=sc.nextInt();
			Arrays.sort(ants);
			
			int shortest=Integer.MIN_VALUE;
			for (int n=0;n<N;n++) {
				int left=ants[n];
				int right=L-ants[n];
				shortest=Math.max(shortest, Math.min(left, right));
			}
			int longest=Math.max(L-ants[0], ants[ants.length-1]);
			
			System.out.printf("%d %d\n",shortest,longest);
		}
	}

}