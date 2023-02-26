import java.util.Scanner;
import java.util.Stack;

class Main {

	private static class Node {
		int value;
		boolean last;
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (sc.hasNext()) {
			int N=sc.nextInt();
			StringBuilder sb=new StringBuilder();
			int level=0;
			while (sc.hasNext()) {
				String s=sc.next();
				sb.append(s);
				for (int i=0;i<s.length();i++) {
					char c=s.charAt(i);
					if (c=='(') level++;
					else if (c==')') level--;
				}
				if (level==0) break;
			}

			boolean flag=false;
			Stack<Node> stk=new Stack<>();
			for (int i=0;i<sb.length();i++) {
				if (sb.charAt(i)=='(') {
					if (i+3<sb.length() && sb.charAt(i+1)==')' && sb.charAt(i+2)=='(' && sb.charAt(i+3)==')') {
						Node n=new Node();
						n.value=!stk.isEmpty() ? stk.peek().value : 0;
						n.last=true;
						stk.push(n);
					} else {
						Node n=new Node();
						n.last=false;
						int temp=i+1;
						boolean neg=false;
						if (sb.charAt(temp)=='-') {
							neg=true;
							temp++;
						}
						while (sb.charAt(temp)>='0' && sb.charAt(temp)<='9') {
							n.value=n.value*10+(sb.charAt(temp)-'0');
							temp++;
						}
						i=temp-1;
						if (neg) n.value*=-1;
						if (!stk.isEmpty()) n.value+=stk.peek().value;
						stk.push(n);
					}
				} else if (sb.charAt(i)==')') {
					if (stk.peek().last && stk.peek().value==N) {
						flag=true;
						break;
					}
					stk.pop();
				}
			}
			
			System.out.println(flag?"yes":"no");
		}
	}

}
