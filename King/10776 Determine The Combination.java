import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<String> solution=new ArrayList<>();
	
	private static void generate(char [] ch, int startIndex, String currSol, int maxLength) {
		if (currSol.length()<maxLength) {
			for (int i=startIndex;i<ch.length;i++) if (i==startIndex || ch[i]!=ch[i-1]) {
				generate(ch,i+1,currSol+ch[i],maxLength);
			}
		} else solution.add(currSol);
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			char [] ch=st.nextToken().toCharArray();
			int N=Integer.parseInt(st.nextToken());
			Arrays.sort(ch);
			
			solution.clear();
			generate(ch,0,"",N);
			
			StringBuilder sb=new StringBuilder();
			for (String ans : solution) {
				sb.append(ans);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}
}