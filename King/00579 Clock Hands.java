import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0:00")) {
			StringTokenizer st=new StringTokenizer(s,":");
			int hrs=Integer.parseInt(st.nextToken());
			if (hrs==12) hrs=0;
			int min=Integer.parseInt(st.nextToken());
			
			double hrsUnit=360/12.0;
			double hrsA=hrs*hrsUnit+(min/60.0)*hrsUnit;
			double minA=(min/60.0)*360;
			
			double delta=Math.max(minA, hrsA)-Math.min(minA, hrsA);
			delta=Math.min(delta, 360-delta);
			System.out.printf("%.3f\n", delta);
		}
	}

}
