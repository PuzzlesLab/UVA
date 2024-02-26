import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("*")) {
			StringTokenizer st=new StringTokenizer(s);
			char c=Character.toLowerCase(st.nextToken().charAt(0));
			
			boolean flag=true;
			while (st.hasMoreTokens() && flag) flag&=Character.toLowerCase(st.nextToken().charAt(0))==c;
			System.out.println(flag?'Y':'N');
		}
	}
}