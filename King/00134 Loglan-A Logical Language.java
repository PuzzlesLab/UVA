import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static final boolean [] IS_VOWEL=new boolean [128];
	private static final HashSet<String> A=new HashSet<String>();
	private static final HashSet<String> MOD=new HashSet<String>();
	private static final HashSet<String> BA=new HashSet<String>();
	private static final HashSet<String> DA=new HashSet<String>();
	private static final HashSet<String> LA=new HashSet<String>();

	private static boolean isNam(ArrayList<String> tokens, int min, int max) {
		if (max-min!=1) return false;
		String s=tokens.get(min);
		return !IS_VOWEL[s.charAt(s.length()-1)];
	}
	
	private static boolean isPreda(ArrayList<String> tokens, int min, int max) {
		if (max-min!=1) return false;
		String s=tokens.get(min);
		if (s.length()!=5) return false;
		return ((!IS_VOWEL[s.charAt(0)] && !IS_VOWEL[s.charAt(1)] && IS_VOWEL[s.charAt(2)] && !IS_VOWEL[s.charAt(3)]  && IS_VOWEL[s.charAt(4)]) || (!IS_VOWEL[s.charAt(0)] && IS_VOWEL[s.charAt(1)] && !IS_VOWEL[s.charAt(2)] && !IS_VOWEL[s.charAt(3)]  && IS_VOWEL[s.charAt(4)]));
	}
	
	private static boolean isA(ArrayList<String> tokens, int min, int max) {
		return max-min==1 && A.contains(tokens.get(min));
	}

	private static boolean isLa(ArrayList<String> tokens, int min, int max) {
		return max-min==1 && LA.contains(tokens.get(min));
	}

	private static boolean isMod(ArrayList<String> tokens, int min, int max) {
		return max-min==1 && MOD.contains(tokens.get(min));
	}

	private static boolean isBa(ArrayList<String> tokens, int min, int max) {
		return max-min==1 && BA.contains(tokens.get(min));
	}
	
	private static boolean isDa(ArrayList<String> tokens, int min, int max) {
		return max-min==1 && DA.contains(tokens.get(min));
	}

	private static boolean isPredname(ArrayList<String> tokens, int min, int max) {
		return (max-min>1 && isLa(tokens,min,min+1) && isPredstring(tokens,min+1,max)) || isNam(tokens,min,max);
	}

	private static boolean isPredstring(ArrayList<String> tokens, int min, int max) {
		if (min>=max) return false;
		return isPreda(tokens,min,max) || (isPredstring(tokens,min,max-1) && isPreda(tokens,max-1,max));
	}

	private static boolean isVerbperd(ArrayList<String> tokens, int min, int max) {
		if (min>=max) return false;
		return isMod(tokens,min,min+1) && isPredstring(tokens,min+1,max);
	}

	private static boolean isStatement(ArrayList<String> tokens, int min, int max) {
		if (min>=max) return false;
		for (int i=min+1;i<max;i++) for (int i2=i+1;i2<max;i2++) {
			boolean f1=isPredname(tokens,min,i);
			boolean f2=isVerbperd(tokens,i,i2);
			boolean f3=isPredname(tokens,i2,max);
			if (f1&&f2&&f3) return true;
		}
		for (int i=min+1;i<max;i++)  {
			boolean f1=isPredname(tokens,min,i);
			boolean f2=isVerbperd(tokens,i,max);
			if (f1&&f2) return true;
		}
		return false;
	}
	
	private static boolean isPreds(ArrayList<String> tokens, int min, int max) {
		if (min>=max) return false;
		if (isPredstring(tokens,min,max)) return true;
		for (int i=min+1;i<max;i++) for (int i2=i+1;i2<max;i2++) {
			boolean f1=isPreds(tokens,min,i);
			boolean f2=isA(tokens,i,i2);
			boolean f3=isPredstring(tokens,i2,max);
			if (f1&&f2&&f3) return true;
		}
		return false;
	}

	private static boolean isPredclaim(ArrayList<String> tokens, int min, int max) {
		if (min>=max) return false;
		for (int i=min+1;i<max;i++) for (int i2=i+1;i2<max;i2++) {
			boolean f1=isPredname(tokens,min,i);
			boolean f2=isBa(tokens,i,i2);
			boolean f3=isPreds(tokens,i2,max);
			if (f1&&f2&&f3) return true;
		}
		return isDa(tokens,min,min+1) && isPreds(tokens,min+1,max);
	}

	private static boolean isSentence(ArrayList<String> tokens, int min, int max) {
		if (min>=max) return false;
		return isStatement(tokens,min,max) || isPredclaim(tokens,min,max);
	}

	public static void main (String [] args) throws Exception {
		final String GOOD="Good";
		final String BAD="Bad!";
		IS_VOWEL['a']=true;
		IS_VOWEL['e']=true;
		IS_VOWEL['i']=true;
		IS_VOWEL['o']=true;
		IS_VOWEL['u']=true;
		A.add("a");
		A.add("e");
		A.add("i");
		A.add("o");
		A.add("u");
		MOD.add("ga");
		MOD.add("ge");
		MOD.add("gi");
		MOD.add("go");
		MOD.add("gu");
		BA.add("ba");
		BA.add("be");
		BA.add("bi");
		BA.add("bo");
		BA.add("bu");
		DA.add("da");
		DA.add("de");
		DA.add("di");
		DA.add("do");
		DA.add("du");
		LA.add("la");
		LA.add("le");
		LA.add("li");
		LA.add("lo");
		LA.add("lu");

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean cont=true;
		while (true) {
			StringBuilder sb=new StringBuilder();
			boolean foundEnd=false;
			while (!foundEnd) {
				String s=br.readLine();
				if (s.equals("#")) {
					cont=false;
					break;
				}
				sb.append(s);
				sb.append(' ');
				for (int i=0;i<s.length();i++) {
					if (s.charAt(i)=='.') {
						foundEnd=true;
						break;
					}
				}
			}
			if (!cont) break;

			StringTokenizer st=new StringTokenizer(sb.toString());
			ArrayList<String> tokens=new ArrayList<>();
			while (st.hasMoreTokens()) tokens.add(st.nextToken());
			if (tokens.size()>0) {
				String last=tokens.remove(tokens.size()-1); // Remove period from last token
				last=last.substring(0,last.length()-1);
				tokens.add(last);
			}
			boolean flag=isSentence(tokens,0,tokens.size());
			System.out.println(flag?GOOD:BAD);
		}
	}

}
