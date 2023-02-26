import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static char [] Pre;
	private static char [] In;
	private static int [] InMap;
	private static int PreIdx;
	private static StringBuilder Ans;

	private static void traverse(int min, int max) {
		if (min>max || PreIdx==Pre.length) return;

		int inIdx=InMap[Pre[PreIdx]];
		if (inIdx==-1) return;

		PreIdx++;
		traverse(min,inIdx-1);
		traverse(inIdx+1,max);
		Ans.append(In[inIdx]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			Pre=st.nextToken().toCharArray();
			In=st.nextToken().toCharArray();

			InMap=new int [128];
			Arrays.fill(InMap,-1);
			for (int i=0;i<Pre.length;i++) InMap[In[i]]=i;

			PreIdx=0;
			Ans=new StringBuilder();
			traverse(0,Pre.length-1);
			System.out.println(Ans);
		}
	}

}
