import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			if (st.countTokens()==1) {
				long N=Long.parseLong(st.nextToken());
				if ((N&3)!=2) {
					long temp=Math.abs(N);

					long A=0;
					long B=0;
					if (N!=0) {
						if ((temp&1)==1) {
							A=(temp+1)>>1;
							B=(temp-1)>>1;
						} else {
							A=(temp>>2)+1;
							B=(temp>>2)-1;
						}
						if (N<0) {
							temp=A;
							A=B;
							B=temp;
						}
					}

					System.out.printf("%d %d\n",A,B);
				} else System.out.println("Bachelor Number.");
			} else if (st.countTokens()==2) {
				long A=Long.parseLong(st.nextToken());
				long B=Long.parseLong(st.nextToken());

				if ((A&1)==1) A++;
				int ans=0;
				for (long i=A;i<=B;i+=2) if ((i&3)!=0) ans++;
				System.out.println(ans);
			}
		}
	}

}
