import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Point {
		long x, y;
		
		public Point(long x, long y) {
			this.x=x;
			this.y=y;
		}
	}

	private static long gcd(long a, long b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Point [] points=new Point[N+1];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points[n]=new Point(Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()));
			}
			points[N]=points[0];

			long area=0;
			for (int n=0;n<N;n++) area+=points[n].x*points[n+1].y-points[n+1].x*points[n].y;
			area=Math.abs(area);
			area>>=1;

			long B=0;
			for (int n=0;n<N;n++) {
				long dx=Math.abs(points[n].x-points[n+1].x);
				long dy=Math.abs(points[n].y-points[n+1].y);
				B+=gcd(dx,dy);
			}

                        // https://en.wikipedia.org/wiki/Pick%27s_theorem
			// A=i-1+b/2, i=A+1-b/2;
			System.out.println(area+1-(B>>1));
		}
 	}

}