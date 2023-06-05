import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Term {
		long co;
		int pow;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int x=Integer.parseInt(s);
			StringTokenizer st=new StringTokenizer(br.readLine());
			Term [] terms=new Term[st.countTokens()];
			for (int i=0;i<terms.length;i++) {
				terms[i]=new Term();
				terms[i].co=Long.parseLong(st.nextToken());
				terms[i].pow=terms.length-i-1;
				
				//Do differentiation
				terms[i].co*=terms[i].pow;
				terms[i].pow--;
			}
			
			long [] pow=new long [terms.length];
			pow[0]=1;
			for (int p=1;p<terms.length;p++) pow[p]=x*pow[p-1];

			// Evaluate
			long ans=0;
			for (int i=0;i<terms.length-1;i++) ans+=terms[i].co*pow[terms[i].pow];

			System.out.println(ans);
		}
	}

}
