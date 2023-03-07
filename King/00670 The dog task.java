import java.util.ArrayList;
import java.util.Scanner;

class Main {

	private static boolean [][] HasEdge;
	private static Movement [] Movements;
	private static Position [] PosM;

	private static class Position {
		int x, y, pair;

		public Position(int x, int y) {
			this.x=x;
			this.y=y;
			this.pair=-1;
		}
	}

	private static class Movement {
		Position s, e;
		double dist;
		boolean visited;
		Position ext;

		public Movement(Position s, Position e) {
			this.s=s;
			this.e=e;
			this.dist=distBetween(s,e);
		}
	}

	private static double distBetween(Position p1, Position p2) {
		int dx=p1.x-p2.x;
		int dy=p1.y-p2.y;
		return Math.sqrt(dx*dx+dy*dy);
	}

	private static boolean mcbm(int mIdx) {
		if (Movements[mIdx].visited) return false;
		
		Movements[mIdx].visited=true;
		for (int i=0;i<HasEdge[mIdx].length;i++) if (HasEdge[mIdx][i]) {
			if (PosM[i].pair==-1 || mcbm(PosM[i].pair)) {
				PosM[i].pair=mIdx;
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCaseCount=sc.nextInt();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=sc.nextInt();
			int M=sc.nextInt();

			Position [] pN=new Position [N];
			for (int n=0;n<N;n++) pN[n]=new Position(sc.nextInt(),sc.nextInt());

			PosM=new Position [M];
			for (int m=0;m<M;m++) PosM[m]=new Position(sc.nextInt(),sc.nextInt());

			Movements=new Movement[N-1];
			for (int n=0;n<N-1;n++) Movements[n]=new Movement(pN[n],pN[n+1]);

			HasEdge=new boolean [Movements.length][PosM.length];
			for (int i=0;i<Movements.length;i++) {
				for (int i2=0;i2<PosM.length;i2++) {
					double dist=distBetween(Movements[i].s,PosM[i2])+distBetween(PosM[i2],Movements[i].e);
					HasEdge[i][i2]=dist<=2*Movements[i].dist;
				}
			}

			for (int i=0;i<Movements.length;i++) {
				for (int i2=0;i2<Movements.length;i2++) Movements[i2].visited=false;
				mcbm(i);
			}
			for (int m=0;m<M;m++) if(PosM[m].pair!=-1) Movements[PosM[m].pair].ext=PosM[m];

			ArrayList<Position> ans=new ArrayList<>();
			for (int i=0;i<Movements.length;i++) {
				if (i==0) ans.add(Movements[i].s);
				if (Movements[i].ext!=null) ans.add(Movements[i].ext);
				ans.add(Movements[i].e);
			}
			
			StringBuilder sb=new StringBuilder();
			if (testCase>0) sb.append('\n');
			sb.append(ans.size());
			sb.append('\n');
			for (Position p: ans) {
				sb.append(p.x);
				sb.append(' ');
				sb.append(p.y);
				sb.append(' ');
			}
			if (sb.length()>0) sb.setLength(sb.length()-1);

			System.out.println(sb.toString());
		}
	}

}
