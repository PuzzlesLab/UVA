import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int lastNum=-1;
			boolean flag=true;
			for (int i=0;i<5 && flag;i++) {
				int n=Integer.parseInt(st.nextToken());
				if (i>0) flag&=(lastNum+1==n);
				lastNum=n;
			}
			System.out.println(flag ? 'Y' : 'N');
		}
	}

}