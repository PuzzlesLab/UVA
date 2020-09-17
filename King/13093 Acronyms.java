import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			StringBuilder sb1=new StringBuilder();
			while (st.hasMoreTokens()) sb1.append(st.nextToken().charAt(0));
			String init1=sb1.toString();
			
			st=new StringTokenizer(br.readLine());
			StringBuilder sb2=new StringBuilder();
			while (st.hasMoreTokens()) sb2.append(st.nextToken().charAt(0));
			String init2=sb2.toString();
			
			System.out.println((init1.equals(init2)) ? "yes" : "no");
		}
		
	}
}