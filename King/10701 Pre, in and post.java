import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static char [] Pre;
	private static int PreI;
	private static char [] In;
	private static int [] InMap;
	private static StringBuilder sb;
	
	private static void find(int min, int max) {
		if (min>max || PreI==Pre.length) return;

		int idx=InMap[Pre[PreI]];
		PreI++;
		find(min,idx-1);
		find(idx+1,max);
		sb.append(In[idx]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			Pre=st.nextToken().toCharArray();
			In=st.nextToken().toCharArray();

			InMap=new int [128];
			Arrays.fill(InMap,-1);
			for (int n=0;n<In.length;n++) InMap[In[n]]=n;
			PreI=0;
			sb=new StringBuilder();
			find(0,N-1);

			System.out.println(sb.toString());
		}
	}

}
