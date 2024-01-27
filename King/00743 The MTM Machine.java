import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static String find(String s) {
		if (s.charAt(0)=='2') return s.length()>1 ? s.substring(1) : null;
		if (s.charAt(0)=='3') {
			if (s.length()==1) return null;
			String y=find(s.substring(1));
			if (y==null) return null;
			StringBuilder sb=new StringBuilder();
			sb.append(y);
			sb.append('2');
			sb.append(y);
			return sb.toString();
		}
		return null;
	}

	public static void main (String [] args) throws Exception {
		final String ERROR="NOT ACCEPTABLE";
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			boolean invalid=false;
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if (c=='0' || !Character.isDigit(c)) {
					invalid=true;
					break;
				}
			}
			if (invalid || s.length()==0) {
				System.out.println(ERROR);
				continue;
			}

			String ans=find(s);
			System.out.println(ans==null?"NOT ACCEPTABLE":ans);
		}
	}

}
