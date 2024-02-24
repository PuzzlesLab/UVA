import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.StringTokenizer;

class Main {

	private static final char [] PRIORITY=new char [128];
	private static final HashMap<String,Character> MAP=new HashMap<>();
	private static final HashMap<Character,String> R_MAP=new HashMap<>();

	private static class Token {
		long n;
		char op;
		boolean isOp;
		
		public Token(long n) {
			this.n=n;
			this.isOp=false;
		}
		
		public Token(char op) {
			this.op=op;
			this.isOp=true;
		}
		
		public String toString() {
			return isOp ? op+"" : n+"";
		}
	}

	private static ArrayList<Token> tokenize(ArrayList<StringBuilder> list) {
		ArrayList<Token> tokens=new ArrayList<>();

		long lastN=0;
		for (int i=0;i<list.size();i++) {
			char c=MAP.get(list.get(i).toString());
			if (Character.isDigit(c)) lastN=lastN*10+(c-'0');
			else {
				tokens.add(new Token(lastN));
				lastN=0;
				tokens.add(new Token(c));
			}
		}
		tokens.add(new Token(lastN));

		ArrayList<Token> postfix=new ArrayList<>();
		Stack<Token> stk=new Stack<>();
		for (int i=0;i<tokens.size();i++) {
			Token t=tokens.get(i);
			if (t.isOp) {
				while (!stk.isEmpty() && PRIORITY[stk.peek().op]>=PRIORITY[t.op]) postfix.add(stk.pop());
				stk.push(t);
			} else postfix.add(t);
		}
		while (!stk.isEmpty()) postfix.add(stk.pop());

		return postfix;
	}

	private static String drawNum(long n) {
		String numS=String.valueOf(n);
		StringBuilder [] lines=new StringBuilder [5];
		for (int i=0;i<lines.length;i++) lines[i]=new StringBuilder();

		for (int i=0;i<numS.length();i++) {
			String digitS=R_MAP.get(numS.charAt(i));
			for (int i2=0;i2<lines.length;i2++) {
				lines[i2].append(digitS.substring(3*i2,3*(i2+1)));
				lines[i2].append(' ');
			}
		}
		
		StringBuilder result=new StringBuilder();
		for (int i=0;i<lines.length;i++) {
			lines[i].setLength(lines[i].length()-1); // Remove trailing space.
			result.append(lines[i]);
			result.append('\n');
		}
		return result.toString();
	}

	public static void main (String [] args) throws Exception {
		PRIORITY['+']=0;
		PRIORITY['-']=0;
		PRIORITY['*']=1;
		PRIORITY['/']=1;

		MAP.put("0000.00.00.0000", '0');
		MAP.put(".0..0..0..0..0.", '1');
		MAP.put("000..00000..000", '2');
		MAP.put("000..0000..0000", '3');
		MAP.put("0.00.0000..0..0", '4');
		MAP.put("0000..000..0000", '5');
		MAP.put("0..0..0000.0000", '6');
		MAP.put("000..0..0..0..0", '7');
		MAP.put("0000.00000.0000", '8');
		MAP.put("0000.0000..0..0", '9');
		MAP.put(".0..0.000.0..0.", '+');
		MAP.put("......000......", '-');
		MAP.put("0.00.0.0.0.00.0", '*');
		MAP.put(".0....000....0.", '/');
		for (Entry<String,Character> entry: MAP.entrySet()) R_MAP.put(entry.getValue(),entry.getKey());

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			ArrayList<StringBuilder> list=new ArrayList<>();
			for (int i=0;i<5;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				if (list.isEmpty()) {
					for (int i2=0;i2<st.countTokens();i2++) list.add(new StringBuilder());
				}
				int count=0;
				while (st.hasMoreTokens()) {
					list.get(count).append(st.nextToken());
					count++;
				}
			}

			ArrayList<Token> tokens=tokenize(list);
			if (tokens.size()==1 && tokens.get(0).n==0) break;

			Stack<Token> stk=new Stack<>();
			for (int i=0;i<tokens.size();i++) {
				Token t=tokens.get(i);
				if (!t.isOp) stk.push(t);
				else {
					Token t2=stk.pop();
					Token t1=stk.pop();
					if (t.op=='+') stk.push(new Token(t1.n+t2.n));
					else if (t.op=='-') stk.push(new Token(t1.n-t2.n));
					else if (t.op=='*') stk.push(new Token(t1.n*t2.n));
					else if (t.op=='/') stk.push(new Token(t1.n/t2.n));
				}
			}
			long ans=stk.pop().n;
			System.out.println(drawNum(ans));

			br.readLine(); // Empty.
		}
	}
}
