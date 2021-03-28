import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			char [] street=br.readLine().toCharArray();

			boolean [] possible=new boolean [street.length];
			for (int i=0;i<possible.length;i++) possible[i]=true;
			
			for (int i=0;i<street.length;i++) {
				char c=street[i];
				if (c=='D') possible[i]=false;
				else if (c=='B') {
					for (int i2=i-2;i2<=i;i2++) if (i2>=0) possible[i2]=false;
				} else if (c=='S') {
					for (int i2=i-1;i2<=i+1;i2++) if (i2>=0 && i2<possible.length) possible[i2]=false;
				}
			}
			
			int count=0;
			for (int i=0;i<possible.length;i++) if (possible[i]) count++;
			
			System.out.printf("Case %d: %d\n", testCase, count);
		}
	}
}