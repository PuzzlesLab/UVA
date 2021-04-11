import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

	public static int calcAngry(long [] producers, long [] consumers, long N) {
		int result=0;
		for (int p=producers.length-1;p>=0;p--) {
			if (producers[p]>N) result++;
			else break;
		}
		for (int c=0;c<consumers.length;c++) {
			if (N>consumers[c]) result++;
			else break;
		}
		return result;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int P=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			TreeSet<Long> prices=new TreeSet<>();
			prices.add(0L);

			long [] producers=new long [P];
			st=new StringTokenizer(br.readLine());
			for (int p=0;p<P;p++) {
				producers[p]=Long.parseLong(st.nextToken());
				prices.add(producers[p]);
			}
			Arrays.sort(producers);
			
			long [] consumers=new long [C];
			st=new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				consumers[c]=Long.parseLong(st.nextToken());
				prices.add(consumers[c]);
			}
			Arrays.sort(consumers);

			long ans=0L;
			long minAngry=Long.MAX_VALUE;
			for (long price : prices) {
				int angry=calcAngry(producers,consumers,price);
				if (angry<minAngry) {
					ans=price;
					minAngry=angry;
				}
			}
			System.out.printf("%d %d\n", ans, minAngry);
		}
	}
}