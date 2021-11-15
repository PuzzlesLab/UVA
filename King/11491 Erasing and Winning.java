import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int D=Integer.parseInt(st.nextToken());
			int ansLength=N-D;
			
			Stack<Character> stk=new Stack<>();
			int tempD=D;
			for (char c: br.readLine().toCharArray()) {
				while (!stk.isEmpty() && tempD>0 && stk.peek()<c) {
					stk.pop();
					tempD--;
				}
				stk.push(c);
			}
			
			StringBuilder ans=new StringBuilder();
			while (!stk.isEmpty()) ans.append(stk.pop());
			System.out.println(ans.reverse().substring(0, ansLength));
		}
	}

}
