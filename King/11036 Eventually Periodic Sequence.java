import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static class Component {
		long operand;
		char opcode;
		int type;
	}

	private static long calc(ArrayList<Component> equation, long x, long N) {
		Stack<Long> stk=new Stack<>();
		for (int i=0;i<equation.size();i++) {
			Component c=equation.get(i);
			if (c.type==0) stk.push(x);
			else if (c.type==1) {
				long v1=stk.pop();
				long v2=stk.pop();
				long result=0;
				if (c.opcode=='+') result=v1+v2;
				else if (c.opcode=='*') result=v1*v2;
				else if (c.opcode=='%') result=v2%v1;
				stk.push(result%N);
			} else if (c.type==2) stk.push(N);
			else if (c.type==3) stk.push(c.operand);
		}
		return stk.pop();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s=br.readLine();
			StringTokenizer st=new StringTokenizer(s);
			long N=Long.parseLong(st.nextToken());
			if (N==0) break;
			
			long x=Long.parseLong(st.nextToken());
			ArrayList<Component> equation=new ArrayList<>();
			while (st.hasMoreTokens()) {
				s=st.nextToken();
				char ch=s.charAt(0);
				Component c=new Component();
				if (ch=='x') c.type=0;
				else if (ch=='+' || ch=='*' || ch=='%') {
					c.type=1;
					c.opcode=ch;
				} else if (ch=='N') c.type=2;
				else {
					c.type=3;
					c.operand=Long.parseLong(s);
				}
				equation.add(c);
			}

			HashMap<Long,Integer> pos=new HashMap<>();
			int cPos=1;
			int ans=-1;
			while (true) {
				x=calc(equation,x,N);
				if (pos.containsKey(x)) {
					ans=cPos-pos.get(x);
					break;
				}
				pos.put(x,cPos++);
			}
			System.out.println(ans);
		}
	}

}
