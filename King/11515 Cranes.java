import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Crane {
		int x, y;
		long r;
		
		public Crane(int x, int y, long r) {
			this.x=x;
			this.y=y;
			this.r=r;
		}
		
		private boolean overlap(Crane c) {
			if (this==c) return true;

			long dx=this.x-c.x;
			long dy=this.y-c.y;
			long dist=dx*dx+dy*dy;
			long d2=this.r+c.r;
			return dist<=d2*d2;
		}
	}

	private static Crane [] Cranes;
	private static long Ans;

	private static void search(boolean [] flag, int currIdx) {
		if (currIdx==Cranes.length) {
			long currArea=0;
			for (int i=0;i<Cranes.length;i++) if (flag[i]) currArea+=Cranes[i].r*Cranes[i].r;
			Ans=Math.max(Ans,currArea);
			return;
		}

		boolean hasOverlap=false;
		for (int i=0;i<currIdx;i++) if (flag[i] && Cranes[i].overlap(Cranes[currIdx])) {
			hasOverlap=true;
			break;
		}
		if (!hasOverlap) {
			flag[currIdx]=true;
			search(flag,currIdx+1);
		}
		flag[currIdx]=false;
		search(flag,currIdx+1);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int C=Integer.parseInt(br.readLine());
			Cranes=new Crane[C];
			for (int c=0;c<C;c++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Cranes[c]=new Crane(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			Ans=0;
			search(new boolean [C],0);
			System.out.println(Ans);
		}
	}

}
