import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static boolean cmp(int [] list1, int [] list2) {
		if (list1.length!=list2.length) return list2.length>list1.length;
		else for (int i=list1.length-1;i>=0;i--) if (list1[i]!=list2[i]) return list1[i]<list2[i];
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int S=Integer.parseInt(s);
			int N=Integer.parseInt(br.readLine());
			
			int [] solCombi=null;
			int sol=-1;
			
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int D=Integer.parseInt(st.nextToken());
				if (D==0) continue;

				int [] stamps=new int [D];
				for (int d=0;d<D;d++) stamps[d]=Integer.parseInt(st.nextToken());

				boolean [][] dp=new boolean [S+1][(stamps[D-1]*S)+1];
				boolean [] exists=new boolean [dp[0].length];
				dp[0][0]=true;
				exists[0]=true;
				for (int d=0;d<D;d++) for (int used=1;used<=S;used++) {
					for (int value=dp[used].length-1-stamps[d];value>=0;value--) {
						if (dp[used-1][value]) {
							int sum=value+stamps[d];
							dp[used][sum]=true;
							exists[sum]=true;
						}
					}
				}
				
				int currMax=0;
				for (int i=0;i<exists.length;i++) {
					if (exists[i]) currMax=i;
					else break;
				}
				
				if (currMax>sol || (currMax==sol && cmp(stamps,solCombi))) {
					sol=currMax;
					solCombi=stamps;
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append(String.format("max coverage =%4d :", sol));
			for (int i=0;i<solCombi.length;i++) sb.append(String.format("%3d",solCombi[i]));
			System.out.println(sb.toString());
		}
	}

}
