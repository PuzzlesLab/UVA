import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Main {
	
	public static void main (String [] args) throws Exception {
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MMMM-dd");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			LocalDate ld=LocalDate.parse(br.readLine(),formatter);
			ld = ld.plusDays(Integer.parseInt(br.readLine()));
			System.out.printf("Case %d: %s\n", testCase, ld.format(formatter));
		}
	}

}