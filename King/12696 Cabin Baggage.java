import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		int totalAllowed=0;
		double L=56, WI=45, D=25, WE=7, S=125;
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double l=Double.parseDouble(st.nextToken());
			double wi=Double.parseDouble(st.nextToken());
			double d=Double.parseDouble(st.nextToken());
			double we=Double.parseDouble(st.nextToken());
			boolean flag=((l<=L && wi<=WI && d<=D) || (l+wi+d<=S)) && we<=WE ;
			totalAllowed+=flag ? 1 : 0;
			System.out.println(flag ? 1 : 0);
		}
		System.out.println(totalAllowed);
	}

}