import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			int N=Integer.parseInt(br.readLine());
			
			Stack<Integer> sol=new Stack<>();
			for (int c=1;c*c<=N;c++) { // let c=sqrt(N-X)
				int demoninator=c*c; // c*c = N-X
				int X=N-demoninator; // X=N-c*c
				if (X!=0 && X%c==0) sol.push(X); // Test X>0 and X%c.
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(t);
			sb.append(":");
			while (!sol.isEmpty()) {
				sb.append(' ');
				sb.append(sol.pop());
			}
			System.out.println(sb.toString());
		}
	}
}
