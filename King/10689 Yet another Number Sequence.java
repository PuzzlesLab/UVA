import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int [] dpSize= {0,60,300,1500,15000}; //Pisano period
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			
			int [] fib=new int [dpSize[m]];
			
			int powM=(int)(Math.pow(10, m));
			
			fib[0]=a%powM;
			fib[1]=b%powM;
			for (int i=2;i<=Math.min(n, fib.length-1);i++) fib[i]=(fib[i-1]+fib[i-2])%powM;

			System.out.println(fib[n%fib.length]);
		}
	}

}