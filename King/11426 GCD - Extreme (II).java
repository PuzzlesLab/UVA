import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX=4000001;

		long [] Phi=new long [MAX];
		for (int i=0;i<Phi.length;i++) Phi[i]=i;
		for (int i=2;i<Phi.length;i++) if (Phi[i]==i) {
			for (int i2=i;i2<Phi.length;i2+=i) Phi[i2]=(Phi[i2]/i)*(i-1);
		}

		long [] F=new long [MAX];
		for (int i=1;i<MAX;i++) for (int i2=i<<1;i2<MAX;i2+=i) F[i2]+=i*Phi[i2/i];
		long [] ans=new long [MAX];
		for (int i=2;i<MAX;i++) ans[i]=ans[i-1]+F[i];

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;
			System.out.println(ans[N]);
		}
	}

}
