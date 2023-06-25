import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int cows=Integer.parseInt(st.nextToken());
			int cars=Integer.parseInt(st.nextToken());
			int shows=Integer.parseInt(st.nextToken());
			int doors=cows+cars;
			
			double n1=cars;
			double d1=doors;
			
			double n2=(cars-1);
			double d2=doors-shows-1;

			double n3=cows;
			double d3=doors;

			double n4=cars;
			double d4=doors-shows-1;

			System.out.printf("%.5f\n",(n1/d1)*(n2/d2)+(n3/d3)*(n4/d4));
		}
	}

}
