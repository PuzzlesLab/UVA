import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		double [] ans=new double [100000];
		for (int i=1;i<ans.length;i++) ans[i]=ans[i-1]+1.0/(i*2);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("# Cards Overhang");
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			System.out.printf("%5d     %.3f\n",N,ans[N]);
		}
	}
}
