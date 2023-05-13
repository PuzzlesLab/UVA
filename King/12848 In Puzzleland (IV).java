import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int gcd(int a, int b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int H1=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			int H2=Integer.parseInt(st.nextToken());

			int n=S*(H2-1);
			int d=H1-1; // Consider interval only.
			int f=gcd(n,d);
			n/=f;
			d/=f;
			int c=n/d;
			n%=d;

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(t);
			sb.append(':');
			if (c>0) {
				sb.append(' ');
				sb.append(c);
			}
			if (n>0) {
				sb.append(' ');
				sb.append(n);
				sb.append('/');
				sb.append(d);
			}
			System.out.println(sb.toString());
		}
	}

}
