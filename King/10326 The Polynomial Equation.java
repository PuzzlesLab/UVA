import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class Term implements Comparable<Term> {
		long co;
		int pow;
		
		public Term(long co, int pow) {
			this.co=co;
			this.pow=pow;
		}
		
		public Term multiply(Term t) {
			return new Term(this.co*t.co,this.pow+t.pow);
		}
		
		public int compareTo(Term t) {
			return t.pow-this.pow;
		}
	}

	private static class Equation {
		ArrayList<Term> terms;
		HashMap<Integer,Term> termMap;
		
		public Equation() {
			this.terms=new ArrayList<>();
			this.termMap=new HashMap<>();
		}

		public void add(Term t) {
			if (termMap.containsKey(t.pow)) termMap.get(t.pow).co += t.co;
			else {
				termMap.put(t.pow, new Term(t.co,t.pow));
				this.terms.add(termMap.get(t.pow));
			}
		}
		
		public String toString() {
			Collections.sort(this.terms);
			StringBuilder sb=new StringBuilder();
			boolean first=true;
			for (int i=0;i<this.terms.size();i++) {
				Term t=this.terms.get(i);
				if (t.co==0) {
					if (t.pow==0) sb.append(" + 0");
					continue;
				}
				
				if (first) {
					if (t.co<-1) sb.append(t.co);
					else if (t.co==-1) sb.append('-');
					else if (t.co>1) sb.append(t.co);
				} else {
					sb.append(' ');
					if (t.co<-1) {
						sb.append("- ");
						sb.append(-t.co);
					}
					else if (t.co==-1) {
						sb.append("- ");
						if (t.pow==0) sb.append('1');
					}
					else if (t.co==1) {
						sb.append("+ ");
						if (t.pow==0) sb.append('1');
					}
					else if (t.co>1) {
						sb.append("+ ");
						sb.append(t.co);
					}
				}
				
				if (t.pow>0) {
					sb.append('x');
					if (t.pow>1) {
						sb.append('^');
						sb.append(t.pow);
					}
				}

				first=false;
			}

			sb.append(" = 0");
			return sb.toString();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			long [] roots=new long [N];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) roots[n]=Long.parseLong(st.nextToken());
			
			Equation ans=new Equation();
			ans.add(new Term(1,1));
			ans.add(new Term(-roots[0],0));
			for (int n=1;n<N;n++) {
				Equation next=new Equation();
				Term t1=new Term(1,1);
				Term t2=new Term(-roots[n],0);
				for (int t=0;t<ans.terms.size();t++) {
					next.add(ans.terms.get(t).multiply(t1));
					next.add(ans.terms.get(t).multiply(t2));
				}
				ans=next;
			}
			
			System.out.println(ans);
		}
	}

}