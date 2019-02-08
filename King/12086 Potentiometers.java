import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static class SegmentTree {
		private int [] list;
		private long [] tree;
		private int [] leafMap;
		
		public SegmentTree(int [] list) {
			this.list=list;
			this.tree=new long [list.length*4];
			this.leafMap=new int [list.length];
			this.buildTree(1, 0, list.length-1);
		}
		
		private void buildTree(int p, int L, int R) {
			if (L==R) {
				this.tree[p]=this.list[L];
				this.leafMap[L]=p;
			} else {
				int mid=(L+R)>>1;
				int p1=p<<1;
				int p2=p1+1;
				
				this.buildTree(p1, L, mid);
				this.buildTree(p2, mid+1, R);
				this.tree[p]=this.tree[p1]+this.tree[p2];
			}
		}
		
		public void setValue(int leaf, int value) {
			int p=this.leafMap[leaf];
			this.tree[p]=value;
			while (p>0) {
				int parent=p>>1;
				int p1=parent<<1;
				int p2=p1+1;
				this.tree[parent]=this.tree[p1]+this.tree[p2];
				p>>=1;
			}
		}
		
		public long query (int p, int L, int R, int i, int j) {
			if (i>R || j<L) return -1;
			if (L>=i && R<=j) return this.tree[p];
			
			int mid=(L+R)>>1;
			int p1=p<<1;
			int p2=p1+1;
			
			long sum=0;
			long q1=this.query(p1,L,mid,i,j);
			if (q1!=-1) sum+=q1;
			long q2=this.query(p2,mid+1,R,i,j);
			if (q2!=-1) sum+=q2;
			
			return sum;
		}
		
		public long query (int i, int j) { return this.query(1,0,this.list.length-1,i,j); }
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int T=0;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int [] resistance=new int [N];
			for (int n=0;n<N;n++) resistance[n]=Integer.parseInt(br.readLine());
			
			StringBuilder sb=new StringBuilder();
			if (T>0) sb.append('\n');
			sb.append("Case ");
			sb.append(++T);
			sb.append(":\n");
			
			SegmentTree tree=new SegmentTree(resistance);
			while (!(s=br.readLine()).equals("END")) {
				StringTokenizer st=new StringTokenizer(s);
				char op=st.nextToken().charAt(0);
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				if (op=='S') tree.setValue(x-1,y);
				else if (op=='M') {
					sb.append(tree.query(x-1, y-1));
					sb.append('\n');
				}
			}
			
			System.out.print(sb.toString());
		}
	}

}
