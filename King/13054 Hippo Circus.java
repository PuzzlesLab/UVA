import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());
			int Ta=Integer.parseInt(st.nextToken());
			int Tb=Integer.parseInt(st.nextToken());
			
			LinkedList<Integer> heights=new LinkedList<>();
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) heights.add(Integer.parseInt(st.nextToken()));
			Collections.sort(heights);

			int walkalone=Ta*N;
			int merge=0;
			while (heights.size()>0) {
				int h1=heights.removeLast();
				if (heights.size()==0) merge+=Ta;
				else {
					int h2=heights.removeFirst();
					if (h1+h2<H) merge+=Tb;
					else {
						merge+=Ta;
						heights.addFirst(h2);
					}
				}
			}

			System.out.printf("Case %d: %d\n", testCase, Math.min(walkalone, merge));
		}
	}
}