import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static int [] calc1(int num, int base) {
		int tempN=num;
		ArrayList<Integer> digits=new ArrayList<>();
		while (tempN>0) {
			digits.add(tempN%base);
			tempN/=base;
		}
		int [] digitsA=new int [digits.size()];
		for (int i=0;i<digitsA.length;i++) digitsA[i]=digits.get(i);
		return digitsA;
	}

	private static int calc2(int [] digits, int base) {
		int multi=1;
		int value=0;
		for (int i=0;i<digits.length;i++) {
			value+=digits[i]*multi;
			multi*=base;
		}
		return value;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int B1=Integer.parseInt(st.nextToken());
			int B2=Integer.parseInt(st.nextToken());
			int r1=Integer.parseInt(st.nextToken());
			int r2=Integer.parseInt(st.nextToken());
			
			int sol=-1;
			for (int i=r2-1;i>r1;i--) { // i = base 10
				int [] digits1=calc1(i,B1); // Convert i to (digits)B1
				int v=calc2(digits1,B2);
				if (v%i==0) {
					sol=i;
					break;
				}
			}

			if (sol==-1) System.out.println("Non-existent.");
			else System.out.println(sol);
		}
	}
}
