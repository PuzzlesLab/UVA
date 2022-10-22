import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Context {
		StringBuilder num;
		int rem;
		public Context(StringBuilder num, int rem) {
			this.num=num;
			this.rem=rem;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			String Fs=st.nextToken();
			String Ns=st.nextToken();
			if (Fs.equals("0") || Ns.equals("0")) break;

			char [] ch=Fs.toCharArray(); // Descending digits
			int [] nums=new int [ch.length];
			for (int n=0;n<nums.length;n++) nums[n]=ch[nums.length-1-n]-'0'; // Make it ascending (we want the smallest num generated first.)

			int N=Integer.parseInt(Ns);
			boolean [] visited=new boolean[N];

			String ans="impossible";
			LinkedList<Context> q=new LinkedList<>();
			q.addLast(new Context(new StringBuilder(),0));
			while (!q.isEmpty()) {
				Context ctx=q.removeFirst();
				if (ctx.num.length()>0 && ctx.rem==0) {
					ans=ctx.num.toString();
					break;
				}
				for (int n=0;n<nums.length;n++) {
					if (ctx.num.length()==0 && nums[n]==0) continue;

					int nextRem=(ctx.rem*10+nums[n])%N;
					if (!visited[nextRem]) {
						visited[nextRem]=true;
						StringBuilder sb=new StringBuilder();
						sb.append(ctx.num.toString());
						sb.append(nums[n]);
						q.addLast(new Context(sb,nextRem));
					}
				}
			}
			
			System.out.println(ans);
		}
	}

}
