import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static int [] Nums;
	private static int [] Sol;

	private static boolean find(int currIdx, int n, int mod) {
		if (n==N) return mod==0;
		if (currIdx==Nums.length) return false;
		if (Nums.length-currIdx<N-n) return false;
		
		if (find(currIdx+1,n,mod%N)) return true;
		if (find(currIdx+1,n+1,(mod+Nums[currIdx])%N)) {
			Sol[n]=Nums[currIdx];
			return true;
		}

		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			N=Integer.parseInt(br.readLine());
			if (N==0) break;
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			Nums=new int [2*N-1];
			for (int n=0;n<Nums.length;n++) Nums[n]=Integer.parseInt(st.nextToken());

			Sol=new int [N];
			boolean flag=find(0,0,0);
			System.out.println(flag?"Yes":"No");
			if (flag) {
				StringBuilder sb=new StringBuilder();
				for (int n=0;n<N;n++) {
					sb.append(Sol[n]);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				System.out.println(sb.toString());
			}
		}
	}

}
