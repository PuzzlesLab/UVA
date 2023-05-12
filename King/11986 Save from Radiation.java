import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			long N=Long.parseLong(br.readLine());
			int sol=0;
			if (N>0) { // Math.log(N)/Math.log(2) contains fp precision issue and causes WA.
				sol=1;
				while (N>1) {
					N>>=1;
					sol++;
				}
				if (N==0) sol++;
			}
			System.out.printf("Case %d: %d\n",t,sol);
		}
	}

}
