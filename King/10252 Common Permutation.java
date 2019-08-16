import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int [] s1Count=new int[26];
			for (char c : s.toCharArray()) s1Count[c-'a']++;
			int [] s2Count=new int[26];
			for (char c : br.readLine().toCharArray()) s2Count[c-'a']++;
			
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<26;i++) while (s1Count[i]>0 && s2Count[i]>0) {
				sb.append((char)('a'+i));
				s1Count[i]--;
				s2Count[i]--;
			}
			
			System.out.println(sb.toString());
		}
	}

}