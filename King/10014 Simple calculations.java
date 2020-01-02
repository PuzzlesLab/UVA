import java.io.BufferedReader;
import java.io.InputStreamReader;

class uva {
	
	public static void main (String [] args) throws Exception {
		/*
		 * 	2a[n] = a[n-1] + a[n+1] - 2C[n]
			3a[n-1] = 2a[n-2] + a[n+1] - 2C[n] - 4C[n-1]
			4a[n-2] = 3a[n-3] + a[n+1] - 2C[n] - 4C[n-1] - 6C[n-2]
			...
			...
			...
			(n+1)a[n-(n+1)] = nA[n-1-(n-1)] + a[n+1] - 2C[n] - 4C[n-1] - 6C[n-2] ... - 2nC[n-(n-1)]
			= (n+1)a[1] = nA[0] + a[n+1] - 2C[n] - 4C[n-1] - 6C[n-2] ... - 2nC[1]
		 * 
		 * 
		 */
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			br.readLine(); //blank.
			int N=Integer.parseInt(br.readLine());
			
			double a0=Double.parseDouble(br.readLine());
			double aLast=Double.parseDouble(br.readLine());;
			
			double [] c=new double[N];
			for (int i=0;i<N;i++) c[i]=Double.parseDouble(br.readLine());
			
			double sum=N*a0 + aLast;
			int temp=2;
			for (int i=N-1;i>=0;i--) {
				sum-=temp*c[i];
				temp+=2;
			}
			sum/=(N+1);
			
			if (t>0) System.out.println();
			System.out.printf("%.2f\n", sum);
		}
	}

}