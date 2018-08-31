import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			StringTokenizer st=new StringTokenizer(br.readLine());
			int C=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			boolean [] flag=new boolean [C];
			for (int n=0;n<N;n++) {
				String s=br.readLine();
				st=new StringTokenizer(s);
				st.nextToken();
				int total=st.countTokens();
				int [] numbers=new int [total];
				for (int i=0;i<numbers.length;i++) numbers[i]=Integer.parseInt(st.nextToken());
				
				if (br.readLine().charAt(0)=='=') for (int i=0;i<numbers.length;i++) flag[numbers[i]-1]=true;
			}
			int zeroCount=0;
			int index=-1;
			for (int i=0;i<flag.length;i++) if (!flag[i]) {
				zeroCount++;
				index=i+1;
			}
			if (zeroCount==1) System.out.println(index);
			else System.out.println(0);
			if (testCase<testCaseCount-1) System.out.println();
		}
	}

}