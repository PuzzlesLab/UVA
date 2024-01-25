import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class Main {

	private static final int [] PRIORITY=new int [128];
	private static final boolean [] IS_SIGN=new boolean[128];
	private static final char LEFT_BRAC ='(';
	private static final char RIGHT_BRAC = ')';
	private static final char EQUAL='=';

	private static class Token {
		boolean isNum;
		boolean isVar;
		int num;
		char ch;
		
		public Token(int num) {
			this.num=num;
			this.isNum=true;
		}
		
		public Token(char c) {
			this.ch=c;
			this.isNum=false;
			this.isVar=c>='A'&&c<='Z';
		}
	}
	
	private static class Node {
		Token lT, rT;
		Node lN, rN;
		char op;
	}

	private static ArrayList<Token> tokenize(String s) {
		ArrayList<Token> tokens=new ArrayList<>();
		char [] ch=s.toCharArray();
		int pos=0;
		while (pos<ch.length) {
			char c=ch[pos];
			if (Character.isDigit(c) || c=='_') {
				int m=c=='_'?-1:1;
				int num=c=='_'?0:(ch[pos]-'0');
				pos++;

				while (pos<ch.length && Character.isDigit(ch[pos])) {
					num=num*10+(ch[pos]-'0');
					pos++;
				}
				num*=m;
				tokens.add(new Token(num));
			} else if (c>='A' && c<='Z' || IS_SIGN[c]) {
				tokens.add(new Token(c));
				pos++;
			} else pos++;
		}
		return tokens;
	}

	private static ArrayList<Token> convertPostfix(ArrayList<Token> tokens) {
		ArrayList<Token> postfix=new ArrayList<>();
		Stack<Token> stk=new Stack<>();
		for (int i=0;i<tokens.size();i++) {
			Token token=tokens.get(i);
			if (token.isNum || token.isVar) postfix.add(token);
			else if (token.ch==LEFT_BRAC) stk.push(token);
			else if (token.ch==RIGHT_BRAC) {
				while(stk.peek().ch!=LEFT_BRAC) postfix.add(stk.pop());
				stk.pop(); // Remove the pairing (
			} else {
				while (!stk.isEmpty()) {
					if (stk.peek().ch==EQUAL || PRIORITY[stk.peek().ch]<=PRIORITY[token.ch]) break;
					postfix.add(stk.pop());
				}
				stk.push(token);
			}
		}
		while (!stk.isEmpty()) postfix.add(stk.pop());
		return postfix;
	}

	private static int evaluateNode(Node root, int [] value) {
		if (root.lT==null) {
			if (root.rT.isNum) return root.rT.num;
			return value[root.rT.ch];
		}
		int rv=evaluateNode(root.rN, value);
		int lv=evaluateNode(root.lN, value);
		int result=0;
		if (root.op=='+') result=lv+rv;
		else if (root.op=='-') result=lv-rv;
		else if (root.op=='*') result=lv*rv;
		else if (root.op=='/') result=lv/rv;
		else if (root.op=='=') {
			result=rv;
			value[root.lT.ch]=rv;
		}
		return result;
	}

	private static int [] evaluate(ArrayList<Token> postfix, int [] value) {
		Stack<Token> tStk=new Stack<>();
		Stack<Node> nStk=new Stack<>();
		for (int i=0;i<postfix.size();i++) {
			Token t=postfix.get(i);
			if (t.isNum || t.isVar) {
				tStk.push(t);
				Node n=new Node();
				n.rT=t;
				nStk.push(n);
			} else {
				Node n=new Node();
				n.op=t.ch;
				n.rT=tStk.pop();
				n.lT=tStk.pop();
				n.rN=nStk.pop();
				n.lN=nStk.pop();

				tStk.push(t);
				nStk.push(n);
			}
		}
		int [] newValue=Arrays.copyOf(value,value.length);
		evaluateNode(nStk.pop(),newValue);
		return newValue;
	}

	public static void main (String [] args) throws Exception {
		PRIORITY['+']=1;
		PRIORITY['-']=1;
		PRIORITY['*']=2;
		PRIORITY['/']=2;
		PRIORITY['=']=3;
		IS_SIGN['+']=true;
		IS_SIGN['-']=true;
		IS_SIGN['*']=true;
		IS_SIGN['/']=true;
		IS_SIGN['=']=true;
		IS_SIGN['(']=true;
		IS_SIGN[')']=true;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int [] values=new int [128];
		while (!(s=br.readLine()).equals("#")) {
			ArrayList<Token> tokens=tokenize(s);
			ArrayList<Token> postfix=convertPostfix(tokens);
			int [] nValues=evaluate(postfix,values);
			
			StringBuilder sb=new StringBuilder();
			boolean flag=false;
			for (int i='A';i<='Z';i++) if (values[i]!=nValues[i]) {
				sb.append((char)(i));
				sb.append(" = ");
				sb.append(nValues[i]);
				sb.append(", ");
				flag=true;
			}
			if (flag) {
				values=nValues;
				sb.setLength(sb.length()-2);
			}
			else sb.append("No Change");

			System.out.println(sb);
		}
	}

}
