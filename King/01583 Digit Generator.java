import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		int [] ansList=new int [100001];
		int [] digitSumCache=new int [100001];
		for (int i=1;i<digitSumCache.length;i++) {
			digitSumCache[i]=i%10;
			if (i>=10) digitSumCache[i]+=digitSumCache[i/10];
			
			int result=digitSumCache[i]+i;
			if (result<ansList.length && ansList[result]==0) ansList[result]=i;
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) System.out.println(ansList[Integer.parseInt(br.readLine())]);
	}
}