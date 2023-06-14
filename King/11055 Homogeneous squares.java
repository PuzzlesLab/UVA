import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int [][] nums=new int [N][N];
			
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int n2=0;n2<N;n2++) nums[n][n2]=Integer.parseInt(st.nextToken());
			}
			
			int ref=0;
			for (int n=0;n<N;n++) ref+=nums[n][n];
			
			boolean flag=true;
			for (int test=0;test<10*N;test++) { // Sample 10*N random combinations and check.
				ArrayList<Integer> ranNums=new ArrayList<>();
				for (int n=0;n<N;n++) ranNums.add(n);

				Random rand=new Random();
				int [] cols=new int[N];
				for (int n=0;n<N;n++) {
					int idx=rand.nextInt(ranNums.size());
					cols[n]=ranNums.remove(idx);
				}

				int curr=0;
				for (int n=0;n<N;n++) curr+=nums[n][cols[n]];
				if (curr!=ref) {
					flag=false;
					break;
				}
			}
			
			System.out.println(flag ? "homogeneous" : "not homogeneous");
		}
	}

}
