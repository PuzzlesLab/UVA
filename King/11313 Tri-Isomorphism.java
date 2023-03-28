import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int E=(N*(N-1))>>1; // Number of edges when nodes are in mesh
			System.out.println(N>=3 && E%3==0 ? "YES" : "NO");
		}
	}
}
