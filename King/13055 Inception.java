import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		String [] stk=new String [N];
		int stkLevel=0;
		
		StringBuilder sb=new StringBuilder();
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			String op=st.nextToken();
			if (op.equals("Sleep")) stk[stkLevel++]=st.nextToken();
			else if (op.equals("Test")) {
				if (stkLevel==0) sb.append("Not in a dream");
				else sb.append(stk[stkLevel-1]);
				sb.append('\n');
			} else if (op.equals("Kick")) if (stkLevel>0) stkLevel--;
		}
		
		System.out.print(sb.toString());
	}
}