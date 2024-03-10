import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("#")) {
			int [] count=new int[128];
			for (int i=0;i<s.length();i++) count[s.charAt(i)]++;
			
			ArrayList<Character> odd=new ArrayList<>();
			for (int i='a';i<='z';i++) if (count[i]>0) {
				if ((count[i]&1)==1) odd.add((char)i);
			}
			Collections.sort(odd);
			if (!odd.isEmpty()) odd.remove(odd.size()-1); // Remove the largest char to get lowest lexi solution.

			StringBuilder sb=new StringBuilder();
			for (int i=0;i<odd.size();i++) sb.append(odd.get(i));
			System.out.println(sb);
		}
	}
}