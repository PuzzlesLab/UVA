import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("*")) {
			s=s.trim();
			boolean flag=s.matches("^[+-]?\\d+(\\.\\d+([eE][+-]?\\d+)?|[eE][+-]?\\d+)$");
			System.out.printf("%s is %s.\n",s,flag?"legal":"illegal");
		}
	}

}
