import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		long init, rate;
		
		public Tuple(long i, long r) {
			this.init=i;
			this.rate=r;
		}
		
		public long count(long year) {
			return this.init+this.rate*year;
		}
	}

	private static Tuple [] Sship;
	private static Tuple [] Mammoths;
	private static int [][] Time;
	private static int [] Pair;
	private static boolean [] Visited;
	
	private static boolean canPair(long year, int si) {
		for (int i=0;i<Mammoths.length;i++) {
			if (Visited[i]) continue;
			// Strange: Solution time can be earlier then travel time?!
			if (Sship[si].count(year-Time[si][i])<Mammoths[i].count(year)) continue;
			Visited[i]=true;
			if (Pair[i]==-1 || canPair(year,Pair[i])) {
				Pair[i]=si;
				return true;
			}
		}
		return false;
	}

	private static boolean simulate(long year) {
		Pair=new int[Mammoths.length];
		Arrays.fill(Pair,-1);

		int count=0;
		for (int i=0;i<Sship.length;i++) {
			Visited=new boolean [Mammoths.length];
			if (canPair(year,i)) {
				count++;
				if (count==Mammoths.length) break;
			}
		}
		return count==Mammoths.length;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int H=Integer.parseInt(st.nextToken());
			int A=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			Sship=new Tuple [H];
			for (int h=0;h<H;h++) Sship[h]=new Tuple(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

			st=new StringTokenizer(br.readLine());
			Mammoths=new Tuple [A];
			for (int a=0;a<A;a++) Mammoths[a]=new Tuple(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

			Time=new int [H][A];
			for (int h=0;h<H;h++) {
				st=new StringTokenizer(br.readLine());
				for (int a=0;a<A;a++) Time[h][a]=Integer.parseInt(st.nextToken());
			}

			long min=0;
			long max=Integer.MAX_VALUE;
			long ans=-1;
			while (max>=min) {
				long mid=(min+max)>>1;
				if (simulate(mid)) {
					ans=mid;
					max=mid-1;
				} else min=mid+1;
			}
			System.out.println(ans!=-1?ans:"IMPOSSIBLE");
		}
	}

}
