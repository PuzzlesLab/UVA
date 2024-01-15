import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {

	private static String hash(String s) {
		int [] map=new int [128];
		Arrays.fill(map,-1);

		StringBuilder result=new StringBuilder();
		char [] ch=s.toCharArray();
		int size=0;
		for (int i=0;i<ch.length;i++) {
			if (ch[i]==' ') result.append(' ');
			else {
				if (map[ch[i]]==-1) map[ch[i]]=size++;
				result.append(map[ch[i]]);
			}
			result.append(';');
		}
		return result.toString();
	};

	public static void main (String [] args) throws Exception {
		final String refMsg="the quick brown fox jumps over the lazy dog";
		final String matchHash=hash(refMsg);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine(); // empty.
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			ArrayList<String> inputs=new ArrayList<>();
			String s;
			while (true) {
				s=br.readLine();
				if (s==null || s.length()==0) break;
				inputs.add(s);
			}

			char [] map=null;
			for (int i=0;i<inputs.size();i++) {
				String input=inputs.get(i);
				if (input.length()!=refMsg.length()) continue;

				String currHash=hash(input);
				if (currHash.equals(matchHash)) {
					map=new char [128];
					for (int i2=0;i2<input.length();i2++) {
						map[input.charAt(i2)]=refMsg.charAt(i2);
					}
					break;
				}
			}
			
			if (testCase>0) System.out.println();
			if (map==null) System.out.println("No solution.");
			else {
				StringBuilder sb=new StringBuilder();
				for (int i=0;i<inputs.size();i++) {
					String input=inputs.get(i);
					for (int i2=0;i2<input.length();i2++) sb.append(map[input.charAt(i2)]);
					sb.append("\n");
				}
				System.out.print(sb);
			}
		}
	}

}
