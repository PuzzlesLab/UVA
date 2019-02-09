import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static StringBuilder Ans;
	public static void dfs(StringBuilder sb, char [] rule, int rulePos, String [] words) {
		if (rulePos==rule.length) {
			Ans.append(sb.toString());
			Ans.append('\n');
		} else {
			if (rule[rulePos]=='#') {
				for (String word : words) {
					StringBuilder temp=new StringBuilder(sb);
					temp.append(word);
					dfs(temp, rule, rulePos+1, words);
				}
			} else if (rule[rulePos]=='0') {
				for (int i=0;i<10;i++) {
					StringBuilder temp=new StringBuilder(sb);
					temp.append(i);
					dfs(temp, rule, rulePos+1, words);
				}
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			String [] words=new String[Integer.parseInt(s)];
			for (int i=0;i<words.length;i++) words[i]=br.readLine();
			
			char [][] rules=new char[Integer.parseInt(br.readLine())][];
			for (int i=0;i<rules.length;i++) rules[i]=br.readLine().toCharArray();
			
			Ans=new StringBuilder();
			Ans.append("--\n");
			for (char [] rule : rules) dfs(new StringBuilder(), rule, 0, words);
			
			System.out.print(Ans.toString());
		}

	}

}