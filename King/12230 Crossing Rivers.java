import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int TC=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int D=Integer.parseInt(st.nextToken());

			double ans=D;
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				st.nextToken(); // Useless.
				double L=Double.parseDouble(st.nextToken());
				double v=Double.parseDouble(st.nextToken());
				/*
				 * Best case - Boat at left bank, travel only L.
				 * Worst case - Boat just left left bank, travel 3L.
				 * Average case - 2L.
				 */
				double boatTime=(2*L)/v;
				ans=ans-L+boatTime; // Subtract expected walk time, + boat time.
			}

			System.out.printf("Case %d: %.3f\n\n",TC++,ans);
		}
	}

}
