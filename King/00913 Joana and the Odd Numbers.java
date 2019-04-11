import java.io.BufferedReader;
import java.io.InputStreamReader;

class uva {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			long lineNo=((N-1)/2)+1;
			long curr=1+2*lineNo*lineNo;
			System.out.println(3*(curr-4));
		}
	}

}
