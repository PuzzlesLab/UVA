import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int R=Integer.parseInt(br.readLine());
		for (int r=0;r<R;r++) {
			int N=Integer.parseInt(br.readLine());
			int e=(int)Math.floor(-N*Math.log10(2));
			double rem=-N*Math.log10(2)-e;
			System.out.printf("2^-%d = %.3fE%d\n",N,Math.pow(10,rem),e);
		}
	}

}
