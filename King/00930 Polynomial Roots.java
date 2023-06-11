import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Term {
		double co;
		int pow;
		
		public Term(double co, int pow) {
			this.co=co;
			this.pow=pow;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int K=Integer.parseInt(br.readLine());
		for (int k=0;k<K;k++) {
			int N=Integer.parseInt(br.readLine());
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			Term [] terms=new Term[N+1];
			for (int n=N;n>=0;n--) terms[n]=new Term(Double.parseDouble(st.nextToken()),n);
			
			st=new StringTokenizer(br.readLine());
			double [] roots=new double [N-2];
			for (int r=0;r<roots.length;r++) roots[r]=Double.parseDouble(st.nextToken());

			for (int r=0;r<roots.length;r++) {
				Term [] quotient=new Term [terms.length];
				for (int n=N;n>r;n--) {
					quotient[n]=new Term(terms[n].co,terms[n].pow-1);
					terms[n-1].co-=-terms[n].co*roots[r];
					terms[n].co=0;
				}
				terms=quotient;
			}

			double a=terms[N].co;
			double b=terms[N-1].co;
			double c=terms[N-2].co;

			double dis=Math.sqrt(b*b-4*a*c);
			double rootA=(-b+dis)/(2*a);
			double rootB=(-b-dis)/(2*a);
			System.out.printf("%.1f\n%.1f\n",rootA,rootB);
		}
	}

}
