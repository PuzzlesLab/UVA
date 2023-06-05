import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Main {

	private static class Term implements Comparable<Term> {
		int co, xp, yp;
		String key;
		
		private static int safeParse(String s) {
			if (s.equals("-")) return -1;
			if (s.length()==0) return 1;
			return Integer.parseInt(s);
		}

		private Term(int co, int xp, int yp) {
			this.co=co;
			this.xp=xp;
			this.yp=yp;
			this.setKey();
		}

		public Term(Term t) {
			this.co=t.co;
			this.xp=t.xp;
			this.yp=t.yp;
			this.key=t.key;
		}

		public Term(String s) {
			int xI=s.indexOf('x');
			int yI=s.indexOf('y');
			
			int i1=-1;
			if (xI!=-1 && yI==-1) i1=xI;
			else if (xI==-1 && yI!=-1) i1=yI;
			else if (xI!=-1 && yI!=-1) i1=Math.min(xI,yI);

			this.co=safeParse((i1==-1) ? s : s.substring(0,i1));
			if (xI!=-1) {
				if (yI==-1) this.xp=safeParse(s.substring(xI+1));
				else this.xp=safeParse(xI<yI ? s.substring(xI+1,yI) : s.substring(xI+1));
			}
			if (yI!=-1) {
				if (xI==-1) this.yp=safeParse(s.substring(yI+1));
				else this.yp=safeParse(yI<xI ? s.substring(yI+1,xI) : s.substring(yI+1));
			}

			this.setKey();
		}

		public Term multiply(Term t) {
			return new Term(this.co*t.co,this.xp+t.xp,this.yp+t.yp);
		}

		private void setKey() {
			StringBuilder sb=new StringBuilder();
			sb.append(this.xp);
			sb.append(';');
			sb.append(this.yp);

			this.key=sb.toString();
		}
		
		public int compareTo(Term t) {
			if (this.xp!=t.xp) return t.xp-this.xp;
			return this.yp-t.yp;
		}
	}

	private static class Equation {
		ArrayList<Term> terms;
		HashMap<String,Term> termMap;

		public Equation() {
			this.terms=new ArrayList<>();
			this.termMap=new HashMap<>();
		}

		public Equation(String s) {
			this.terms=new ArrayList<>();
			this.termMap=new HashMap<>();

			StringBuilder curr=new StringBuilder();
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if ((c=='+' || c=='-') && curr.length()>0) {
					this.add(new Term(curr.toString()));
					curr.setLength(0);
					if (c=='-') curr.append(c);
				} else curr.append(c);
			}
			if (curr.length()>0) this.add(new Term(curr.toString()));
		}
		
		public void add(Term term) {
			String key=term.key;
			if (this.termMap.containsKey(key)) this.termMap.get(key).co += term.co;
			else {
				Term t=new Term(term);
				this.termMap.put(key,t);
				this.terms.add(t);
			}
		}

		public String toString() { // Annoying output
			StringBuilder line1=new StringBuilder();
			StringBuilder line2=new StringBuilder();
			boolean firstTerm=true;
			for (int i=0;i<terms.size();i++) {
				Term t=terms.get(i);
				if (t.co==0) continue;

				if (!firstTerm) line1.append(' ');
				if (t.co==-1) {
					line1.append("-");
					if (!firstTerm) line1.append(' ');
					if (t.xp==0 && t.yp==0) line1.append(1);
				}
				else if (t.co<-1) {
					if (firstTerm) line1.append('-');
					else line1.append("- ");
					line1.append(-t.co);
				}
				else if (t.co==1) {
					if (t.xp==0 && t.yp==0) line1.append(1);
					else if (!firstTerm) line1.append("+ ");
				}
				else {
					if (!firstTerm) line1.append("+ ");
					line1.append(t.co);
				}
				while (line2.length()<line1.length()) line2.append(' ');

				if (t.xp>0) {
					line1.append('x');
					while (line2.length()<line1.length()) line2.append(' ');
					if (t.xp>1) {
						line2.append(t.xp);
						while (line1.length()<line2.length()) line1.append(' ');
					}

				}
				if (t.yp>0) {
					line1.append('y');
					while (line2.length()<line1.length()) line2.append(' ');
					if (t.yp>1) {
						line2.append(t.yp);
						while (line1.length()<line2.length()) line1.append(' ');
					}
				}
				firstTerm=false;
			}

			while (line1.length() > 0 && line2.length() > 0 && line1.charAt(line1.length()-1)==' ' && line2.charAt(line2.length()-1)==' ') {
				line1.setLength(line1.length()-1);
				line2.setLength(line2.length()-1);
			}

			StringBuilder sb=new StringBuilder();
			if (line1.length() > 0) {
				sb.append(line2);
				sb.append('\n');
				sb.append(line1);
			} else {
				sb.append("\n\n0");
			}
			return sb.toString();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			if (s.equals("#")) break;

			Equation eq1=new Equation(s);
			Equation eq2=new Equation(br.readLine());
			Equation ans=new Equation();
			
			for (int i=0;i<eq1.terms.size();i++) {
				for (int i2=0;i2<eq2.terms.size();i2++) {
					ans.add(eq1.terms.get(i).multiply(eq2.terms.get(i2)));
				}
			}
			Collections.sort(ans.terms);

			System.out.println(ans);
		}
	}

}
