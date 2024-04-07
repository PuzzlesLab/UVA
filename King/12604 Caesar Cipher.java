import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	private static char [] Charset;
	private static int [] CharIdx;

	private static boolean kmp(String t, String p) {
		int [] back=new int [p.length()+1];
		back[0]=-1;
		int i=0;
		int i2=-1;
		while (i<p.length()) {
			while (i2>=0 && p.charAt(i)!=p.charAt(i2)) i2=back[i2];
			back[++i]=++i2;
		}

		i=0;
		i2=0;
		int count=0;
		while (i<t.length()) {
			while (i2>=0 && t.charAt(i)!=p.charAt(i2)) i2=back[i2];
			i++;
			i2++;
			if (i2==p.length()) {
				count++;
				i2=back[i2];
			}
		}
		return count==1;
	}
	
	private static String shift(String s, int d) {
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<s.length();i++) sb.append(Charset[(CharIdx[s.charAt(i)]+d)%Charset.length]);
		return sb.toString();
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			Charset=br.readLine().toCharArray();
			CharIdx=new int [128];
			for (int i=0;i<Charset.length;i++) CharIdx[Charset[i]]=i;

			String W=br.readLine();
			String S=br.readLine();

			ArrayList<Integer> ans=new ArrayList<>();
			for (int i=0;i<Charset.length;i++) {
				String ws=shift(W,i);
				if (kmp(S,ws)) ans.add(i);
			}

			StringBuilder sb=new StringBuilder();
			if (ans.size()==0) sb.append("no solution");
			else {
				sb.append(ans.size()==1 ? "unique:": "ambiguous:");
				for (int i=0;i<ans.size();i++) {
					sb.append(' ');
					sb.append(ans.get(i));
				}
			}
			System.out.println(sb);
		}
	}
}