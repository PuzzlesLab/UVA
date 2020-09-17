import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	
	public static class Statement {
		char lhs, operator, op1c, op2c;
		int operatorPriority;
		BigInteger op1v, op2v;
		Statement op1s, op2s;
		boolean op1Resolved, op2Resolved;
		
		public boolean resolved() {
			return this.op1Resolved && this.op2Resolved;
		}
		
		public String toString() {
			StringBuilder sb=new StringBuilder();
			if (this.op1s!=null) {
				if (this.op1s.operatorPriority<this.operatorPriority) sb.append('(');
				sb.append(this.op1s);
				if (this.op1s.operatorPriority<this.operatorPriority) sb.append(')');
			} else sb.append(this.op1v);
			
			sb.append(this.operator);
			
			if (this.op2s!=null) {
				if (this.op2s.operatorPriority<this.operatorPriority || this.op2s.operator == this.operator) sb.append('(');
				sb.append(this.op2s);
				if (this.op2s.operatorPriority<this.operatorPriority || this.op2s.operator == this.operator) sb.append(')');
			} else sb.append(this.op2v);
			
			return sb.toString();
		}
	}
	
	private static boolean isDigit(String s) {
		for (char c : s.toCharArray()) if (c<'0' || c>'9') return false;
		return true;
	}
	
	private static void resolve(Statement [] statements) {
		boolean [] filledFlag=new boolean [statements.length];
		for (int iter=0;iter<statements.length;iter++) {
			for (int i=0;i<statements.length;i++) if (statements[i].resolved() && !filledFlag[i]) {
				filledFlag[i]=true;
				Statement toFill=statements[i];
				for (int i2=0;i2<statements.length;i2++) if (i!=i2 && !statements[i2].resolved()) {
					Statement beFilled=statements[i2];
					if (!beFilled.op1Resolved && beFilled.op1c==toFill.lhs) {
						beFilled.op1s=toFill;
						beFilled.op1Resolved=true;
					}
					if (!beFilled.op2Resolved && beFilled.op2c==toFill.lhs) {
						beFilled.op2s=toFill;
						beFilled.op2Resolved=true;
					}
				}
			}
		}

	}
	
	public static void main (String [] args) throws Exception {
		int [] opPrio=new int [128];
		opPrio['+']=1;
		opPrio['+']=1;
		opPrio['*']=2;
		opPrio['/']=2;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int lines=Integer.parseInt(br.readLine());
			Statement [] map=new Statement[128];
			Statement [] statements=new Statement[lines];
			for (int line=0;line<lines;line++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Statement s=new Statement();
				s.lhs=st.nextToken().charAt(0);
				st.nextToken(); //equal
				
				String operand=st.nextToken();
				if (isDigit(operand)) {
					s.op1v=new BigInteger(operand);
					s.op1Resolved=true;
				}
				else s.op1c=operand.charAt(0);
				
				s.operator=st.nextToken().charAt(0);
				s.operatorPriority = opPrio[s.operator];
				
				operand=st.nextToken();
				if (isDigit(operand)) {
					s.op2v=new BigInteger(operand);
					s.op2Resolved=true;
				}
				else s.op2c=operand.charAt(0);
				
				statements[line]=s;
				map[s.lhs]=s;
			}
			
			resolve(statements);
			System.out.printf("Expression #%s: %s\n", testCase, statements[lines-1]);
		}
		
	}
}