import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class Main {

	private static final int [] PRIORITY=new int [128];
	private static class Token {
		boolean isOp;
		char op;
		double num;
		
		public Token(char c) {
			this.isOp=true;
			this.op=c;
		}
		
		public Token(double n) {
			this.isOp=false;
			this.num=n;
		}
		
		public String toString() {
			return this.isOp ? ""+op : ""+num;
		}
	}

	private static ArrayList<Token> tokenize(String s) {
		ArrayList<Token> tokens=new ArrayList<>();
		int idx=0;
		while (idx<s.length()) {
			char c=s.charAt(idx);
			if (c=='+' || c=='*' || c=='/') {
				tokens.add(new Token(c));
				idx++;

				StringBuilder sb=new StringBuilder();
				if (s.charAt(idx)=='-') {
					sb.append(s.charAt(idx));
					idx++;
				}
				while (idx<s.length()) {
					c=s.charAt(idx);
					if (Character.isDigit(c) || c=='.') sb.append(c);
					else break;
					idx++;
				}
				tokens.add(new Token(Double.parseDouble(sb.toString())));
			} else if (c=='-') {
				if (tokens.size()>0) tokens.add(new Token('+'));

				boolean isNeg=false;
				while (idx<s.length() && s.charAt(idx)=='-') {
					isNeg=!isNeg;
					idx++;
				}
				StringBuilder sb=new StringBuilder();
				if (isNeg) sb.append(c);
				while (idx<s.length()) {
					c=s.charAt(idx);
					if (Character.isDigit(c) || c=='.') sb.append(c);
					else break;
					idx++;
				}
				tokens.add(new Token(Double.parseDouble(sb.toString())));
			} else {
				StringBuilder sb=new StringBuilder();
				while (idx<s.length()) {
					c=s.charAt(idx);
					if (Character.isDigit(c) || c=='.') sb.append(c);
					else break;
					idx++;
				}
				tokens.add(new Token(Double.parseDouble(sb.toString())));
			}
		}
		return tokens;
	}

	public static ArrayList<Token> toPostfix(ArrayList<Token> tokens) {
		ArrayList<Token> postfix=new ArrayList<>();
		Stack<Token> stk=new Stack<>();
		for (int i=0;i<tokens.size();i++) {
			Token t=tokens.get(i);
			if (!t.isOp) postfix.add(t);
			else {
				while (!stk.isEmpty() && PRIORITY[stk.peek().op]>=PRIORITY[t.op]) postfix.add(stk.pop());
				stk.add(t);
			}
		}
		while (!stk.isEmpty()) postfix.add(stk.pop());
		return postfix;
	}

	private static double evaluate(ArrayList<Token> postfix) {
		Stack<Token> stk=new Stack<>();
		for (int i=0;i<postfix.size();i++) {
			Token t=postfix.get(i);
			if (!t.isOp) stk.push(t);
			else {
				Token t2=stk.pop();
				Token t1=stk.pop();
				double result=0.0;
				switch (t.op) {
					case '+': {
						result=t1.num+t2.num;
						break;
					}
					case '-': {
						result=t1.num-t2.num;
						break;
					}
					case '*': {
						result=t1.num*t2.num;
						break;
					}
					case '/': {
						result=t1.num/t2.num;
						break;
					}
				}
				stk.push(new Token(result));
			}
		}
		return stk.pop().num;
	}

	public static void main (String [] args) throws Exception {
		PRIORITY['+']=1;
		PRIORITY['-']=1;
		PRIORITY['*']=2;
		PRIORITY['/']=2;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			ArrayList<Token> tokens=tokenize(s);
			ArrayList<Token> postfix=toPostfix(tokens);
			double ans=evaluate(postfix);
			System.out.printf("%.3f\n",ans);
		}
	}

}
