import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			String Is=st.nextToken();
			int I=0;
			if (Is.startsWith("0x")) I=Integer.parseInt(Is.substring(2),16);
			else if (Is.startsWith("0")) I=Integer.parseInt(Is,8);
			else I=Integer.parseInt(Is);
			int T=Integer.parseInt(st.nextToken());
			
			int [] output=new int [T+1];
			int [] expected=new int [T+1];
			for (int t=1;t<=T;t++) {
				st=new StringTokenizer(br.readLine());
				String op=st.nextToken();
				
				output[t]=Integer.parseInt(st.nextToken());
				
				if (op.equals("++i")) expected[t]=++I;
				else if (op.equals("i++")) expected[t]=I++;
				else if (op.equals("--i")) expected[t]=--I;
				else if (op.equals("i--")) expected[t]=I--;
				else if (op.equals("i")) expected[t]=I;
			}
			
			int mark=0;
			for (int i=1;i<=T;i++) if (output[i]-output[i-1]==expected[i]-expected[i-1]) mark++;
			System.out.println(mark);
		}
	}

}