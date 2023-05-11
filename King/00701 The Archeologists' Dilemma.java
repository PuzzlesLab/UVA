import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			int N2=N+1;
			
			int pad=(int)(Math.log10(N))+1;
			double logN=Math.log10(N)+pad;
			double logN2=Math.log10(N2)+pad;
			while (true) {
				logN++;
				logN2++;
				double min=logN/Math.log10(2);
				double max=logN2/Math.log10(2);
				if ((int)min!=(int)max) {
					System.out.println((int)max);
					break;
				}
			}
			
		}
	}

}
