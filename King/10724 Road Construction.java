import java.util.Arrays;
import java.util.Scanner;

class Main {

	private static class Node {
		int x, y;
		
		public Node(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	private static class Solution implements Comparable<Solution> {
		int u, v;
		double dist, sum;

		public Solution(int u, int v, double dist, double sum) {
			this.u=u;
			this.v=v;
			this.dist=dist;
			this.sum=sum;
		}
		
		public int compareTo(Solution s) {
			if (this.sum!=s.sum) return Double.compare(s.sum,this.sum);
			if (this.dist!=s.dist) return Double.compare(this.dist,s.dist);
			if (this.u!=s.u) return this.u-s.u;
			return this.v-s.v;
		}
	}

	private static double distBetween(Node n1, Node n2) {
		double dx=n1.x-n2.x;
		double dy=n1.y-n2.y;
		return Math.sqrt(dx*dx+dy*dy);
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (true) {
			int N=sc.nextInt();
			int M=sc.nextInt();
			if (N==0 && M==0) break;
			
			Node [] nodes=new Node[N];
			for (int n=0;n<N;n++) nodes[n]=new Node(sc.nextInt(),sc.nextInt());

			final double MAX=1000000;
			double [][] preCost=new double [N][N];
			boolean [][] existsRoad=new boolean [N][N];
			for (int n=0;n<preCost.length;n++) {
				Arrays.fill(preCost[n],MAX);
				preCost[n][n]=0;
			}
			
			for (int m=0;m<M;m++) {
				int u=sc.nextInt()-1;
				int v=sc.nextInt()-1;
				preCost[u][v]=preCost[v][u]=Math.min(preCost[u][v],distBetween(nodes[u],nodes[v]));
				existsRoad[u][v]=existsRoad[v][u]=true;
			}
			for (int k=0;k<nodes.length;k++) for (int i=0;i<nodes.length;i++) for (int j=0;j<nodes.length;j++) {
				preCost[i][j]=Math.min(preCost[i][j],preCost[i][k]+preCost[k][j]);
			}

			Solution best=null;
			for (int u=0;u<N;u++) for (int v=u+1;v<N;v++) if (!existsRoad[u][v]) {
				double newRoadDist=distBetween(nodes[u],nodes[v]);

				double sum=0;
				for (int i=0;i<N;i++) for (int i2=0;i2<N;i2++) {
					double curCost=preCost[i][u]+newRoadDist+preCost[v][i2];
					curCost=Math.min(curCost,preCost[i][i2]);
					sum+=(preCost[i][i2]-curCost);
				}
				if (sum>1.0) {
					Solution curr=new Solution(u+1,v+1,distBetween(nodes[u],nodes[v]),sum);
					if (best==null || curr.compareTo(best)<0) best=curr;
				}
			}
			if (best!=null && best.sum<=1.0) best=null;

			System.out.println(best==null?"No road required":best.u+" "+best.v);
		}
	}

}
