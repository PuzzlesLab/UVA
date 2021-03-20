import java.util.Scanner;

class Main {

	private static int [][] rotate(int [][] ary) {
		int [][] newAry=new int [2][2];
		for (int i=0;i<2;i++) for (int i2=0;i2<2;i2++) newAry[i][i2]=ary[i2][1-i];
		return newAry;
	}
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCaseCount=sc.nextInt();
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int [][] original=new int [2][2];
			for (int i=0;i<2;i++) for (int i2=0;i2<2;i2++) original[i][i2]=sc.nextInt();

			int [][] received=new int [2][2];
			for (int i=0;i<2;i++) for (int i2=0;i2<2;i2++) received[i][i2]=sc.nextInt();
			
			boolean found=false;
			for (int i=0;i<4 && !found;i++) {
				found = original[0][0]==received[0][0] && original[0][1]==received[0][1] && original[1][0]==received[1][0] && original[1][1]==received[1][1];
				original=rotate(original);
			}
			
			System.out.printf("Case #%d: %s\n", testCase, found ? "POSSIBLE": "IMPOSSIBLE");
		}
	}
}