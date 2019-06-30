import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.StringTokenizer;

class uva {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			LocalDate dt=LocalDate.of(2011, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			System.out.println(dt.getDayOfWeek().name().charAt(0)+dt.getDayOfWeek().name().substring(1).toLowerCase());
		}
	}

}