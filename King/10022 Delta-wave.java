import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int x, y;

		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	private static Tuple getPosition(int N) {
		int level=(int)Math.sqrt(N);
		if (level*level<N) level++;

		int min=(level-1)*(level-1);
		int max=level*level;
		int mid=(min+max)/2;

		return new Tuple(level,N-mid);
	}

	private static int calcDist(Tuple p1, Tuple p2) {
		Tuple curr=new Tuple(p1.x,p1.y);
		int dist=0;
		boolean goDown=(p1.x&1)==(p1.y&1);
		// If triangle is top-down, goes down.
		// If triangle is down-top, goes left/right.
		while (curr.x<p2.x) {
			if (goDown) curr.x++;
			else curr.y+=(curr.y<p2.y)?1:-1;
			goDown=!goDown;
			dist++;
		}
		dist+=Math.abs(p2.y-curr.y);
		return dist;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine(); // empty

			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			if (N>M) {
				int temp=N;
				N=M;
				M=temp;
			}

			if (tc>0) System.out.println();
			System.out.println(calcDist(getPosition(N),getPosition(M)));
		}
	}

}
