import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static void generate(char [] ch, boolean [] used, String curr, StringBuilder ans) {
		if (curr.length()==ch.length) {
			ans.append(curr);
			ans.append('\n');
		} else {
			for (int i=0;i<ch.length;i++) if (!used[i]) {
				used[i]=true;
				for (int ins=0;ins<=curr.length();ins++) {
					String prev=curr.substring(0,ins);
					String next=curr.substring(ins,curr.length());
					generate(ch,used,prev+ch[i]+next,ans);
				}
				used[i]=false;
				break; // Prevent duplicate permutation
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean first=true;
		while ((s=br.readLine())!=null) {
			char [] ch=s.toCharArray();

			StringBuilder ans=new StringBuilder();
			if (first) first=false;
			else ans.append('\n');

			generate(ch,new boolean[ch.length],"",ans);
			System.out.print(ans);
		}
	}

}
