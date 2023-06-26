import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			/*
			 * Explanation :
			 * 
			 *  n=2 =>  1-2, 2-1  -- 50%
			 *  n=3 =>  1-2-3, 2-1-3, 3-1-2, 3-2-1  -- 50%
			 *
			 */
			System.out.println("1/2");
		}
	}

}
