import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			int T=Integer.parseInt(br.readLine());
			int N=Integer.parseInt(br.readLine());
			
			for (int t=0;t<T;t++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int [] src= {N-1-Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
				int [] dest= {N-1-Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
				
				int [] delta= {Math.abs(dest[0]-src[0]),Math.abs(dest[1]-src[1])};
				if (delta[0]==0 && delta[1]==0) System.out.println(0);
				else if (delta[0]==delta[1]) System.out.println(1);
				else if (delta[0]%2 == delta[1]%2) System.out.println(2);
				else System.out.println("no move");
				
			}
		}
	}

}
