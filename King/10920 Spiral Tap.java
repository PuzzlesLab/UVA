import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int size=Integer.parseInt(st.nextToken());
			long p=Long.parseLong(st.nextToken());
			
			int row=size/2, col=size/2;
			long delta=0;
			char direction='N';
			while (p>1) {
				if (direction=='N' && p>1) {
					delta=Math.min(p-1, delta+1);
					p-=delta;
					row-=delta;
					direction='W';
				}
				if (direction == 'W' && p>1) {
					delta=Math.min(p-1, delta);
					p-=delta;
					col-=delta;
					direction='S';
				}
				if (direction =='S' && p>1) {
					delta=Math.min(p-1, delta+1);
					p-=delta;
					row+=delta;
					direction='E';
				}
				if (direction =='E' && p>1) {
					delta=Math.min(p-1, delta);
					p-=delta;
					col+=delta;
					direction='N';
				}
			}
			
			col++;
			row=size-row;
			
			System.out.printf("Line = %d, column = %d.\n", row, col);
		}
	}

}