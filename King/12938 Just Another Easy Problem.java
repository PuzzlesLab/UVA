import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		boolean [] squareNumbers=new boolean [10000];
		int [] ans=new int [10000];
		for (int i=32;i*i<squareNumbers.length;i++) {
			int sq=i*i;
			squareNumbers[sq]=true;
			
			int[] digits=new int [4];
			int digitsPos=3;
			while (sq>0) {
				digits[digitsPos--]=sq%10;
				sq/=10;
			}

			for (int pos=0;pos<digits.length;pos++) {
				for (int diff=1;diff<10;diff++) {
					digits[pos]=(digits[pos]+1)%10;
					int currNumber=digits[0]*1000+digits[1]*100+digits[2]*10+digits[3];
					ans[currNumber]++;
				}
				digits[pos]=(digits[pos]+1)%10;
			}
		}
				
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			sb.append("Case ");
			sb.append(testCase);
			sb.append(": ");
			sb.append(ans[Integer.parseInt(br.readLine())]);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}
