import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s)/10;
			int pow=(N-196)+2;
			int bits=1<<pow;
			
			int fac=1;
			double sum=0.0;
			while (sum<bits) {
				fac++;
				sum+=Math.log(fac)/Math.log(2);
			}
			System.out.println(fac-1);
		}
	}

}
