import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static long gcd(long a, long b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			long v1=Long.parseLong(st.nextToken());
			long d1=Long.parseLong(st.nextToken());
			long v2=Long.parseLong(st.nextToken());
			long d2=Long.parseLong(st.nextToken());

			long lcmV=(v1*v2)/gcd(v1,v2);
			d1=d1*(lcmV/v1);
			d2=d2*(lcmV/v2);

			long d=d1+d2;
			long v=lcmV<<1;
			long f=gcd(d,v);
			d/=f;
			v/=f;
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc++);
			if (d1<d2) sb.append(": You owe me a beer!");
			else sb.append(": No beer for the captain.");
			sb.append("\nAvg. arrival time: ");
			sb.append(d);
			if (v>1) {
				sb.append('/');
				sb.append(v);
			}
			System.out.println(sb.toString());
		}
	}

}