import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int gcd(int a, int b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			int divisor=0;
			int [] ticket=new int [N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				for (int m=0;m<M-1;m++) st.nextToken();
	
				ticket[n]=Integer.parseInt(st.nextToken());
				divisor+=ticket[n];
			}

			if (N==1) {
				System.out.println("1 / 1");
				continue;
			}

			StringBuilder sb=new StringBuilder();
			for (int n=0;n<N;n++) {
				int gcd=gcd(ticket[n],divisor);
				int num=ticket[n]/gcd;
				int den=divisor/gcd;
				sb.append(num);
				sb.append(" / ");
				sb.append(den);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}
