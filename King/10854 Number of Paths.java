import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class zz {

	private static final String END_PROG="ENDPROGRAM";
	private static final String IF="IF";
	private static final String ELSE="ELSE";
	private static final String END_IF="END_IF";
	
	private static class Block {
		ArrayList<Block> children;
		boolean isIf=true;

		public Block(boolean flag) {
			this.children=new ArrayList<>();
			this.isIf=flag;
		}
	}
	
	private static long traverse(Block root) {
		if (root.children.size()==0) return 1;
		long sum=1;
		for (int i=0;i<root.children.size();i++) {
			Block b=root.children.get(i);
			if (!b.isIf) continue;

			long curr=0;
			long temp=sum;
			curr+=temp*traverse(root.children.get(i));
			if (i+1<root.children.size() && !root.children.get(i+1).isIf) {
				curr+=temp*traverse(root.children.get(i+1));
				i++;
			}
			sum=curr;
		}
		return sum;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			Stack<Block> stk=new Stack<>();
			stk.push(new Block(true));
			while (true) {
				String s=br.readLine();
				if (s.equals(END_PROG)) break;

				switch (s) {
					case IF: {
						Block b=new Block(true);
						stk.peek().children.add(b);
						stk.push(b);
						break;
					}
					case ELSE: {
						stk.pop(); // Pop the IF block.
						Block b=new Block(false);
						stk.peek().children.add(b);
						stk.push(b);
						break;
					}
					case END_IF: {
						stk.pop();
						break;
					}
				}
			}

			Block root=stk.pop();
			System.out.println(traverse(root));
		}
	}

}
