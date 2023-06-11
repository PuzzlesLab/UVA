import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		final long MAX=1<<16;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int M=Integer.parseInt(br.readLine());
		for (int m=0;m<M;m++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			if (b==0) {
				System.out.println(1);
				continue;
			}
			
			long n=2;
			long c=a;
			long d=b;
			while (true) {
				if (c!=0 && d==0) {
					c=c*a;
					d=c*b;
				} else if (c==0 && d!=0) {
					c=-d*b;
					d=d*a;
				} else {
					c=a*c-b*d;
					d=a*d+b*c;
				}
				if (c==0) {
					n<<=1;
					break;
				}
				if (c>MAX || d>MAX) {
					n=-1;
					break;
				}
				if (d==0) {
					break;
				}
				n++;
			}
			System.out.println(n==-1 ? "TOO COMPLICATED" : n);
		}
	}

}