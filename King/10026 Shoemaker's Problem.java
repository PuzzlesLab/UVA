import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			int N=Integer.parseInt(br.readLine());
			int[][] data=new int [N][3];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				data[n]=new int [] {n+1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}

			Arrays.sort(data, (a,b) -> {
				int d=a[1]*b[2]-b[1]*a[2];
				if (d==0) return a[0]-b[0];
				return d;
			});
			
			StringBuilder sb=new StringBuilder();
			for (int [] ans : data) {
				sb.append(ans[0]);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			if (testCase>0) System.out.println();
			System.out.println(sb.toString());
		}
	}

}