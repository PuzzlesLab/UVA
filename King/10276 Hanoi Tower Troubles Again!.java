import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int P=Integer.parseInt(br.readLine());
			
			int [] top=new int [P];
			
			boolean flag=true;
			int i=0;
			while (flag) {
				i++;
				flag=false;
				for (int p=0;p<P && !flag;p++) {
					if (top[p]==0) {
						flag=true;
						top[p]=i;
					} else {
						int sum=top[p]+i;
						int sqrt=(int)Math.sqrt(sum);
						if (sqrt*sqrt==sum) {
							flag=true;	
							top[p]=i;
						}
					}
				}
			}
			
			System.out.println(i-1);
		}
		
	}

}
