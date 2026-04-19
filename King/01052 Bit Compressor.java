import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static String Compressed;
	private static int Ans;
	private static int L;
	private static int N;

	private static void dfs(int start, int len, int ones) {
		if (Ans>=2 || len>L || ones>N) return;

		if (start==Compressed.length()) {
			if (len==L && ones==N) Ans++;
			return;
		}

		char c=Compressed.charAt(start);
		if (c=='0') {
			dfs(start+1,len+1,ones);
			return;
		}

		int currOneLen=0;
		for (int end=start;end<Compressed.length();end++) {
			currOneLen=(currOneLen<<1)+(Compressed.charAt(end)-'0');
			int segmentLen=end-start+1;
			if (segmentLen>1 && segmentLen>=currOneLen) continue;

			if (end+1==Compressed.length() || Compressed.charAt(end+1)=='0') {
				dfs(end+1,len+currOneLen,ones+currOneLen);
				if (currOneLen==3) dfs(end+1,len+2,ones+2); // special case, 11.
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			L=Integer.parseInt(st.nextToken());
			N=Integer.parseInt(st.nextToken());
			
			Compressed=br.readLine().trim();
			Ans=0;
			dfs(0,0,0);

			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc++);
			sb.append(": ");
			if (Ans==0) sb.append("NO");
			else if (Ans==1) sb.append("YES");
			else sb.append("NOT UNIQUE");
			System.out.println(sb);
		}
	}

}