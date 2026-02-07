import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Branch {
		int x, y;
		
		public Branch(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	private static class Tree {
		boolean doctor;
		Branch [] branches;
		
		public Tree(boolean d) {
			this.doctor=d;
		}
		
		public long distSq(Tree t) {
			long min=100000000L;
			for (int i=0;i<this.branches.length;i++) {
				Branch b1=this.branches[i];
				for (int i2=0;i2<t.branches.length;i2++) {
					Branch b2=t.branches[i2];
					long dx=b1.x-b2.x;
					long dy=b1.y-b2.y;
					min=Math.min(min,dx*dx+dy*dy);
				}
			}
			return min;
		}
	}

	private static int [] Parent;

	private static int getParent(int i) {
		if (Parent[i]==i) return i;
		return Parent[i]=getParent(Parent[i]);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			long K=Integer.parseInt(st.nextToken());
			K*=K;
			long D=Long.parseLong(st.nextToken());
			D*=D;
			
			Tree [] trees=new Tree [M+N];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				trees[m]=new Tree(true);
				trees[m].branches=new Branch[1];
				trees[m].branches[0]=new Branch(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			for (int n=0;n<N;n++) {
				trees[M+n]=new Tree(false);
				int B=Integer.parseInt(br.readLine());
				trees[M+n].branches=new Branch[B];
				for (int b=0;b<B;b++) {
					st=new StringTokenizer(br.readLine());
					trees[M+n].branches[b]=new Branch(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				}
			}
			
			// Link trees
			Parent=new int[trees.length];
			for (int i=0;i<Parent.length;i++) Parent[i]=i;
			for (int i=0;i<trees.length;i++) {
				for (int i2=i+1;i2<trees.length;i2++) {
					if (trees[i].doctor && trees[i2].doctor) continue;
					long cmp=(trees[i].doctor || trees[i2].doctor)?D:K;
					if (trees[i].distSq(trees[i2])<=cmp) Parent[getParent(i2)]=getParent(i);
				}
			}

			boolean exists=false;
			for (int m=0;m<M;m++) if (getParent(m)==getParent(M)) {
				exists=true;
				break;
			}
			System.out.println(exists?"Tree can be saved :)":"Tree can't be saved :(");
		}
	}

}