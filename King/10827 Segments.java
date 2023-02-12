import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			
			int [][] intervals=new int [N+2][3];
			intervals[0]=new int [] {1,1,0};
			for (int n=1;n<=N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				intervals[n][0]=Integer.parseInt(st.nextToken());
				intervals[n][1]=Integer.parseInt(st.nextToken());
				intervals[n][2]=intervals[n][1]-intervals[n][0];
			}
			intervals[N+1]=new int [] {N,N,0};

			int [] steps=new int [2];
			for (int n=1;n<=N+1;n++) {
				// Last left to current left vs Last right to current left
				int l=Math.min(steps[0]+Math.abs(intervals[n-1][0]-intervals[n][0]),steps[1]+Math.abs(intervals[n-1][1]-intervals[n][0]));
				// Last left to current right vs Last right to current right
				int r=Math.min(steps[0]+Math.abs(intervals[n-1][0]-intervals[n][1]),steps[1]+Math.abs(intervals[n-1][1]-intervals[n][1]));
				steps[0]=r+intervals[n][2];
				steps[1]=l+intervals[n][2];
			}
			System.out.println(Math.min(steps[0],steps[1])+N-1); // N-1 = step between rows.
		}
	}

}
