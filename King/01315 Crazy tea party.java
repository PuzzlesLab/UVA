import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		int diff=2;
		int [] ans=new int [32768];
		ans[3]=1;
		ans[4]=2;
		for (int n=5;n<ans.length;n++) {
			ans[n]=ans[n-1]+diff;
			if (n%2==0) diff++;
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			System.out.println(ans[Integer.parseInt(br.readLine())]);
		}
	}
}
