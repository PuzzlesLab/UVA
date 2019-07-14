import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			long value=Math.max(Long.parseLong(br.readLine())-180000,0);
			double tax=0.0;

			if (value>=300000) {
				tax+=300000*0.1;
				value-=300000;
			} else {
				tax+=value*0.1;
				value=0;
			}
			
			if (value>=400000) {
				tax+=400000*0.15;
				value-=400000;
			} else {
				tax+=value*0.15;
				value=0;
			}
			
			if (value>=300000) {
				tax+=300000*0.2;
				value-=300000;
			} else {
				tax+=value*0.2;
				value=0;
			}
			
			if (value>0) tax+=value*0.25;
			if (tax>0) tax=Math.max(tax, 2000);
			System.out.printf("Case %d: %.0f\n", testCase, Math.ceil(tax));
		}
	}

}