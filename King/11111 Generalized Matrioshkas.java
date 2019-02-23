import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	public static class Doll {
		int size, emptySpace;
		
		public Doll(int s) {
			this.size=s;
			this.emptySpace=s;
		}
		
		
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			Stack<Doll> stk=new Stack<>();
			int dollCount=0;
			while (st.hasMoreTokens()) {
				int n=Integer.parseInt(st.nextToken());
				dollCount++;
				if (n<0) {
					Doll d=new Doll(-n);
					if (stk.empty()) stk.add(d);
					else if (stk.peek().emptySpace>d.size) {
						stk.peek().emptySpace-=d.size;
						stk.add(d);
					} else break;
				} else {
					if (!stk.isEmpty() && stk.peek().size==n) stk.pop();
					else break;
				}
			}

			if (stk.size()==0 && dollCount%2==0) System.out.println(":-) Matrioshka!");
			else System.out.println(":-( Try again.");
		}
	}

}
