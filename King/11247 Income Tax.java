import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			long M=Integer.parseInt(st.nextToken());
			int rate=Integer.parseInt(st.nextToken());
			if (rate==100) {
				System.out.println("Not found");
				continue;
			}
			rate=100-rate;
			long m=(long)Math.floor((M-1)*100.0/rate);
			if (((M-1)*100)%rate==0) m--;
			if (m<=M-1) m=0;
			System.out.println(m==0 ? "Not found" : m);
		}
	}
}