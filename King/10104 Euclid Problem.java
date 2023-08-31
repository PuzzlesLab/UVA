import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Result {
		int x, y, d;
		
		public Result(int x, int y, int d) {
			this.x=x;
			this.y=y;
			this.d=d;
		}
	}

	private static Result extEuclid(int a, int b) {
		int y=0;
		int x=1;
		int xx=0;
		int yy=1;
		while (b!=0) {
			int q=a/b;
			int t=b; b=a%b; a=t;
			t=xx; xx=x-q*xx; x=t;
			t=yy; yy=y-q*yy; y=t;
		}
		return new Result(x,y,a);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			Result r=extEuclid(A,B);
			System.out.printf(r.x+" "+r.y+" "+r.d);
		}
	}

}