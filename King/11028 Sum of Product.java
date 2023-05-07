import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		int [] ans=new int [21];
		ans[2]=1;
		ans[3]=1;
		ans[4]=3;
		ans[5]=8;
		ans[6]=21;
		
		for (int i=7;i<=20;i++) {
			if (i%2==1) ans[i]=(i*i*i-16*i+27)/6;
			else ans[i]=(i*i*i-16*i+30)/6;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;
			
			System.out.printf("Case #%d: %d\n",tc++,ans[N]);
		}
	}

}
