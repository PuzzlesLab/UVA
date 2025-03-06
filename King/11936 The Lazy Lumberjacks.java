import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double a=Double.parseDouble(st.nextToken());
			double b=Double.parseDouble(st.nextToken());
			double c=Double.parseDouble(st.nextToken());
			
			boolean flag=(a+b>c) && (a+c>b) && (b+c>a);
			System.out.println(flag?"OK":"Wrong!!");
		}
 	}

}
