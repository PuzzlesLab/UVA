import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		int [] dp=new int [10000];
		for (int n=1;n<dp.length;n++) {
			boolean [] exists=new boolean [10000];
			int ans=0;

			int temp=n;
			while (!exists[temp]) {
				ans++;
				exists[temp]=true;

				temp*=temp;
				temp/=100; // Remove last 2 digits;
				temp%=10000; // Get 4 digits;
			}

			dp[n]=ans;
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int n=Integer.parseInt(s);
			System.out.println(dp[n]);
		}
	}

}
