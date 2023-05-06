import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=1;n<=N;n++) {
			int num=Integer.parseInt(br.readLine());
			Stack<Integer> ans=new Stack<>();
			if (num==0) ans.push(0);
			else {
				while (num!=0) {
					int rem=num%-2;
					num/=-2;
					if (rem==-1) {
						rem=Math.abs(rem);
						num++;
					}
					ans.push(rem);
				}
			}
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(n);
			sb.append(": ");
			while (!ans.isEmpty()) sb.append(ans.pop());
			System.out.println(sb.toString());
		}
	}
}
