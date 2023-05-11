import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		long [] pow2=new long [55];
		for (int i=0;i<pow2.length;i++) pow2[i]=1L<<i;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			long N=Long.parseLong(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			System.out.println(pow2[b+1]>N ? "yes" : "no");
		}
	}

}
