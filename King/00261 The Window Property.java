import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main {

	private static int check(String s, int K, int end) {
		HashSet<String> windows=new HashSet<>();
		for (int start=0;start+K<=end;start++) {
			windows.add(s.substring(start, start+K));
			if (windows.size()>K+1) return start+K;
		}
		return -1;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int ans=Integer.MAX_VALUE;
			for (int k=1;k<=s.length();k++) {
			        // Optimization: Truncate as early as possible with current finding since we just want to find first occurrence.
				int curr=check(s,k,ans==Integer.MAX_VALUE?s.length():ans);
				if (curr!=-1) ans=Math.min(ans,curr);
			}
			if (ans==Integer.MAX_VALUE) System.out.println("YES");
			else System.out.printf("NO:%d\n",ans);
		}
	}

}