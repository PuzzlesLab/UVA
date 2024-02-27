import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb=new StringBuilder();
		while ((s=br.readLine())!=null) {
			if (s.isEmpty()) break;
			
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if (Character.isAlphabetic(c) || c==' ') sb.append(c);
				else if (c=='-' && i>0 && Character.isAlphabetic(s.charAt(i-1)) && i==s.length()-1) sb.append(c);
			}
			sb.append('\n');
		}
		sb.setLength(sb.length()-1);
		
		StringBuilder ans=new StringBuilder();
		boolean addLB=false;
		for (int i=0;i<sb.length();i++) {
			char c=sb.charAt(i);
			if (Character.isAlphabetic(c) || c==' ' || c=='\n') {
				if (!Character.isAlphabetic(c) && addLB) {
					ans.append('\n');
					addLB=false;
				}
				ans.append(c);
			} else if (c=='-' && sb.charAt(i+1)=='\n') {
				Stack<Character> stk=new Stack<>();
				while (ans.charAt(ans.length()-1)!=' ') {
					stk.push(ans.charAt(ans.length()-1));
					ans.setLength(ans.length()-1);
				}
				ans.append('\n');
				while (!stk.isEmpty()) ans.append(stk.pop());
				i++;
				addLB=true;
			}
		}
		
		System.out.print(ans);
	}
}