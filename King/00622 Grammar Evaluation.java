import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class Main {

	private static class Token {
		boolean isOp;
		char op;
		long num;
		
		public Token(char op) {
			this.isOp=true;
			this.op=op;
		}
		
		public Token(long n) {
			this.isOp=false;
			this.num=n;
		}
		
		public String toString() {
			if (this.isOp) return ""+this.op;
			return ""+this.num;
		}
	}

	public static void main (String [] args) throws Exception {
		boolean [] VALID_OP=new boolean [128];
		VALID_OP['+']=true;
		VALID_OP['*']=true;
		VALID_OP['(']=true;
		VALID_OP[')']=true;
		int [] PRIORITY=new int [128];
		PRIORITY['+']=1;
		PRIORITY['*']=2;
		final String ERROR="ERROR";

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			String s=br.readLine();
			
			// Tokenize input
			ArrayList<Token> tokens=new ArrayList<>();
			int idx=0;
			boolean valid=true;
			while (idx<s.length()) {
				char c=s.charAt(idx);
				if (Character.isDigit(c)) {
					long num=c-'0';
					idx++;
					while (idx<s.length() && Character.isDigit(s.charAt(idx))) {
						num=num*10+(s.charAt(idx)-'0');
						idx++;
					}
					tokens.add(new Token(num));
				} else {
					valid&=VALID_OP[c];
					tokens.add(new Token(c));
					idx++;
				}
			}

			if (!valid) {
				System.out.println(ERROR);
				continue;
			}
			
			// Attempt convert to postfix + check
			ArrayList<Token> postfix=new ArrayList<>();
			Stack<Token> stk=new Stack<>();
			for (int i=0;i<tokens.size();i++) {
				Token t=tokens.get(i);
				if (!t.isOp) postfix.add(t);
				else if (t.op=='(') stk.push(t);
				else if (t.op==')') {
					while (!stk.isEmpty() && stk.peek().op!='(') postfix.add(stk.pop());
					if (stk.isEmpty()) {
						valid=false;
						break;
					}
					stk.pop();
				} else {
					while (!stk.isEmpty() && PRIORITY[stk.peek().op]>PRIORITY[t.op]) postfix.add(stk.pop());
					stk.push(t);
				}
			}
			while (!stk.isEmpty()) postfix.add(stk.pop());
			if (!valid) {
				System.out.println(ERROR);
				continue;
			}
			
			int numCount=0;
			int opCount=0;
			for (int i=0;i<postfix.size();i++) {
				if (postfix.get(i).isOp) opCount++;
				else numCount++;
			}
			if (numCount!=opCount+1) {
				System.out.println(ERROR);
				continue;
			}

			// Compute
			stk.clear();
			for (int i=0;i<postfix.size();i++) {
				Token t=postfix.get(i);
				if (!t.isOp) stk.push(t);
				else {
					Token t1=stk.pop();
					Token t2=stk.pop();
					Token t3=new Token(t.op=='+'?t1.num+t2.num:t1.num*t2.num);
					stk.push(t3);
				}
			}
			System.out.println(stk.pop().num);
		}
	}

}
