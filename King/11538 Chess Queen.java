import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long M=Long.parseLong(st.nextToken());
			long N=Long.parseLong(st.nextToken());

			if (M==0 && N==0) break;
			
			long min=Math.min(M,N);
			long max=Math.max(M,N);
			long a1=M*N*(N+M-2); // horizontal + vertical
			long a2=(((min-1)*min*(min+1))<<2)/3; // short diag
			long a3=((max-min-1)*min*(min-1))<<1; // long diag

			System.out.println(a1+a2+a3);
		}
	}

}
