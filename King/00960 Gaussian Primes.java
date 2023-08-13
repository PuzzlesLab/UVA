import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX=10000;
		boolean [] notPrime=new boolean [MAX+1];
		for (int i=2;i<=MAX;i++) {
			if (!notPrime[i]) continue;
			for (int i2=i*i;i2<=MAX;i2+=i) notPrime[i2]=true;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			if (a!=0 && b!=0) {
				int max=a*a+b*b;
				boolean flag=true;
				for (int fa=2;fa*fa<=max;fa++) if (max%fa==0) {
					flag=false;
					break;
				}
				System.out.println(flag?"P":"C");
			} else if (a==0) System.out.println(Math.abs(b)%4==3 ? "P" : "C");
			else System.out.println(Math.abs(a)%4==3 ? "P" : "C");
		}
	}

}
