import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	private static class Tree implements Comparable<Tree> {
		int id,x,y,v,l;
		
		public Tree(int id, String s) {
			this.id=id;
			StringTokenizer st=new StringTokenizer(s);
			this.x=Integer.parseInt(st.nextToken());
			this.y=Integer.parseInt(st.nextToken());
			this.v=Integer.parseInt(st.nextToken());
			this.l=Integer.parseInt(st.nextToken());
		}

		public int compareTo(Tree t) {
			return (this.x!=t.x) ? this.x-t.x : this.y-t.y;
		}
		
		public double dist(Tree t) {
			double dx=this.x-t.x;
			double dy=this.y-t.y;
			return Math.sqrt(dx*dx+dy*dy);
		}
	}

	private static final double MAX_COST=10000000.0;
	private static Tree [] Trees;
	private static double AnsCost;
	private static double AnsExtraWood;
	private static ArrayList<Integer> AnsCutTree;

	private static boolean ccw(Tree p, Tree q, Tree r) {
		int pqx=q.x-p.x;
		int pqy=q.y-p.y;
		int prx=r.x-p.x;
		int pry=r.y-p.y;
		return pqx*pry-pqy*prx>0;
	}

	private static double convexHull(int keepMask) {
		Tree [] h=new Tree[2*Trees.length];
		int k=0;
		for (int i=0;i<Trees.length;i++) if ((keepMask&(1<<i))!=0) {
			while ((k>=2) && !ccw(h[k-2],h[k-1],Trees[i])) k--;
			h[k++]=Trees[i];
		}
		for (int i=Trees.length-1,t=k+1;i>=0;i--) if ((keepMask&(1<<i))!=0) {
			while ((k>=t) && !ccw(h[k-2],h[k-1],Trees[i])) k--;
			h[k++]=Trees[i];
		}
		double len=0.0;
		for (int i=0;i<k-1;i++) len+=h[i].dist(h[i+1]);
		return len;
	}

	private static void find(int curr, int keepMask, int woodL) {
		if (curr==Trees.length) {
			if (keepMask==0) return; // All trees are cut.
			
			double chLen=convexHull(keepMask);
			if (woodL<chLen) return; // Not enough to build fence.

			double cutCost=0.0;
			int cutCount=0;
			for (int i=0;i<Trees.length;i++) if ((keepMask&(1<<i))==0) {
				cutCost+=Trees[i].v;
				cutCount++;
			}
			if (cutCost>AnsCost) return;
			if (cutCost==AnsCost && cutCount>=AnsCutTree.size()) return;

			AnsCost=cutCost;
			AnsExtraWood=woodL-chLen;
			AnsCutTree.clear();
			for (int i=0;i<Trees.length;i++) if ((keepMask&(1<<i))==0) AnsCutTree.add(Trees[i].id);
			return;
		}
		find(curr+1,keepMask,woodL+Trees[curr].l);
		find(curr+1,keepMask|(1<<curr),woodL);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Trees=new Tree[N];
			for (int n=0;n<N;n++) Trees[n]=new Tree(n+1,br.readLine());
			Arrays.sort(Trees);
			
			AnsCost=MAX_COST;
			AnsCutTree=new ArrayList<>();
			find(0,0,0);
			Collections.sort(AnsCutTree);
			
			StringBuilder sb=new StringBuilder();
			if (tc>1) sb.append('\n');
			sb.append("Forest ");
			sb.append(tc++);
			sb.append("\nCut these trees:");
			for (int i=0;i<AnsCutTree.size();i++) {
				sb.append(' ');
				sb.append(AnsCutTree.get(i));
			}
			sb.append("\nExtra wood: ");
			sb.append(String.format("%.2f",AnsExtraWood));
			System.out.println(sb);
		}
	}

}
