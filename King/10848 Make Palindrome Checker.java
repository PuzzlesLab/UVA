import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	private static boolean check1(String s, int lim) {
		if (s.length()>lim) return false;
		boolean flag=true;
		for (int i=0;i<s.length() && flag;i++) {
			char c=s.charAt(i);
			flag&=(c>='a' && c<='z');
		}
		return flag;
	}

	private static boolean check2(String s) {
		for (int i=0;i<(s.length()>>1);i++) if (s.charAt(i)!=s.charAt(s.length()-1-i)) return false;
		return true;
	}

	private static boolean check3(String s1, String s2) {
		boolean [] exists=new boolean [128];
		for (int i=0;i<s1.length();i++) exists[s1.charAt(i)]=true;
		for (int i=0;i<s2.length();i++) exists[s2.charAt(i)]=false;
		for (int i='a';i<='z';i++) if (exists[i]) return false;
		return true;
	}

	private static boolean check4(String s1, String s2) {
		int [] count=new int [128];
		for (int i=0;i<s1.length();i++) count[s1.charAt(i)]++;
		for (int i=0;i<s2.length();i++) count[s2.charAt(i)]--;
		for (int i='a';i<='z';i++) if (count[i]>0) return false;
		return true;
	}

	private static boolean check5(String s1, String s2) {
		int s1I=0;
		for (int i=0;i<s2.length() && s1I<s1.length();i++) if (s2.charAt(i)==s1.charAt(s1I)) s1I++;
		return s1I==s1.length();
	}

	private static ArrayList<String> tokenize(String s) {
		ArrayList<String> list=new ArrayList<>();
		String [] tokens={"","",""};

		int curr=0;
		for (int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			if (c==' ') curr++;
			else {
				if (curr==0) tokens[curr]+=c;
				else if (curr==1) tokens[curr]+=c;
				else if (curr==2) tokens[curr]+=c;
			}
		}
		
		if (curr==2) {
			list.add(tokens[0]);
			list.add(tokens[1]);
			list.add(tokens[2]);
		}
		return list;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			ArrayList<String> tokens=tokenize(s);
			String p1="";
			String p2="";
			int n=0;

			try {
				if (tokens.size()!=3) throw new Exception();
				p1=tokens.get(0);
				n=Integer.parseInt(tokens.get(1));
				p2=tokens.get(2);
			} catch (Exception exp) {
				System.out.println("FFFFFFF The solution is not accepted");
				continue;
			}

			boolean [] flags=new boolean [7];
			flags[0]=check1(p1,1000) && check1(p2,2000) && n>=0 && n<=1000;
			flags[1]=flags[0] && check2(p2);
			flags[2]=flags[0] && check3(p1,p2);
			flags[3]=flags[0] && check4(p1,p2);
			flags[4]=flags[0] && check5(p1,p2);
			flags[5]=flags[0] && p1.length()+n==p2.length();
			flags[6]=flags[0] && n<p1.length();

			boolean accept=true;
			for (int i=0;i<flags.length;i++) accept&=flags[i];
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<flags.length;i++) sb.append(flags[i]?'T':'F');
			sb.append(" The solution is");
			if (!accept) sb.append(" not");
			sb.append(" accepted");
			System.out.println(sb);
		}
	}
}