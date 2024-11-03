import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			double L=Integer.parseInt(br.readLine());
			double W=L/10*6;
			double R=L/5;
			double red=Math.acos(-1)*R*R;
			double green=L*W-red;
			System.out.printf("%.2f %.2f\n",red,green);
		}
 	}
}
