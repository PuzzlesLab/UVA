import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static boolean shouldClap(int n) {
		if (n%7==0) return true;
		while (n>0) {
			if (n%10==7) return true;
			n/=10;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken())-1;
			int K=Integer.parseInt(st.nextToken());

			/*
			 *  Simple approximation :
			 *  Consider every 10 numbers (i.e. 7, 17, 27) needs 1 clap, so we search for N*K claps.
			 *  
			 *  With multiples of 7, this bound should be smaller, but 10*N*K is good enough. ;)
			 */

			int MAX=10*N*K;
			int [] claps=new int [N];
			int count=1;
			int currPpl=0;
			boolean add=true;
			for (;count<=MAX;count++) {
				if (shouldClap(count)) {
					claps[currPpl]++;
					if (currPpl==M && claps[currPpl]==K) break;
				}

				if (currPpl==N-1) add=false;
				else if (currPpl==0) add=true;
				currPpl+=(add?1:-1);
			}
			
			if (count==MAX) count=-1;
			System.out.println(count);
		}
	}

}
