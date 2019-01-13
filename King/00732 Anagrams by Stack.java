import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

class Main {
	
	public static ArrayList<String> solutions=new ArrayList<>();
	
	private static String listToStr(LinkedList<Character> list) {
		StringBuilder sb=new StringBuilder();
		for (Character c : list) sb.append(c);
		return sb.toString();
	}
	
	public static void search(Stack<Character> source, String target, Stack<Character> temp, LinkedList<Character> output, char [] op, int depth) {
		if (depth==op.length && output.size()==target.length()) {
			if (listToStr(output).equals(target)) {
				StringBuilder sb=new StringBuilder();
				for (int i=0;i<op.length;i++) {
					sb.append(op[i]);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				solutions.add(sb.toString());
			}
		} else if (depth<op.length) {
			if (!source.isEmpty()) {
				temp.push(source.pop());
				op[depth]='i';
				search(source, target, temp, output, op, depth+1);
				op[depth]='a';
				source.push(temp.pop());
			}
			if (!temp.isEmpty()) {
				char c=temp.peek();
				output.addLast(c);
				if (target.startsWith(listToStr(output))) { //Branch cutting!
					temp.pop();
					op[depth]='o';
					search(source, target, temp, output, op, depth+1);
					op[depth]='a';
					temp.push(c);
				}
				output.removeLast();
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			solutions.clear();
			int [] charSetSrc=new int [128];
			int [] charSetTarg=new int [128];
			String target=br.readLine();
			
			Stack<Character> stk1=new Stack<>();
			for (char c : s.toCharArray()) {
				charSetSrc[c]++;
				stk1.add(c);
			}
			for (char c : target.toCharArray()) charSetTarg[c]++;
			boolean flag=true;
			for (int i=0;i<128 && flag;i++) flag=charSetSrc[i]==charSetTarg[i];
			
			if (flag) {
				Stack<Character> stk2=new Stack<>();
				while (!stk1.isEmpty()) stk2.push(stk1.pop());
				search(stk2, target, new Stack<>(), new LinkedList<>(), new char [target.length()*2], 0);
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("[\n");
			for (String sol : solutions) {
				sb.append(sol);
				sb.append('\n');
			}
			sb.append(']');
			System.out.println(sb.toString());
		}
	}

}