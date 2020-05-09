import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static long S2(long N) {
		long count=0;
		for (int size=1;size<=N;size++) count+=(N-size+1) * (N-size+1);
		return count;
	}
	
	public static long R2(long N) {
		long count=0;
		for (int d1Size=1;d1Size<=N;d1Size++)
			for (int d2Size=1;d2Size<=N;d2Size++)
				if (d1Size!=d2Size) count+=(N-d1Size+1) * (N-d2Size+1);
		return count;
	}
	
	public static long S3(long N) {
		long count=0;
		for (int size=1;size<=N;size++) count+=(N-size+1) * (N-size+1) * (N-size+1);
		return count;
	}
	
	public static long R3(long N) {
		long count=0;
		for (int d1Size=1;d1Size<=N;d1Size++)
			for (int d2Size=1;d2Size<=N;d2Size++)
				for (int d3Size=1;d3Size<=N;d3Size++)
					if (d1Size!=d2Size || d1Size!=d3Size || d2Size!=d3Size)
						count+=(N-d1Size+1) * (N-d2Size+1) * (N-d3Size+1);
		return count;
	}
	
	public static long S4(long N) {
		long count=0;
		for (int size=1;size<=N;size++) count+=(N-size+1) * (N-size+1) * (N-size+1) * (N-size+1);
		return count;
	}
	
	public static long R4(int N) {
		long count=0;
		for (int d1Size=1;d1Size<=N;d1Size++)
			for (int d2Size=1;d2Size<=N;d2Size++)
				for (int d3Size=1;d3Size<=N;d3Size++)
					for (int d4Size=1;d4Size<=N;d4Size++)
						if (d1Size!=d2Size || d1Size!=d3Size || d1Size!=d4Size || d2Size!=d3Size || d2Size!=d4Size || d3Size!=d4Size)
							count+=(N-d1Size+1) * (N-d2Size+1) * (N-d3Size+1) * (N-d4Size+1);
		return count;
	}
	
	public static void main (String [] args) throws Exception {
		long [][] ans=new long [101][6];
		for (int i=1;i<=100;i++) {
			ans[i][0]=S2(i);
			ans[i][1]=R2(i);
			ans[i][2]=S3(i);
			ans[i][3]=R3(i);
			ans[i][4]=S4(i);
			ans[i][5]=R4(i);
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			System.out.printf("%d %d %d %d %d %d\n", ans[N][0], ans[N][1], ans[N][2], ans[N][3], ans[N][4],ans[N][5]);
		}
	}

}