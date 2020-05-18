import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			String [] initial=new String[N];
			for (int n=0;n<N;n++) initial[n]=br.readLine();
			String [] finall=new String[N];
			for (int n=0;n<N;n++) finall[n]=br.readLine();
			
			int iidx=N-1, fidx=N-1;
			while (iidx>=0) if (finall[fidx].equals(initial[iidx--])) fidx--;
			StringBuilder sb=new StringBuilder();
			while (fidx>=0) {
				sb.append(finall[fidx--]);
				sb.append('\n');
			}
			sb.append('\n');
			System.out.print(sb.toString());
		}
	}

}