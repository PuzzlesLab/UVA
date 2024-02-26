import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static boolean canMove(String s1, String s2) {
		int diff=0;
		for (int i=0;i<s1.length();i++) {
			if (s1.charAt(i)!=s2.charAt(i)) diff++;
			if (diff>1) return false;
		}
		return diff==1;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int W=Integer.parseInt(st.nextToken());
			Integer.parseInt(st.nextToken());
			
			String ss=br.readLine();
			String se=br.readLine();
			HashSet<String> wordSet=new HashSet<>();
			for (int w=2;w<W;w++) {
				wordSet.add(br.readLine());
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append(ss);
			sb.append('\n');
			while (!wordSet.isEmpty()) {
				s=null;
				for (String test: wordSet) {
					if (canMove(ss,test)) {
						s=test;
						break;
					}
				}
				sb.append(s);
				sb.append('\n');
				wordSet.remove(s);
				ss=s;
			}
			sb.append(se);
			sb.append('\n');
			System.out.print(sb.toString());
		}
	}
}