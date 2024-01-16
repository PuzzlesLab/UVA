import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static char [] ChSet;
	private static int [] ChIndex;

	public static void main (String [] args) throws Exception {
		ChSet=new char [27];
		ChSet[0]=' ';
		for (int i='A';i<='Z';i++) ChSet[i-'A'+1]=(char)i;

		ChIndex=new int [128];
		for (int i=0;i<ChSet.length;i++) ChIndex[ChSet[i]]=i;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		HashSet<String> words=new HashSet<>();
		while (!(s=br.readLine()).equals("#")) words.add(s);

		char [] encrypted=br.readLine().toCharArray();
		String ans="";
		int ansMatch=-1;
		for (int k=0;k<ChSet.length;k++) {
			StringTokenizer st=new StringTokenizer(new String(encrypted));
			int match=0;
			while (st.hasMoreTokens()) {
				if (words.contains(st.nextToken())) match++;
			}
			if (match>ansMatch) {
				ans=new String(encrypted);
				ansMatch=match;
			}
			
			for (int i=0;i<encrypted.length;i++) encrypted[i]=ChSet[(ChIndex[encrypted[i]]+1)%ChSet.length];
		}
		
		StringTokenizer st=new StringTokenizer(ans);
		int lineLen=0;
		StringBuilder sb=new StringBuilder();
		while (st.hasMoreTokens()) {
			s=st.nextToken();
			if (lineLen+s.length()<=60) {
				sb.append(s);
				sb.append(" ");
				lineLen+=(s.length()+1);
			} else {
				if (sb.charAt(sb.length()-1)==' ') {
					sb.setLength(sb.length()-1);
				}
				sb.append('\n'); // Start a new line!.
				lineLen=s.length();
				sb.append(s);
				if (lineLen<=60) {
					sb.append(" ");
					lineLen++;
				}
			}
		}
		System.out.println(sb.toString().trim());
	}

}
