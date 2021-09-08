import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			long N=Integer.parseInt(br.readLine());
			int digitsCount=(int)Math.log10(N)+1;
			long sum=0;
			long tempN=N;
			while (tempN>0) {
				sum+=(int)Math.pow(tempN%10,digitsCount);
				tempN/=10;
			}
			System.out.println(N==sum? "Armstrong" : "Not Armstrong");
		}
	}

}
