import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Interval implements Comparable<Interval> {
		double angL, angR;
		
		public Interval(String line) {
			StringTokenizer st=new StringTokenizer(line);
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());

			double ang1=Math.atan2(y1,x1);
			double ang2=Math.atan2(y2,x2);
			this.angL=Math.min(ang1,ang2);
			this.angR=Math.max(ang1,ang2);
		}

		public int compareTo(Interval intv) {
			return (this.angL!=intv.angL) ? Double.compare(this.angL,intv.angL) : Double.compare(this.angR,intv.angR);
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int B=Integer.parseInt(s);
			Interval [] boards=new Interval[B];
			for (int b=0;b<B;b++) boards[b]=new Interval(br.readLine());
			Arrays.sort(boards);
			
			int ans=0;
			for (int b=0;b<B;b++) {
				double end=boards[b].angR;
				for (int b2=b;b2<B && end>=boards[b2].angL;b2++) {
					end=Math.min(end,boards[b2].angR);
					b=b2;
				}
				ans++;
			}
			
			System.out.println(ans);
		}
	}

}
