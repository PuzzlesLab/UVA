import java.util.Arrays;
import java.util.Scanner;

class Main {

	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCaseCount=1;
		while (true) {
			int N=sc.nextInt();
			if (N==0) break;
			int [][] data=new int [N][2];
			for (int n=0;n<N;n++) {
				data[n][0]=sc.nextInt();
				data[n][1]=sc.nextInt();
			}
			
			Arrays.sort(data, (a,b) -> {
				return b[1]-a[1];
			});
			
			int max=data[0][0]+data[0][1];
			for (int n=1;n<N;n++) {
				data[n][0]+=data[n-1][0];
				data[n][1]+=data[n][0];
				max=Math.max(max, data[n][1]);
			}
			
			System.out.printf("Case %d: %d\n", testCaseCount++, max);
		}
	}

}
