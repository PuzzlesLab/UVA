import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			long P=Long.parseLong(s);
			long round=0;
			while (P>1) {
				round+=(P+1)/2;
				P/=2;
			}
			System.out.println(round);
		}
	}
}