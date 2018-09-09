import java.util.HashSet;
import java.util.Scanner;

class Main {
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCaseCount=sc.nextInt();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int n=sc.nextInt(), m=sc.nextInt();
			boolean [][] flag=new boolean [n][m];
			for (int i=0;i<n;i++) for (int i2=0;i2<m;i2++) flag[i][i2]=sc.next().charAt(0)=='1';
			
			HashSet<String> set=new HashSet<>();
			boolean ans=true;
			for (int col=0;col<m;col++) {
				int count=0;
				for (int row=0;row<n;row++) if (flag[row][col]) count++;
				
				if (count==2) {
					String e="";
					for (int row=0;row<n;row++) if (flag[row][col]) e=e+row+"_";
					if (set.contains(e)) ans=false;
					else set.add(e);
				} else ans=false;
				
				if (!ans) break;
			}
			
			if (ans) System.out.println("Yes");
			else System.out.println("No");
		}
	}

}
