import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [] In;
	private static int [] InMap;
	private static int [] Post;
	private static int PostIdx;
	private static int AnsLeastSum;
	private static int AnsLeastNode;

	private static void find(int from, int to, int currSum) {
		if (from>to || PostIdx==-1) return;
		currSum+=Post[PostIdx];
		if (from==to) {
			if (AnsLeastSum>currSum) {
				AnsLeastSum=currSum;
				AnsLeastNode=Post[PostIdx];
			} else if (AnsLeastSum==currSum) {
				AnsLeastNode=Math.min(AnsLeastNode,Post[PostIdx]);
			}
		}

		int inIdx=InMap[Post[PostIdx]];
		if (inIdx==-1) return;
		PostIdx--;

		find(inIdx+1,to,currSum);
		find(from,inIdx-1,currSum);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			
			In=new int [st.countTokens()];
			InMap=new int [10000];
			Arrays.fill(InMap,-1);
			for (int i=0;i<In.length;i++) {
				In[i]=Integer.parseInt(st.nextToken());
				InMap[In[i]]=i;
			}

			Post=new int [In.length];
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<Post.length;i++) Post[i]=Integer.parseInt(st.nextToken());

			AnsLeastSum=Integer.MAX_VALUE;
			AnsLeastNode=Integer.MAX_VALUE;
			PostIdx=Post.length-1;
			find(0,In.length-1,0);

			System.out.println(AnsLeastNode);
		}
	}

}
