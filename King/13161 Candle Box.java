import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int D=Integer.parseInt(s);
			int R=Integer.parseInt(br.readLine());
			int T=Integer.parseInt(br.readLine());
			int sum=R+T;
			
			int cR=0;
			int cT=0;
			for (int rAge=0;true;rAge++) {
				int tAge=rAge-D;
				if (rAge>=4) cR+=rAge;
				if (tAge>=3) cT+=tAge;
				if (cR+cT>sum) break; // Input has issue, stop now.
				else if (cR+cT==sum) {
					System.out.println(R-cR);
					break;
				}
			}
		}
	}

}
