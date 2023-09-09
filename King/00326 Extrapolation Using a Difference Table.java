import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			if (N==0) break;
			
			int [] nums=new int[N];
			for (int n=0;n<N;n++) nums[n]=Integer.parseInt(st.nextToken());
			for (int level=1;level<N;level++) for (int n=0;n<N-level;n++) nums[n]=nums[n+1]-nums[n];
			int K=Integer.parseInt(st.nextToken());

			for (int k=0;k<K;k++) for (int n=1;n<N;n++) nums[n]+=nums[n-1];
			
			StringBuilder sb=new StringBuilder();
			sb.append("Term ");
			sb.append(N+K);
			sb.append(" of the sequence is ");
			sb.append(nums[nums.length-1]);
			System.out.println(sb);
		}
	}

}
