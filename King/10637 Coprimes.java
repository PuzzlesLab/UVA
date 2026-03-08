import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int S;
	private static StringBuilder Ans;

	private static int gcd(int a, int b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	private static void compute(int minNum, int currSum, int [] pick, int t) {
		if (t==pick.length) {
			if (currSum==S) {
				for (int i=0;i<pick.length;i++) {
					Ans.append(pick[i]);
					Ans.append(' ');
				}
				Ans.setLength(Ans.length()-1);
				Ans.append('\n');
			}
			return;
		}
		if (currSum>S || currSum+(pick.length-t)*minNum>S) return;

		if (currSum+minNum<=S) {
			boolean flag=true;
			for (int i=0;i<t && flag;i++) flag&=gcd(pick[i],minNum)==1;
			if (flag) {
				pick[t]=minNum;
				compute(minNum,currSum+minNum,pick,t+1);
			}
		}
		compute(minNum+1,currSum,pick,t);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			S=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			
			Ans=new StringBuilder();
			compute(1,0,new int [T],0);

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(":\n");
			sb.append(Ans);
			System.out.print(sb);
		}
	}

}