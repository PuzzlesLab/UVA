import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			long X=Long.parseLong(s);
			/* N-M=X, let N=10a+b, then M=a. 
			 * Hence 10a+b-a=X, 9a+b=X, a=(X-b)/9
			 * Constraint = 0 <= b < 10
			 */
			StringBuilder sb=new StringBuilder();
			for (int b=9;b>=0;b--) {
				long a=X-b;
				if (a%9!=0) continue;
				a/=9;
				sb.append(10*a+b);
				sb.append(' ');
			}
			if (sb.length()>0) sb.setLength(sb.length()-1);
			System.out.println(sb);
		}
	}

}
