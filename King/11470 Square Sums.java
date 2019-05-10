import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int [][] nums=new int [N][N];
			for (int i=0;i<N;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int i2=0;i2<N;i2++) nums[i][i2]=Integer.parseInt(st.nextToken());
			}
			
			StringBuilder sb=new StringBuilder("Case ");
			sb.append(testCase++);
			sb.append(':');
			for (int side=0;side<=(N-1)/2;side++) {
				int sum=0;
				if (N%2==0 || side<(N-1)/2) {
					for (int i=side;i<N-side;i++) {
						sum+=nums[i][side];
						sum+=nums[side][i];
						sum+=nums[i][N-1-side];
						sum+=nums[N-1-side][i];
					}
					sum-=nums[side][side];
					sum-=nums[N-1-side][side];
					sum-=nums[side][N-1-side];
					sum-=nums[N-1-side][N-1-side];
				} else sum=nums[side][side];
				
				sb.append(' ');
				sb.append(sum);
			}
			System.out.println(sb.toString());
		}
	}

}