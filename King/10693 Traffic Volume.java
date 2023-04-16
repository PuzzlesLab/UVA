import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int L=Integer.parseInt(st.nextToken());
			int f=Integer.parseInt(st.nextToken());
			/*
			 *  d + L = (v*v)/2f
			 *  d=0,
			 *  v=sqrt(L*2*f)
			 */
			double v=Math.sqrt(L*2*f);
			double Vmax=(v*3600)/(2*L); // 2*L=start+end
			System.out.printf("%.8f %.8f\n",v,Vmax);
		}
	}
}
