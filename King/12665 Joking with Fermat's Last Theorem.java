import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		final long LIMIT=1000000000; // Extra 0 since we shall calculate first n-1 digits later.
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int TC=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());

			int max=(int)Math.ceil(Math.cbrt(LIMIT));
			int solutions=0;
			for (int a=x;a<=max;a++) {
				int aCube=a*a*a;
				for (int b=a;b<=max;b++) {
					int bCube=b*b*b;

					int sum=aCube+bCube;
					int rem=sum%10;
					sum/=10;

					if (sum>y) break;
					if (sum>=x && sum<=y && rem%10==3) solutions++;
				}
			}
			
			System.out.printf("Case %d: %d\n",TC++,solutions*2);
		}
	}

}
