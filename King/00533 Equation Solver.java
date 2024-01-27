import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class Main {

	private static final boolean [] IS_OP=new boolean [128];
	private static final int [] PRIORITY=new int [128];

	private static class Token {
		boolean isOp;
		char op;
		int co0, co1;
		
		public Token(char op) {
			this.op=op;
			this.isOp=true;
		}
		
		public Token(int co0, int co1) {
			this.co0=co0;
			this.co1=co1;
			this.isOp=false;
		}
	}

	private static ArrayList<Token> tokenize(String s) {
		ArrayList<Token> tokens=new ArrayList<>();
		int i=0;
		boolean neg=false;
		while (i<s.length()) {
			char c=s.charAt(i);
			if (IS_OP[s.charAt(i)]) {
				if (c=='-') { // Handles negative number.
					neg=c=='-';
					c='+';
				}
				if (!tokens.isEmpty() || (c!='+' && c!='-')) tokens.add(new Token(c));
				i++;
			} else if (Character.isDigit(c)) {
				int num=c-'0';
				i++;
				while (i<s.length()&&Character.isDigit(s.charAt(i))) {
					num=num*10+(s.charAt(i)-'0');
					i++;
				}
				if (neg) {
					num=-num;
					neg=false;
				}
				boolean isX=false;
				if (i<s.length()&&s.charAt(i)=='x') {
					isX=true;
					i++;
				}
				tokens.add(new Token(isX?0:num,isX?num:0));
			} else if (c=='x') {
				tokens.add(new Token(0,neg?-1:1));
				i++;
				neg=false;
			}
		}
		return tokens;
	}

	private static ArrayList<Token> toPostfix(ArrayList<Token> tokens) {
		ArrayList<Token> postfix=new ArrayList<>();
		Stack<Token> stk=new Stack<>();
		for (int i=0;i<tokens.size();i++) {
			Token t=tokens.get(i);
			if (!t.isOp) postfix.add(t);
			else if (t.op=='(') stk.add(t);
			else if (t.op==')') {
				while (stk.peek().op!='(') postfix.add(stk.pop());
				stk.pop();
			} else {
				while (!stk.isEmpty() && PRIORITY[stk.peek().op]>PRIORITY[t.op]) postfix.add(stk.pop());
				stk.push(t);
			}
		}
		while (!stk.isEmpty()) postfix.add(stk.pop());
		return postfix;
	}

	private static Token doOp(Token left, Token right, char op) {
		Token t=null;
		if (op=='+') t=new Token(left.co0+right.co0,left.co1+right.co1);
		else if (op=='-') t=new Token(left.co0-right.co0,left.co1-right.co1);
		else if (op=='*') t=new Token(left.co0*right.co0,left.co0*right.co1+left.co1*right.co0);
		return t;
	}

	private static Token evaluate(ArrayList<Token> postfix) {
		Stack<Token> stk=new Stack<>();
		for (int i=0;i<postfix.size();i++) {
			Token t=postfix.get(i);
			if (!t.isOp) stk.push(t);
			else {
				stk.push(doOp(stk.pop(),stk.pop(),t.op));
			}
		}
		return stk.pop();
	}

	public static void main (String [] args) throws Exception {
		PRIORITY['+']=1;
		PRIORITY['-']=1;
		PRIORITY['*']=2;
		IS_OP['+']=true;
		IS_OP['-']=true;
		IS_OP['*']=true;
		IS_OP['(']=true;
		IS_OP[')']=true;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int TC=1;
		while ((s=br.readLine())!=null) {
			int eq=s.indexOf('=');
			ArrayList<Token> lTokens=tokenize(s.substring(0,eq));
			ArrayList<Token> rTokens=tokenize(s.substring(eq+1,s.length()));
			ArrayList<Token> lTokens2=toPostfix(lTokens);
			ArrayList<Token> rTokens2=toPostfix(rTokens);
			
			Token l=evaluate(lTokens2);
			Token r=evaluate(rTokens2);
			Token e=doOp(l,r,'-');
			e.co0=-e.co0;

			StringBuilder sb=new StringBuilder();
			if (TC>1) sb.append('\n');
			sb.append("Equation #");
			sb.append(TC++);
			sb.append('\n');
			if (e.co1!=0) {
				double d=e.co0;
				sb.append("x = ");
				sb.append(String.format("%.6f", e.co0==0?0.0:d/e.co1));
			} else if (e.co0==0 && e.co1==0) sb.append("Infinitely many solutions.");
			else sb.append("No solution.");
			System.out.println(sb.toString());
		}
	}

}
