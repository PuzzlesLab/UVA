import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			int pairCount=0;
			boolean [] inPair=new boolean [N+1];
			// 1000 * 1000 = 1,000,000 (Max)
			// Euclid's formula!!
			for (int m=1;m<1000;m++) {
				for (int n=1;n<m;n++) {
					long x=m*m-n*n;
					long y=2*m*n;
					long z=m*m+n*n;
					
					if (x>N || y>N || z>N) break;
					
					long xt=x, yt=y, zt=z;
					while (xt<=N && yt<=N && zt<=N) {
						inPair[(int)xt]=true;
						inPair[(int)yt]=true;
						inPair[(int)zt]=true;

						if (gcd(xt,yt)==1 && gcd(yt,zt)==1 && gcd(zt,xt)==1) pairCount++;

						xt+=x;
						yt+=y;
						zt+=z;
					}
				}
			}

			int notInPair=0;
			for (int i=1;i<inPair.length;i++) if (!inPair[i]) notInPair++;
			
			System.out.printf("%d %d\n",pairCount, notInPair);
		}
	}

}