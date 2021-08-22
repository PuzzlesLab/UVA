import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int gcd(int a, int b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	public static void main (String [] args) throws Exception {	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			double first=(double)a/b;
			double n=Double.parseDouble(br.readLine());
			double second=first+n;
			
			int c=0;
			int d=1;
			while (true) {
				int cTest=(int)(Math.floor(second*d));
				double test=(double)cTest/d;
				if (test>first && test<=second) {
					c=cTest;
					break;
				}
				d++;
			}
			
			int gcd2=gcd(c,d);
			System.out.printf("%d %d\n",c/gcd2,d/gcd2);
		}
	}
}
