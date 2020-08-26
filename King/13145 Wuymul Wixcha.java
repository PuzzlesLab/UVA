import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			StringBuilder sb=new StringBuilder();
			for (char c : br.readLine().toCharArray()) {
				if (c>='a' && c<='z') sb.append((char)(Math.floorMod((c-'a'+N),26)+'a'));
				else if (c>='A' && c<='Z') sb.append((char)(Math.floorMod((c-'A'+N),26)+'A'));
				else sb.append(c);
			}
			System.out.println(sb.toString());
		}
	}
}