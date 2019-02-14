import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			char c=st.nextToken().charAt(0);
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			
			switch (c) {
				case 'r': System.out.println(Math.min(x, y));
							break;
				case 'k': System.out.println((int)Math.ceil(x*y/2.0));
							break;
				case 'Q': System.out.println(Math.min(x, y));
							break;
				case 'K': System.out.println((int)(Math.ceil(x/2.0))*(int)Math.ceil(y/2.0));
							break;
			}
		}
	}

}