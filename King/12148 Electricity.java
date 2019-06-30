import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			HashMap<LocalDate, int []> data=new HashMap<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int [] record = {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
				data.put(LocalDate.of(record[2], record[1], record[0]), record);
			}
			
			int count=0;
			int sumOfDelta=0;
			for (LocalDate today : data.keySet()) {
				LocalDate ytd=today.minusDays(1);
				if (data.containsKey(ytd)) {
					int todayValue=data.get(today)[3];
					int ytdValue=data.get(ytd)[3];
					sumOfDelta+=todayValue-ytdValue;
					count++;
				}
			}
			System.out.printf("%d %d\n", count, sumOfDelta);
		}
	}

}