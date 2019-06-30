import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			/*
			 * Explanation : 
			 * H moves by 6 degree each time.
			 * M moves by 6 degree each time.
			 * 
			 * The delta between them must be different by factor of 6!!
			 * 
			 */
			System.out.println((Integer.parseInt(s)%6==0) ? "Y" : "N");
		}
	}

}