import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int lsOne(int x) {
		return x&-x;
	}

	private static void add(int [] tree, int x, int v) {
		for (;x<tree.length;x+=lsOne(x)) tree[x]+=v;
	}
	
	private static int get(int [] tree, int x) {
		int r=0;
		for (;x>0;x-=lsOne(x)) r+=tree[x];
		return r;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			int [] tree=new int [N+M]; // N numbers + M empty extension nodes
			int [] pos=new int [N+1]; // Store position idx of number at lowest level of tree.
			for (int n=1;n<=N;n++) {
				pos[n]=N-n+1;
				add(tree,n,1);
			}

			int [] ans=new int [M];
			st=new StringTokenizer(br.readLine());
			for (int m=1;m<=M;m++) {
				int movie=Integer.parseInt(st.nextToken());
				ans[m-1]=N-get(tree,pos[movie]);
				add(tree,pos[movie],-1);
				pos[movie]=N+m;
				add(tree,pos[movie],1);
			}

			StringBuilder sb=new StringBuilder();
			for (int m=0;m<M;m++) {
				sb.append(ans[m]);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}
}
