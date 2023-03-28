import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		int [] start=new int [2001];
		start[0]=1;
		int curr=1;
		for (int i=1;i<start.length;i++) {
			start[i]=start[i-1]+curr;
			curr++;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int A=Integer.parseInt(br.readLine());
		for (int a=1;a<=A;a++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			System.out.println(start[N+M]+N);
		}
	}
}
