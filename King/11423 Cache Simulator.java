import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int lsOne(int n) {
		return n&-n;
	}
	
	private static void add(int [] tree, int n, int d) {
		for (;n<tree.length;n+=lsOne(n)) tree[n]+=d;
	}
	
	private static int query(int [] tree, int n) {
		int sum=0;
		for (;n>0;n-=lsOne(n)) sum+=tree[n];
		return sum;
	}

	private static void doAccess(int addr, int [] tree, int [] position, int [] cacheSize, int [] miss, int seq) {
		if (position[addr]==0) { // First access
			for (int c=0;c<cacheSize.length;c++) miss[c]++;
		} else {
			int diff=query(tree,seq)-query(tree,position[addr]);
			for (int c=0;c<cacheSize.length;c++) { // Later access
				if (diff>=cacheSize[c]) miss[c]++;
			}
			add(tree,position[addr],-1);
		}
		position[addr]=seq;
		add(tree,seq,1);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		int [] cacheSize=new int [N];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for (int n=0;n<N;n++) cacheSize[n]=Integer.parseInt(st.nextToken());
		
		int [] miss=new int [N];
		int [] tree=new int [10000000];
		int [] position=new int [1<<24];
		int seq=1;
		while (true) {
			st=new StringTokenizer(br.readLine());
			String opCode=st.nextToken();
			if (opCode.equals("RANGE")) {
				int b=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int n=Integer.parseInt(st.nextToken());
				for (int k=0;k<n;k++) {
					doAccess(b+y*k,tree,position,cacheSize,miss,seq);
					seq++;
				}
			} else if (opCode.equals("ADDR")) {
				doAccess(Integer.parseInt(st.nextToken()),tree,position,cacheSize,miss,seq);
				seq++;
			} else if (opCode.equals("STAT")) {
				StringBuilder sb=new StringBuilder();
				for (int n=0;n<N;n++) {
					sb.append(miss[n]);
					sb.append(' ');
					miss[n]=0;
				}
				sb.setLength(sb.length()-1);
				System.out.println(sb.toString());
			} else if (opCode.equals("END")) {
				break;
			}
		}

	}
}
