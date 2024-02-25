import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			String s=br.readLine();
			String s2=br.readLine();
			
			int t1s=s.indexOf('<');
			int t1e=s.indexOf('>',t1s);
			int t2s=s.indexOf('<',t1e);
			int t2e=s.indexOf('>',t2s);

			String p1=s.substring(t1s+1,t1e);
			String p2=s.substring(t1e+1,t2s);
			String p3=s.substring(t2s+1,t2e);
			String p4=s.substring(t2e+1,s.length());

			StringBuilder sm=new StringBuilder();
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if (c!='<' && c!='>') sm.append(c);
			}
			sm.append('\n');
			for (int i=0;i<s2.length();i++) {
				char c=s2.charAt(i);
				if (i+2<s2.length() && c=='.' && s2.charAt(i+1)=='.' && s2.charAt(i+2)=='.' ) {
					sm.append(p3);
					sm.append(p2);
					sm.append(p1);
					sm.append(p4);
					i+=2;
				} else sm.append(c);
			}
			
			System.out.println(sm);
		}
	}
}