import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			int [] counts=new int [2];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				for (int m=0;m<M;m++) {
					int c=Integer.parseInt(st.nextToken());
					if (c==-1) continue;
					counts[(n+m)&1]+=c;
				}
			}
			// Every crosswalk is shared by 2 blocks (i.e. odd & even).
			// So odd should be equal to even. Missing = difference of odd and even.
			System.out.println(Math.abs(counts[0]-counts[1]));
		}
 	}

}
