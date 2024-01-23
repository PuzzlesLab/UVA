import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {

	public static void main (String [] args) throws Exception {
		final char SLUMP_1='!';
		final char SLUMP_2='#';
		final char SLUMP_3='@';
		final char SLIMP_1='.';
		final char SLIMP_2='=';
		final char SLIMP_3='/';
		final char SLIMP_4=';';
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		System.out.println("SLURPYS OUTPUT");
		for (int n=0;n<N;n++) {
			String s=br.readLine();
			Stack<Character> stk=new Stack<>();
			
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);

				if (c=='D' || c=='E') {
					stk.push(SLUMP_1);
					continue;
				} else if (c=='A') {
					stk.push(SLIMP_1);
					continue;
				}
				if (stk.isEmpty()) continue;

				if (stk.peek()==SLUMP_1) {
					if (c=='F') {
						stk.pop();
						stk.push(SLUMP_2);
					} else stk.pop();
				} else if (stk.peek()==SLUMP_2) {
					if (c=='D' || c=='E') stk.push(SLUMP_1);
					else if (c=='G') {
						stk.pop();
						while (!stk.isEmpty() && stk.peek()==SLUMP_2) stk.pop();
						stk.push(SLUMP_3);
					} else if (c!='F') stk.pop();
				} else if (stk.peek()==SLUMP_3) {
					stk.pop();
					if (c=='C' && !stk.isEmpty() && stk.peek()==SLIMP_1) {
						stk.pop();
						stk.push(SLIMP_4);
					}
				} else if (stk.peek()==SLIMP_1) {
					stk.pop();
					if (c=='H') stk.push(SLIMP_4);
					else if (c=='B') stk.push(SLIMP_2);
				} else if (stk.peek()==SLIMP_2) {
					stk.pop();
					if (c=='C') {
						if (!stk.isEmpty() && stk.peek()==SLUMP_3) stk.pop();
						stk.push(SLIMP_3);
					}
				} else if (stk.peek()==SLIMP_3 || stk.peek()==SLIMP_4) {
					stk.pop();
					if (c=='C') {
						if (!stk.isEmpty() && stk.peek()==SLIMP_2) stk.pop();
						stk.push(SLIMP_4);
					}
				}
			}
			if (stk.size()!=2) System.out.println("NO");
			else {
				char second=stk.pop();
				char first=stk.pop();
				System.out.println(first==SLIMP_4 && second==SLUMP_3 ? "YES" : "NO");
			}
		}
		System.out.println("END OF OUTPUT");
	}

}