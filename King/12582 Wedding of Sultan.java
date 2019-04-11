import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Stack;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			char [] loc=br.readLine().toCharArray();
			HashSet<Character> [] set=new HashSet [128];
			
			Stack<Character> stk=new Stack<>();
			for (int i=0;i<loc.length;i++) {
				if (stk.isEmpty()) stk.push(loc[i]);
				else {
					char top=stk.peek();
					if (top == loc[i]) stk.pop();
					else {
						if (set[top]==null) set[top]=new HashSet<>();
						set[top].add(loc[i]);
						if (set[loc[i]]==null) set[loc[i]]=new HashSet<>();
						set[loc[i]].add(top);
						
						stk.push(loc[i]);
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append('\n');
			for (int i='A';i<='Z';i++) if (set[i]!=null) {
				sb.append((char)i);
				sb.append(" = ");
				sb.append(set[i].size());
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}
