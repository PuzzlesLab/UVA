import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Term {
		int co, pow;
		
		public Term(int co, int pow) {
			this.co=co;
			this.pow=pow;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int K=Integer.parseInt(s);
			StringTokenizer st=new StringTokenizer(br.readLine());
			int termsCount=st.countTokens();
			Term [] terms=new Term[termsCount];
			for (int i=0;i<terms.length;i++) terms[i]=new Term(Integer.parseInt(st.nextToken()),terms.length-i);

			Term [] quotient=new Term[termsCount];
			// Divide by x-k;
			for (int i=0;i<terms.length-1;i++) {
				quotient[i]=new Term(terms[i].co,terms[i].pow-1);
				terms[i+1].co-=-K*terms[i].co;
			}

			StringBuilder sb=new StringBuilder();
			sb.append("q(x):");
			for (int i=0;i<terms.length-1;i++) {
				sb.append(' ');
				sb.append(terms[i].co);
			}
			sb.append("\nr = ");
			sb.append(terms[terms.length-1].co);
			sb.append('\n');
			System.out.println(sb.toString());
		}
	}

}
