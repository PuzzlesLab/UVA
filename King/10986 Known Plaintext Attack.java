import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		char [] chars=new char [26];
		for (int i=0;i<26;i++) chars[i]=(char)(i+'a');
		
		int [] charIndex=new int [128];
		for (int i='a';i<='z';i++) charIndex[i]=i-'a';
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			ArrayList<String> encryptedWords=new ArrayList<>();
			while (st.hasMoreTokens()) encryptedWords.add(st.nextToken());
			String plainWord=br.readLine();
			
			TreeSet<Character> keys=new TreeSet<>();
			for (String encryptedWord : encryptedWords) if (encryptedWord.length() == plainWord.length()) {
				int [] delta=new int [plainWord.length()];
				for (int i=0;i<plainWord.length();i++) {
					delta[i]=charIndex[encryptedWord.charAt(i)]-charIndex[plainWord.charAt(i)];
					if (delta[i]<0) delta[i]+=chars.length;
				}
				boolean sameDelta=true;
				for (int i=1;i<delta.length;i++) sameDelta&=delta[i-1]==delta[i];
				if (sameDelta) keys.add(chars[delta[0]]);
			}
			
			StringBuilder sb=new StringBuilder();
			for (char c : keys) sb.append(c);
			System.out.println(sb.toString());
		}
	}
}