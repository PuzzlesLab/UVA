import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int [] stones=new int [N];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) stones[n]=Integer.parseInt(st.nextToken());

			int nim=0;
			for (int n=0;n<N;n++) nim^=stones[n];
			System.out.println(nim!=0?"Yes":"No");
		}
 	}

}
