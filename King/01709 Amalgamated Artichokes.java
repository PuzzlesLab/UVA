import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			double p=Double.parseDouble(st.nextToken());
			double a=Double.parseDouble(st.nextToken());
			double b=Double.parseDouble(st.nextToken());
			double c=Double.parseDouble(st.nextToken());
			double d=Double.parseDouble(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			double maxDec=0.0;
			double peak=0.0;
			for (int i=1;i<=N;i++) {
				double price=p*(Math.sin(a*i+b)+Math.cos(c*i+d)+2);
				if (price>peak) peak=price;
				maxDec=Math.max(peak-price, maxDec);
			}
			System.out.printf("%.6f\n", maxDec);
		}

	}

}