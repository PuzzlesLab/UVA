import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class Main {
	
	public static void main (String [] args) throws Exception {
		int [] level=new int [128];
		level['+']=1;
		level['-']=1;
		level['*']=2;
		level['/']=2;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			ArrayList<Character> ch=new ArrayList<>();
			String s;
			while ((s=br.readLine())!=null) {
				if (s.trim().length()==0) break;
				ch.add(s.charAt(0));
			}
			
			Stack<Character> opcode=new Stack<>();
			StringBuilder sb=new StringBuilder();
			for (char c : ch) {
				if (c>='0' && c<='9') sb.append(c);
				else if (c=='(') opcode.push(c);
				else if (c==')') {
					while (!opcode.isEmpty() && opcode.peek()!='(') sb.append(opcode.pop());
					opcode.pop();
				} else {
					while (!opcode.isEmpty() && level[c]<=level[opcode.peek()] ) sb.append(opcode.pop());
					opcode.push(c);
				}
			}
			
			while (!opcode.isEmpty()) sb.append(opcode.pop());

			if (testCase>0) System.out.println();
			System.out.println(sb.toString());
		}
	}

}