import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static class SegmentTree {
		private int [] list;
		private int [] leafMap;
		private int [] tree;
		
		public SegmentTree(int [] list) {
			this.list=list;
			this.tree=new int [list.length*4];
			this.leafMap=new int [list.length];
			this.buildTree();
		}
		
		public void buildTree() { this.buildTree(1,0,list.length-1);}
		
		private void buildTree(int p, int L, int R) {
			if (L==R) {
				this.leafMap[L]=p;
				this.tree[p]=this.list[L];
			} else {
				int mid=(L+R)>>1;
				int p1=p<<1;
				int p2=p1+1;
				
				this.buildTree(p1, L, mid);
				this.buildTree(p2, mid+1, R);
				this.tree[p]=this.tree[p1]*this.tree[p2];
			}
		}
		
		public void update(int leaf, int v) {
			this.list[leaf]=v;
			int p=this.leafMap[leaf];
			this.tree[p]=v;
			while (p>0) {
				int parent=p>>1;
				int p1=parent<<1;
				int p2=p1+1;
				this.tree[parent]=this.tree[p1]*this.tree[p2];
				p>>=1;
			}
		}
		
		private int query(int p, int L, int R, int i, int j) {
			if (i>R || j<L) return 1;
			if (L>=i && R<=j) return this.tree[p];
			
			int mid=(L+R)>>1;
			int p1=p<<1;
			int p2=p1+1;

			return this.query(p1, L, mid, i, j)*this.query(p2, mid+1, R, i, j);
		}
		
		public int query(int i, int j) { return this.query(1,0,list.length-1,i,j);}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			int [] nums=new int [N];
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) {
				int num=Integer.parseInt(st.nextToken());
				if (num>0) nums[i]=1;
				else if (num<0) nums[i]=-1; //Multiplication will cause overflow later! :/
			}
			
			SegmentTree tree=new SegmentTree(nums);
			StringBuilder sb=new StringBuilder();
			for (int k=0;k<K;k++) {
				st=new StringTokenizer(br.readLine());
				char op=st.nextToken().charAt(0);
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				
				if (op=='C') {
					if (y>0) y=1;  //Multiplication will cause overflow later! :/
					else if (y<0) y=-1;
					tree.update(x-1,y);
				}
				else if (op=='P') {
					int result=tree.query(x-1,y-1);
					if (result==0) sb.append(0);
					else if (result>0) sb.append('+');
					else sb.append('-');
				}
			}
			System.out.println(sb.toString());
		}
		
	}

}