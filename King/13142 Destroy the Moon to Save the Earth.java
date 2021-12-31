import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double T=Double.parseDouble(st.nextToken())*24*3600; // To second
			double S=Double.parseDouble(st.nextToken()); // Useless
			double D=Double.parseDouble(st.nextToken())*1000000; // To mm
			
			double mass=D/T;
			if ((int)mass>0) System.out.printf("Remove %d tons\n", (int)mass);
			else System.out.printf("Add %d tons\n", (int)Math.abs(mass));
		}
	}

}
