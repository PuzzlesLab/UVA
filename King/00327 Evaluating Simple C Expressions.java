import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main {
	
	private static String removeBlank(String s) {
		StringBuilder sb=new StringBuilder();
		for (char c: s.toCharArray()) if (c!=' ') sb.append(c);
		return sb.toString();
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			String s2=removeBlank(s);
			char [] ch=s2.toCharArray();
			HashSet<Character> variables=new HashSet<>();
			for (char c : ch) if (c>='a' && c<='z') variables.add(c);
			int [] variableValues=new int [128];
			for (int i='a';i<='z';i++) variableValues[i]=i-'a'+1;
			int [] postDeltas=new int [128];
			
			boolean [] ignore=new boolean [ch.length];
			for (int i=0;i<ch.length-2;i++) {
				char c=ch[i];
				if (c=='+' && ch[i+1]=='+' && variables.contains(ch[i+2])) {
					ignore[i]=true;
					ignore[i+1]=true;
					variableValues[ch[i+2]]++;
					i+=3;
				} else if (c=='-' && ch[i+1]=='-' && variables.contains(ch[i+2])) {
					ignore[i]=true;
					ignore[i+1]=true;
					variableValues[ch[i+2]]--;
					i+=3;
				} else if (variables.contains(ch[i]) && ch[i+1]=='+' && ch[i+2]=='+') {
					ignore[i+1]=true;
					ignore[i+2]=true;
					postDeltas[ch[i]]++;
					i+=3;
				} else if (variables.contains(ch[i]) && ch[i+1]=='-' && ch[i+2]=='-') {
					ignore[i+1]=true;
					ignore[i+2]=true;
					postDeltas[ch[i]]--;
					i+=3;
				}
			}
			
			int sum=0;
			boolean negative=false;
			for (int i=0;i<ch.length;i++) if (!ignore[i]) {
				char c=ch[i];
				if (c=='-') negative=true;
				else if (c=='+') negative=false;
				else if (variables.contains(c)) sum+=negative ? -variableValues[c] : variableValues[c];
			}
			for (char c : ch) if (variables.contains(c)) variableValues[c]+=postDeltas[c];
			
			StringBuilder sb=new StringBuilder();
			sb.append("Expression: ");
			sb.append(s);
			sb.append("\n    value = ");
			sb.append(sum);
			sb.append('\n');
			for (char c='a';c<='z';c++) if (variables.contains(c)) {
				sb.append("    ");
				sb.append(c);
				sb.append(" = ");
				sb.append(variableValues[c]);
				sb.append('\n');
			}
			
			System.out.print(sb.toString());
		}
	}
}