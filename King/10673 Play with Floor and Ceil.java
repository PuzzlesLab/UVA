import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Result {
		long x, y, d;
		
		public Result(long x, long y, long d) {
			this.x=x;
			this.y=y;
			this.d=d;
		}
	}

	private static Result extEuclid(long a, long b) {
		long y=0;
		long x=1;
		long xx=0;
		long yy=1;
		while (b!=0) {
			long q=a/b;
			long t=b; b=a%b; a=t;
			t=xx; xx=x-q*xx; x=t;
			t=yy; yy=y-q*yy; y=t;
		}
		return new Result(x,y,a);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long x=Long.parseLong(st.nextToken());
			long k=Long.parseLong(st.nextToken());
			long A=x/k;
			long B=(long)Math.ceil((double)x/k);
			Result r=extEuclid(A,B);
			long m=x/r.d;

			r.x*=m;
			r.y*=m;
			System.out.printf("%d %d\n",r.x,r.y);
		}
	}

}
