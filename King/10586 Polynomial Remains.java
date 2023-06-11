import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static class Term {
		int co;
		int pow;
		
		public Term(int co, int pow) {
			this.co=co;
			this.pow=pow;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			if (N==-1 && K==-1) break;

			st=new StringTokenizer(br.readLine());
			Term [] terms=new Term[N+1];
			for (int i=0;i<=N;i++) terms[i]=new Term(Integer.parseInt(st.nextToken()),i);

			Term [] quotient=new Term[terms.length];
			// Divide by x^k+1;
			for (int i=N;i>=K;i--) {
				int p=terms[i].pow-K;
				quotient[i]=new Term(terms[i].co,p);
				terms[p].co-=terms[i].co;
				terms[i].co=0;

			}

			boolean flag=false;
			Stack<Integer> stk=new Stack<>();
			for (int i=N;i>=0;i--) {
				if (terms[i].co!=0) flag=true;
				if (flag) stk.push(terms[i].co);
			}

			if (flag) {
				StringBuilder sb=new StringBuilder();
				while (!stk.empty()) {
					sb.append(stk.pop());
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				System.out.println(sb.toString());
			}
			else System.out.println(0);
		}
	}

}
