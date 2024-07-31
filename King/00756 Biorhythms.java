import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int getX(int mi, int m) {
		// miXi = 1 mod m, find Xi = a mod m, return a
		int l=mi;
		int temp=1;
		while (temp<l || temp%l!=0) temp+=m;
		return temp/l;
	}

	public static void main (String [] args) throws Exception {
		final int m1=23;
		final int m2=28;
		final int m3=33;

		final int mi1=m2*m3;
		final int mi2=m1*m3;
		final int mi3=m1*m2;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("-1 -1 -1 -1")) {
			StringTokenizer st=new StringTokenizer(s);
			int r1=Integer.parseInt(st.nextToken());
			int r2=Integer.parseInt(st.nextToken());
			int r3=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());

			int M=m1*m2*m3;
			int x1=getX(mi1,m1);
			int x2=getX(mi2,m2);
			int x3=getX(mi3,m3);

			int dAbs=((x1*mi1*r1)%M+(x2*mi2*r2)%M+(x3*mi3*r3)%M)%M;
			while (dAbs<=d) dAbs+=M;
			int ans=(dAbs-d);

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(": the next triple peak occurs in ");
			sb.append(ans);
			sb.append(" days.");
			System.out.println(sb.toString());
		}
	}

}