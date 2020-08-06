import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			if (A!=B && A!=C && B==C) System.out.println('A');
			else if (B!=A && B!=C && A==C) System.out.println('B');
			else if (C!=A && C!=B && A==B) System.out.println('C');
			else System.out.println('*');
		}
	}

}