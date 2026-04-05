import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static int [] Values;
	private static int M;
	private static boolean Ans;
	private static Expression Expr;

	private static class Expression {
		private static boolean [] Operator;
		private static int [] Priority;
		private ArrayList<Character> postfix;
		private int [] operandPos;

		private static void init() {
			Expression.Operator=new boolean [128];
			Expression.Operator['+']=true;
			Expression.Operator['-']=true;
			Expression.Operator['*']=true;
			Expression.Operator['/']=true;
			
			Expression.Priority=new int [128];
			Priority['+']=1;
			Priority['-']=1;
			Priority['*']=2;
			Priority['/']=2;
		}

		public Expression(String s) {
			Stack<Character> stk=new Stack<>();

			postfix=new ArrayList<>();
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if (Character.isAlphabetic(c)) postfix.add(c);
				else if (c=='(') stk.push(c);
				else if (c==')') {
					while (!stk.isEmpty() && stk.peek()!='(') postfix.add(stk.pop());
					stk.pop();
				} else if (Expression.Operator[c]) {
					while (!stk.isEmpty() && stk.peek()!='(' && Priority[stk.peek()]>=Priority[c]) postfix.add(stk.pop());
					stk.push(c);
				}
			}

			while (!stk.isEmpty()) postfix.add(stk.pop());
			
			this.operandPos=new int [128];
			int operandCount=0;
			for (int i=0;i<postfix.size();i++) if (Character.isAlphabetic(postfix.get(i))) this.operandPos[postfix.get(i)]=operandCount++;
		}
		
		private int eval(int [] v) {
			Stack<Integer> stk=new Stack<>();
			for (int i=0;i<postfix.size();i++) {
				char c=postfix.get(i);
				if (Character.isAlphabetic(c)) stk.push(v[this.operandPos[c]]);
				else {
					int n2=stk.pop();
					int n1=stk.pop();
					if (c=='+') stk.push(n1+n2);
					else if (c=='-') stk.push(n1-n2);
					else if (c=='*') stk.push(n1*n2);
					else if (c=='/') stk.push(n1/n2);
				}
			}
			return stk.pop();
		}
	}

	private static void compute(int [] perm, int count, int mask) {
		if (Ans) return;
		if (mask==(1<<perm.length)-1) {
			Ans|=Expr.eval(perm)==M;
			return;
		}
		for (int i=0;i<Values.length;i++) if ((mask&(1<<i))==0) {
			perm[count]=Values[i];
			compute(perm,count+1,mask|(1<<i));
		}
	}
	
	public static void main(String[] args) throws Exception {
		Expression.init();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			Values=new int [N];
			for (int n=0;n<N;n++) Values[n]=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			Expr=new Expression(br.readLine());

			Ans=false;
			compute(new int [N],0,0);
			System.out.println(Ans?"YES":"NO");
		}
	}

}