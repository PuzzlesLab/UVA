import java.util.Arrays;
import java.util.Scanner;

class Main {
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		for (int t=0;t<T;t++) {
			int S=sc.nextInt(), A=sc.nextInt();
			int F=sc.nextInt();
			
			int [] X=new int [F], Y=new int [F];
			for (int f=0;f<F;f++) {
				X[f]=sc.nextInt();
				Y[f]=sc.nextInt();
			}
			
			Arrays.sort(X);
			Arrays.sort(Y);
			
			if (F==0) {
				X=new int [] {0}; Y=new int [] {0};
				F=1;
			}
			
			
			int median=(F%2==0) ? (F/2)-1 : F/2;
			System.out.printf("(Street: %d, Avenue: %d)\n", X[median], Y[median]);
		}
	}

}
