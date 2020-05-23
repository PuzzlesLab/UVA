import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class Main {

	private static long evaluate(ArrayList<String> eq) {
		int [] level=new int [128]; level['*']=1;
		Stack<Character> operator=new Stack<>();
		Stack<Long> operand=new Stack<>();
		for (String s : eq) {
			char c=s.charAt(0);
			if (c>='0' && c<='9') operand.push(Long.parseLong(s));
			else if (c=='(') operator.push(c);
			else if (c==')') {
				while (!operator.isEmpty() && operator.peek()!='(') {
					char op=operator.pop();
					long opd=operand.pop(), opd2=operand.pop();
					operand.push(op=='+' ? opd+opd2 : opd*opd2);
				}
				if (!operator.isEmpty()) operator.pop();
			} else {
				while (!operator.isEmpty() && operator.peek() != '(' && level[operator.peek()]>=level[c]) {
					char op=operator.pop();
					long opd=operand.pop(), opd2=operand.pop();
					operand.push(op=='+' ? opd+opd2 : opd*opd2);
				}
				operator.push(c);
			}
		}
		while (!operator.isEmpty()) {
			char op=operator.pop();
			long opd=operand.pop(), opd2=operand.pop();
			operand.push(op=='+' ? opd+opd2 : opd*opd2);
		}
		
		return operand.pop();
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			ArrayList<String> minEq=new ArrayList<>();
			StringBuilder sb=new StringBuilder();
			for (char c : br.readLine().toCharArray()) {
				if (c>='0' && c<='9') sb.append(c);
				else {
					minEq.add(sb.toString());
					minEq.add(String.valueOf(c));
					sb.setLength(0);
				}
			}
			if (sb.length()>0) minEq.add(sb.toString());
			
			ArrayList<String> maxEq=new ArrayList<>();
			maxEq.addAll(minEq);
			for (int i=0;i<maxEq.size();i++) {
				if (maxEq.get(i).equals("+")) {
					boolean put=false;
					for (int i2=i-1;i2>=0;i2--) if (maxEq.get(i2).equals("*")) {
						maxEq.add(i2+1,"(");
						put=true;
						break;
					}
					if (!put) maxEq.add(0,"(");
					
					put=false;
					for (int i2=i+1;i2<maxEq.size();i2++) if (maxEq.get(i2).equals("*")) {
						maxEq.add(i2,")");
						put=true;
						i=i2+1;
						break;
					}
					if (!put) {
						maxEq.add(")");
						i=maxEq.size();
					}
				}
			}
			
			System.out.printf("The maximum and minimum are %d and %d.\n", evaluate(maxEq), evaluate(minEq));
		}
	}

}