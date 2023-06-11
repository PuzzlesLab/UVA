import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [] StartIdx;

	private static class Tuple {
		int x, y;
		
		public Tuple (int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public double dist(Tuple t) {
			int dx=this.x-t.x;
			int dy=this.y-t.y;
			return Math.sqrt(dx*dx+dy*dy);
		}
		
		public String toString() {
			return this.x+","+this.y;
		}
	}

	private static int findStartIdx(int p) {
		int min=0;
		int max=StartIdx.length-1;
		while (min+1<max) {
			int mid=(min+max)>>1;
			if (StartIdx[min]==p) return min;

			if (StartIdx[mid]>p) max=mid;
			else min=mid;
		}
		return min;
	}

	private static Tuple getPos(int N) {
		if (N==0) return new Tuple(0,0);
		
		int sId=findStartIdx(N);
		int x=-sId+1;
		int y=1;
		int rem=N-StartIdx[sId];

		if (rem>0) { // Go right+up
			int d=Math.min(rem,-x);
			rem-=d;
			x+=d;
			y+=d;
		}
		if (rem>0) { // Go right+down
			int d=Math.min(rem,y);
			rem-=d;
			x+=d;
			y-=d;
		}
		if (rem>0) { // Go left+down
			int d=Math.min(rem,x);
			rem-=d;
			x-=d;
			y-=d;
		}
		if (rem>0) { // Go left+up
			int d=Math.min(rem,-y);
			rem-=d;
			x-=d;
			y+=d;
		}

		return new Tuple(x,y);
	}

	public static void main(String[] args) throws Exception {
		StartIdx=new int [23000];
		StartIdx[1]=1;
		int diff=4;
		for (int i=2;i<StartIdx.length;i++) {
			StartIdx[i]=StartIdx[i-1]+diff;
			diff+=4;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("-1 -1")){
			StringTokenizer st=new StringTokenizer(s);
			Tuple p1=getPos(Integer.parseInt(st.nextToken()));
			Tuple p2=getPos(Integer.parseInt(st.nextToken()));
			
			System.out.printf("%.2f\n",p1.dist(p2));
		}
	}

}
