import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			int minArea=Integer.MAX_VALUE;
			for (int x=1;x<=N;x++) if (N%x==0) {
				int factor1=N/x;
				for (int y=x;y<=N;y++) if (factor1%y==0) {
					int z=factor1/y;
					int area=2*(x*y+y*z+z*x);
					minArea=Math.min(minArea, area);
				}
			}
			System.out.println(minArea);
		}
	}

}