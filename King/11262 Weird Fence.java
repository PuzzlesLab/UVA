import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Pole {
		int x, y;
		
		public Pole(int x, int y) {
			this.x=x;
			this.y=y;
		}

		public long distSq(Pole p) {
			long dx=this.x-p.x;
			long dy=this.y-p.y;
			return dx*dx+dy*dy;
		}
	}

	private static ArrayList<Pole> Blue;
	private static ArrayList<Pole> Red;
	private static long [][] DistSq;
	private static long MaxDistSq;
	private static boolean [] Visited;
	private static int [] Pair;

	private static boolean match(int m) {
		for (int n=0;n<Red.size();n++) {
			if (DistSq[m][n]<=MaxDistSq && !Visited[n]) {
				Visited[n]=true;
				if (Pair[n]==-1 || match(Pair[n])) {
					Pair[n]=m;
					return true;
				}
			}
		}
		return false;
	}

	private static int compute(long limit) {
		if (Visited==null || Visited.length!=Red.size()) {
			Visited=new boolean [Red.size()];
			Pair=new int[Red.size()];
		}
		
		Arrays.fill(Pair,-1);
		MaxDistSq=limit;
		int count=0;
		for (int m=0;m<Blue.size();m++) {
			Arrays.fill(Visited,false);
			if (match(m)) count++;
		}
		return count;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			br.readLine();
			StringTokenizer st=new StringTokenizer(br.readLine());
			int P=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());

			Blue=new ArrayList<>();
			Red=new ArrayList<>();
			for (int p=0;p<P;p++) {
				st=new StringTokenizer(br.readLine());
				Pole pole=new Pole(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				String c=st.nextToken();
				if (c.equals("blue")) Blue.add(pole);
				else Red.add(pole);
			}
			
			if (Blue.size()<K || Red.size()<K) {
				System.out.println("Impossible");
				continue;
			}
			
			DistSq=new long[Blue.size()][Red.size()];
			long min=10000000;
			long max=0;
			for (int i=0;i<Blue.size();i++) for (int i2=0;i2<Red.size();i2++) {
				DistSq[i][i2]=Blue.get(i).distSq(Red.get(i2));
				min=Math.min(min,DistSq[i][i2]);
				max=Math.max(max,DistSq[i][i2]);
			}

			while (max>=min+2) {
				long mid=(min+max)>>1;
				if (compute(mid)>=K) max=mid;
				else min=mid;
			}
			if (min+1==max && compute(min)>=K) max=min; 
			long ans=(long)Math.ceil(Math.sqrt(max));
			//if ((ans-1)*(ans-1)>=max) ans-=1;
			System.out.println(ans);
		}
	}

}