import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class zz {

	private static final int BEGIN=0;
	private static final int LOOP=1;
	private static final int OP=2;
	private static final int END=3;

	private static class Line {
		int op;
		boolean isArgI;
		String argS;
		int argI;
		
		public Line(int op) {
			this.op=op;
		}

		public Line(int op, String s) {
			this.op=op;
			this.argS=s;
			this.isArgI=false;
		}

		public Line(int op, int i) {
			this.op=op;
			this.argI=i;
			this.isArgI=true;
		}

		public String toString() {
			if (this.op==BEGIN) return "BEGIN";
			else if (this.op==END) return "END";
			else if (this.op==LOOP) return "LOOP "+(isArgI?argI:argS);
			return "OP "+(isArgI?argI:argS);
		}
	}
	
	private static class Term {
		int n, pow;
		
		public Term(int n, int p) {
			this.n=n;
			this.pow=p;
		}
		
		public String toString() {
			return n+"n^"+pow;
		}
	}

	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int K=Integer.parseInt(sc.next());
		for (int k=1;k<=K;k++) {
			ArrayList<Line> lines=new ArrayList<>();
			int level=0;
			while (true) {
				String s=sc.next();
				if (s.equals("BEGIN")) {
					level++;
					lines.add(new Line(BEGIN));
				} else if (s.equals("LOOP")) {
					level++;
					String s2=sc.next();
					if (s2.equals("n")) lines.add(new Line(LOOP,s2));
					else lines.add(new Line(LOOP,Integer.parseInt(s2)));
				} else if (s.equals("OP")) {
					lines.add(new Line(OP,Integer.parseInt(sc.next())));
				} else if (s.equals("END")) {
					lines.add(new Line(END));
					level--;
				}

				if (level==0) break;
			}

			Stack<Term> stk=new Stack<>();
			ArrayList<Term> ans=new ArrayList<>();
			for (int i=0;i<lines.size()-1;i++) {
				Line line=lines.get(i);
				if (line.op==BEGIN) {
					stk.push(new Term(1,0));
					continue;
				} else if (line.op==LOOP) {
					Term parent=stk.peek();
					if (line.isArgI) stk.push(new Term(parent.n*line.argI,stk.peek().pow));
					else stk.push(new Term(parent.n,stk.peek().pow+1));
				} else  if (line.op==OP) {  // OP
					Term parent=stk.peek();
					//System.out.println(line+" >> "+parent);
					Term curr=new Term(parent.n,parent.pow);
					curr.n*=line.argI;
					ans.add(curr);
				} else { // END
					stk.pop();
				}
			}
			int maxPow=0;
			for (int i=0;i<ans.size();i++) maxPow=Math.max(maxPow,ans.get(i).pow);
			int [] coef=new int [maxPow+1];
			for (int i=0;i<ans.size();i++) {
				Term t=ans.get(i);
				coef[t.pow]+=t.n;
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Program #");
			sb.append(k);
			sb.append("\nRuntime = ");
			boolean hasTerm=false;
			for (int i=maxPow;i>=0;i--) {
				if (coef[i]==0) continue;
				hasTerm=true;
				boolean includeMultiply=false;
				if (coef[i]!=1 || (coef[i]==1 && i==0)) {
					sb.append(coef[i]);
					includeMultiply=true;
				}
				if (i>0) {
					if (includeMultiply) sb.append('*');
					sb.append('n');
					if (i>1) {
						sb.append('^');
						sb.append(i);
					}
				}
				sb.append('+');
			}
			if (hasTerm) sb.setLength(sb.length()-1);
			else sb.append('0');
			sb.append('\n');
			System.out.println(sb);
		}
	}

}
