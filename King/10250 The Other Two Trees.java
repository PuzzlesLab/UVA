import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			double x1=Double.parseDouble(st.nextToken());
			double y1=Double.parseDouble(st.nextToken());
			double x2=Double.parseDouble(st.nextToken());
			double y2=Double.parseDouble(st.nextToken());

			double mx=(x1+x2)/2;
			double my=(y1+y2)/2;
			double dx=mx-x1;
			double dy=my-y1;
			
			System.out.printf("%.10f %.10f %.10f %.10f\n",mx-dy,my+dx,mx+dy,my-dx);
		}
 	}

}