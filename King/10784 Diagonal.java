import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=1;
		while (true) {
			long x=Long.parseLong(br.readLine());
			if (x==0) break;
			/*
			 *  N(N-3) / 2 = x
			 *  N^2 - 3N -2x = 0
			 *  
			 *  Given x, find N.
			 *  a=1, b=-3, c=-2x
			 */
			long N=(long)((3+Math.sqrt(9-4*1*-2*x))/2);
			long x1=(N*(N-3))>>1;
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(TC++);
			sb.append(": ");
			sb.append(x1==x ? N : N+1);
			System.out.println(sb);
		}
	}

}
