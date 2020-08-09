import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			//H=ut+0.5at^2, u=0, H=0.5gt^2
			StringTokenizer st=new StringTokenizer(br.readLine());
			int L=Integer.parseInt(st.nextToken());
			int D=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());
			int V=Integer.parseInt(st.nextToken());
			
			double tH=Math.sqrt(H/(0.5*9.81*1000));
			double dV=tH*V;
			
			if ((dV>=D-500 && dV<=D+500) || (dV>=L+D-500 && dV<=L+D+500)) System.out.println("EDGE");
			else if (dV>D+500 && dV<D+L-500)System.out.println("POOL");
			else System.out.println("FLOOR");
		}
	}

}