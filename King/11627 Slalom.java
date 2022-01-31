import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main { // Didn't pass in UVA (Runtime error), but passed in Kattis (slalom2), likely is UVA issue.
	
	private static class Reader {
		private BufferedReader br;
		private StringTokenizer st;
		
		public Reader() {
			this.br=new BufferedReader(new InputStreamReader(System.in));
			this.st=new StringTokenizer("");
		}
		
		private String next() throws Exception {
			while (!st.hasMoreTokens()) st=new StringTokenizer(br.readLine());
			return st.nextToken();
		}
		
		public int nextInt() throws Exception {
			return Integer.parseInt(this.next());
		}
	}
	
	
	private static class Gate {
		double x, y, x2;
		
		public Gate(double x, double y, double w) {
			this.x=x;
			this.y=y;
			this.x2=x+w;
		}
	}

	public static boolean possible(Gate [] gates, int vx, int vy) {
		if (gates.length==0) return false;
		
		double x1=gates[0].x;
		double x2=gates[0].x2;
		for (int i=1;i<gates.length;i++) {
			double time=(gates[i].y-gates[i-1].y)/(vy*1.0);
			double dx=vx*time;
			x1-=dx;
			x2+=dx;
			if (x2<gates[i].x || x1>gates[i].x2) return false;
			x1=Math.max(x1, gates[i].x);
			x2=Math.min(x2, gates[i].x2);
		}
		return true;
	}

	public static void main (String [] args) throws Exception {
		Reader reader=new Reader();
		int T=reader.nextInt();
		for (int t=0;t<T;t++) {
			int W = reader.nextInt();
			int vh = reader.nextInt();
			int N = reader.nextInt();

			Gate [] gates=new Gate[N];
			for (int n=0;n<N;n++) {
				gates[n]=new Gate(reader.nextInt(), reader.nextInt(), W);
			}

			int [] ski=new int[reader.nextInt()];
			int maxSki=0;
			for (int n=0;n<ski.length;n++) {
				ski[n]=reader.nextInt();
				maxSki=Math.max(maxSki, ski[n]);
			}

			int min=0;
			int max=maxSki+1;
			int maxSpeed=0;

			while (max-min>1) {
				int mid=(min+max)/2;
				if (possible(gates,vh,mid)) {
					min=mid;
					maxSpeed=Math.max(mid,maxSpeed);
				} else {
					max=mid;
				}
			}
			
			int ans=-1;
			for (int i=0;i<ski.length;i++) if (ski[i]<=maxSpeed) ans=Math.max(ski[i], ans);
			System.out.println(ans==-1? "IMPOSSIBLE" : ans);
		}
	}

}
