import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Loc implements Comparable<Loc> {
		long x, y;
		
		public Loc(long x, long y) {
			this.x=x;
			this.y=y;
		}
		
		public double dist(Loc l) {
			double dx=this.x-l.x;
			double dy=this.y-l.y;
			return Math.sqrt(dx*dx+dy*dy);
		}

		public int compareTo(Loc l) {
			return (this.x!=l.x) ? Long.compare(this.x,l.x) : Long.compare(this.y,l.y);
		}
	}
	
	private static long K;
	private static int S;
	private static long D;
	private static Loc [] Guards;

	private static boolean simulate(long M) {
		int count=0;
		Loc sniper=null;
		long sy=K-M;
		for (int i=0;i<Guards.length;i++) {
			if (sniper==null || Guards[i].dist(sniper)>D) {
				if (count==S) return false; // Ran out of new sniper.
				// Place the new sniper as far as possible.
				// sqrt((Guard[i].x - sniper.x)^2 + (Guards[i].y-(K-M))^2) = D
				long dy=Guards[i].y-sy;
				if (D<dy) return false;
				long dx=(long)Math.sqrt(D*D-dy*dy);
				sniper=new Loc(Guards[i].x+dx,sy);
				count++;
				if (Guards[i].dist(sniper)>D) return false;
			}
		}
		return true;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			br.readLine(); // Empty.
			StringTokenizer st=new StringTokenizer(br.readLine());
			K=Long.parseLong(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			S=Integer.parseInt(st.nextToken());
			D=Long.parseLong(st.nextToken());

			Guards=new Loc[N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				Guards[n]=new Loc(Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()));
			}
			Arrays.sort(Guards);

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");

			if (!simulate(0)) sb.append("IMPOSSIBLE");
			else {
				long ans=0;
				long min=0;
				long max=D;
				while (max>=min) {
					long mid=(min+max)>>1;
					if (simulate(mid)) {
						ans=mid;
						min=mid+1;
					} else max=mid-1;
				}
				sb.append(ans);
			}

			System.out.println(sb);
		}
	}

}
