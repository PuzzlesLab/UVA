import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("#")) {
			String [] cards=new String [52];
			StringTokenizer st=new StringTokenizer(s);
			for (int i=0;i<26;i++) cards[i]=st.nextToken();
			st=new StringTokenizer(br.readLine());
			for (int i=26;i<cards.length;i++) cards[i]=st.nextToken();
			
			LinkedList<Stack<String>> stk=new LinkedList<>();
			for (int i=0;i<cards.length;i++) {
				stk.add(new Stack<>());
				stk.getLast().add(cards[i]);
			}
			
			for (int i=1;i<stk.size();i++) {
				String curr=stk.get(i).peek();
				if (i>=3) {
					String left=stk.get(i-3).peek();
					if (left.charAt(0) == curr.charAt(0) || left.charAt(1) == curr.charAt(1)) {
						stk.get(i-3).push(stk.get(i).pop());
						if (stk.get(i).isEmpty()) stk.remove(i);
						i-=4;
						continue;
					}
					
				}
				if (i>=1) {
					String left=stk.get(i-1).peek();
					if (left.charAt(0) == curr.charAt(0) || left.charAt(1) == curr.charAt(1)) {
						stk.get(i-1).push(stk.get(i).pop());
						if (stk.get(i).isEmpty()) stk.remove(i);
						i-=2;
						continue;
					}
				}
			}
			

			StringBuilder sb=new StringBuilder();
			sb.append(stk.size());
			sb.append(" pile");
			if (stk.size()>1) sb.append('s');
			sb.append(" remaining:");
			for (int i=0;i<stk.size();i++) {
				sb.append(' ');
				sb.append(stk.get(i).size());
			}
			
			System.out.println(sb.toString());
		}
	}

}
