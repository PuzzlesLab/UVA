import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			long N=Long.parseLong(s);
			boolean stan=true;
			long p=1;
			while (true) {
				if (stan) p*=9;
				else p<<=1;
				if (p>=N) break;

				stan=!stan;
			}
			System.out.println((stan) ? "Stan wins." : "Ollie wins.");
		}
	}

}
