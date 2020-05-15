import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			int [] singers=new int[N];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) singers[n]=Integer.parseInt(st.nextToken());
			Arrays.sort(singers);
			
			int avg=0;
			for (int n=0;n<N;n++) avg+=singers[n];
			avg/=N;
			
			int sum=0;
			for (int n=0;n<N;n++) sum+=singers[n];
			
			int bar=-1;
			if (sum%N==0) {
				int totalDiff=0;
				for (int n=0;n<N;n++) totalDiff+=Math.abs(singers[n]-avg);
				totalDiff/=2;
				
				bar=totalDiff+1;
			}

			System.out.println(bar);
		}
	}

}