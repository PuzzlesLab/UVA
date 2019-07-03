import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int delta1=Integer.parseInt(st.nextToken());
			int delta2=Integer.parseInt(st.nextToken());

			int delta=Math.abs(delta2-delta1);
			double rate = 43200.0 / delta;
			double secondsNeeded = rate * (86400 - delta2);
			int minuteTaken= (int)((secondsNeeded / 60.0) + 0.5);
			long H=(minuteTaken/60)%12;
			if (H==0) H=12;
			long M=minuteTaken%60;
			
			System.out.printf("%d %d %02d:%02d\n", delta1, delta2, H, M);
		}
		
	}

}
