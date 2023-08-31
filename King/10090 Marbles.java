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
		String s;
		while (!(s=br.readLine()).equals("0")) {
			long N=Long.parseLong(s);

			StringTokenizer st=new StringTokenizer(br.readLine());
			long c1=Long.parseLong(st.nextToken());
			long n1=Long.parseLong(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			long c2=Long.parseLong(st.nextToken());
			long n2=Long.parseLong(st.nextToken());

			Result r=extEuclid(n1,n2);
			long low=(long)Math.ceil((double)(-N*r.x)/n2);
			long high=(long)Math.floor((double)(N*r.y)/n1);
			
			if (N%r.d!=0 || low>high) {
				System.out.println("failed");
				continue;
			}
			long v1=c1*n2;
			long v2=c2*n1;
			if (v1>=v2) {
				r.x=r.x*N/r.d+n2/r.d*low;
				r.y=r.y*N/r.d-n1/r.d*low;
			} else {
				r.x=r.x*N/r.d+n2/r.d*high;
				r.y=r.y*N/r.d-n1/r.d*high;
			}
			System.out.println(r.x+" "+r.y);
		}
	}

}
