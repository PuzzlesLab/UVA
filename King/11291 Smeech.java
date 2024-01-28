import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	private static ArrayList<String> tokenize(String s) {
		ArrayList<String> tokens=new ArrayList<>();
		if (s.length()==0) return tokens;
		if (s.charAt(0)=='(') s=s.substring(1,s.length()-1);
		
		StringBuilder sb=new StringBuilder();
		int level=0;
		for (int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			if (c==' ' && level==0) {
				tokens.add(sb.toString().trim());
				sb.setLength(0);
			}
			if (c=='(') level++;
			else if (c==')') level--;
			sb.append(c);
		}
		tokens.add(sb.toString().trim());
		return tokens;
	}

	private static ArrayList<String> makeList(String s) {
		ArrayList<String> l=new ArrayList<>();
		l.add(s);
		return l;
	}

	public static double compute(ArrayList<String> tokens) {
		//System.out.println(tokens);
		if (tokens.isEmpty()) return 0.0;
		if (tokens.size()==1) {
			String s=tokens.get(0);
			char c=s.charAt(0);
			return (Character.isDigit(c) || c=='-' || c=='.') ? Double.parseDouble(s) : compute(tokenize(s));
		}
		double p=compute(makeList(tokens.get(0)));
		double x=compute(makeList(tokens.get(1)));
		double y=compute(makeList(tokens.get(2)));
		double a=p*(x+y);
		double b=(1-p)*(x-y);
		return a+b;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s=br.readLine();
			if (s.equals("()")) break;

			double ans=compute(tokenize(s));
			System.out.printf("%.2f\n",ans);
		}
	}

}
