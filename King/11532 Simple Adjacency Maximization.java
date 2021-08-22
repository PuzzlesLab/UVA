import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int P=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());
			
			LinkedList<Integer> list=new LinkedList<>();
			while (P>Q*2) {
				list.addFirst(1);
				P--;
			}
			while (P>0 || Q>0) {
				if (P>0) {
					list.addFirst(1);
					P--;
				}
				if (Q>0) {
					list.addFirst(0);
					Q--;
				}
				if (P>0) {
					list.addFirst(1);
					P--;
				}
			}
			
			long ans=0;
			for (Integer num : list) ans=(ans<<1)+num;
			System.out.println(ans);
		}
	}
}