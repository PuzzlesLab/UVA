import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		long [] m=new long [20001];
		int add=1;
		for (int i=2;i<m.length;i++) {
			m[i]=m[i-1]+add;
			add++;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			System.out.printf("Case %d: %d\n",tc++,m[A]*m[B]);
		}
	}

}
