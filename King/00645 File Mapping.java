import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

class Main {

	private static class Entry {
		String name;
		ArrayList<Entry> files;
		int level;
		boolean isDir;
		
		public Entry(String n, int l) {
			this.name=n;
			this.level=l;
			this.files=new ArrayList<>();
			this.isDir=this.name.charAt(0)=='d';
		}
	}

	private static void doIndent(StringBuilder sb, int width) {
		for (int i=0;i<width;i++) sb.append("|     ");
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean end=false;
		int testCase=1;
		while (true) {
			ArrayList<String> inputs=new ArrayList<>();
			while (true) {
				s=br.readLine();
				if (s.equals("*")) break;
				else if (s.equals("#")) {
					end=true;
					break;
				} else inputs.add(s);
			}
			if (end) break;
			inputs.add("]");
			
			StringBuilder sb=new StringBuilder();
			if (testCase>1) sb.append('\n');
			sb.append("DATA SET ");
			sb.append(testCase++);
			sb.append(":\n");

			Stack<Entry> stk=new Stack<>();
			stk.push(new Entry("ROOT",0));
			sb.append("ROOT\n");
			for (int i=0;i<inputs.size();i++) {
				String name=inputs.get(i);
				if (name.equals("]")) {
					Entry parent=stk.peek();
					Collections.sort(parent.files, (x,y) -> x.name.compareTo(y.name));
					for (int i2=0;i2<parent.files.size();i2++) {
						Entry e=parent.files.get(i2);
						doIndent(sb,e.level);
						sb.append(e.name);
						sb.append('\n');
					}
					stk.pop();
					continue;
				}

				Entry curr=new Entry(name,stk.peek().level);
				if (!curr.isDir) stk.peek().files.add(curr);
				else {
					curr.level++;
					stk.push(curr);
					doIndent(sb,curr.level);
					sb.append(curr.name);
					sb.append('\n');
				}
			}
			while (sb.length()>0 && sb.charAt(sb.length()-1)=='\n') sb.setLength(sb.length()-1);

			System.out.println(sb);
		}
	}
}
