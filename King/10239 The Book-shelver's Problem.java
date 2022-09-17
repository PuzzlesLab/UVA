import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Book {
		double h,w;
	}

	private static Book [] Books;
	private static double W;
	private static double [] Dp;

	private static double compute(int n) {
		if (n==Books.length) return 0.0;

		if (Dp[n]<0) {
			double ans=1000000.0;
			double maxH=Books[n].h;
			double currW=Books[n].w;
			for (int i=n+1;i<=Books.length;i++) {
				ans=Math.min(ans,maxH+compute(i)); // Put in new shelve

				if (i<Books.length) { // Continue adding
					currW+=Books[i].w;
					maxH=Math.max(maxH,Books[i].h);
					if (currW>=W+0.000001) break;
				}
			}
			Dp[n]=ans;
		}

		return Dp[n];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			W=Double.parseDouble(st.nextToken());
			if (N==0 && W==0) break;
			
			Books=new Book[N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				Books[n]=new Book();
				Books[n].h=Double.parseDouble(st.nextToken());
				Books[n].w=Double.parseDouble(st.nextToken());
			}

			Dp=new double [N];
			Arrays.fill(Dp,-123);
			System.out.printf("%.4f\n",compute(0));
		}
	}

}
