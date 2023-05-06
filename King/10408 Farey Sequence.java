import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	private static class Fraction implements Comparable<Fraction> {
		private final int num, dem;
		private final String str;
		
		public Fraction(int n, int d) {
			this.num=n;
			this.dem=d;
			
			StringBuilder sb=new StringBuilder();
			sb.append(this.num);
			sb.append('/');
			sb.append(this.dem);
			this.str=sb.toString();
		}

		public double value() {
			return this.num/(double)this.dem;
		}

		public int compareTo(Fraction f) {
			return Double.compare(this.value(),f.value());
		}
		
		public String toString() {
			return this.str;
		}
	}

	private static int gcd(int a, int b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			ArrayList<Fraction> series=new ArrayList<>();
			for (int n1=1;n1<=N;n1++) for (int n2=1;n2<=n1;n2++) {
				if (gcd(n1,n2)!=1) continue;
				series.add(new Fraction(n2,n1));
			}
			Collections.sort(series);

			System.out.println(series.get(K-1));
		}
	}
}
