import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			double [] amount=new double [N];
			for (int n=0;n<N;n++) amount[n]=Double.parseDouble(br.readLine());
			
			double avg=0;
			for (int n=0;n<N;n++) avg+=amount[n];
			avg=Math.round(avg*100/N)/100.0;
			
			double low=0, high=0;
			for (int n=0;n<N;++n) {
				if (amount[n]>avg) low+=amount[n]-avg;
				else if (amount[n]<avg) high+=avg-amount[n];
			}
			
			System.out.printf("$%.2f\n", Math.min(low, high));
		}
	}

}