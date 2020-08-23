import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			BigDecimal left=new BigDecimal(st.nextToken());
			BigDecimal right=new BigDecimal(st.nextToken());
			int cmp=left.compareTo(right);
			if (cmp>0) System.out.printf("Case %d: Bigger\n", testCase++);
			else if (cmp==0) System.out.printf("Case %d: Same\n", testCase++);
			else System.out.printf("Case %d: Smaller\n", testCase++);
		}
	}
}