import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			int [] A=new int [Integer.parseInt(br.readLine())];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int a=0;a<A.length;a++) A[a]=Integer.parseInt(st.nextToken());

			int sum=0;
			for (int a1=1;a1<A.length;a1++) for (int a2=0; a2<a1; a2++) if (A[a1]>=A[a2]) sum++;
			System.out.println(sum);
		}
	}

}
