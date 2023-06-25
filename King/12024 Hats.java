import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX=13;
		int [] num=new int [MAX];
		num[1]=0;
		num[2]=1;
		int [] den=new int [MAX];
		den[1]=1;
		den[2]=2;
		for (int n=3;n<MAX;n++) {
			num[n]=(n-1)*(num[n-1]+num[n-2]);
			den[n]=den[n-1]*n;
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			System.out.printf("%d/%d\n",num[N],den[N]);
		}
	}

}
