import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb=new StringBuilder();
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			double ln=Math.log(N);
			int L=0;
			while (true) {
				double x=1-Math.exp(ln-L);
				if (Math.abs(x)<1) {
					sb.append(String.format("%d %.8f\n",L,x)); // Printing every case will lead to TLE.
					break;
				}
				L++;
			}
		}
		System.out.print(sb.toString());
	}

}