import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int [] CYCLE= {
		0,1,5,2,8,3,9,2,8,7,
		0,1,7,0,6,1,7,4,8,7
	};

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=0;
			if (s.length()<=2) N=Integer.parseInt(s);
			else N=Integer.parseInt(s.substring(s.length()-2, s.length()));

			System.out.println((N/10*7+CYCLE[N%20])%10);
		}
	}

}
