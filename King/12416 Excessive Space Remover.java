import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int maxLen=0;
			int currLen=0;
			for (int i=0;i<s.length();i++) {
				if (s.charAt(i)==' ') {
					currLen++;
				} else {
					maxLen=Math.max(maxLen,currLen);
					currLen=0;
				}
			}
			maxLen=Math.max(maxLen,currLen);
			
			System.out.println((int)Math.ceil(Math.log10(maxLen)/Math.log10(2)));
		}
	}

}
