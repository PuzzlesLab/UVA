import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

class Main {

	private static final String AND="AND";
	private static final String THEN="THEN";
	private static final HashSet<String> WHEN=new HashSet<>();
	
	private static ArrayList<String> tokenize(String s) {
		ArrayList<String> tokens=new ArrayList<>();
		StringBuilder sb=new StringBuilder();
		boolean dQuote=false;
		
		for (int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			if (c=='"') {
				dQuote=!dQuote;
				if (sb.length()>0 && sb.charAt(sb.length()-1)==' ') sb.setLength(sb.length()-1);
				sb.append(c);
			} else if (c==' ' && dQuote || c!=' ') {
				if (c==' ' && sb.length()>0) {
					char lastC=sb.charAt(sb.length()-1);
					if (lastC==' ' || lastC=='"') continue;
				}
				sb.append(c);
			} else if (c==' ' && sb.length()>0) {
				tokens.add(sb.toString());
				sb.setLength(0);
			}
		}
		if (sb.length()>0) tokens.add(sb.toString());

		return tokens;
	}

	private static boolean isInstruction(ArrayList<String> tokens) {
		boolean flag = isNavigational(tokens,0,tokens.size()) || isTimeKeeping(tokens,0,tokens.size());
		for (int i=0;i<tokens.size() && !flag;i++) {
			flag|=isNavigational(tokens,0,i) && tokens.get(i).equals(AND) && isTimeKeeping(tokens,i+1,tokens.size());
		}
		return flag;
	}

	private static boolean isNavigational(ArrayList<String> tokens, int min, int max) {
		if (isDirectional(tokens,min,max)) return true;
		boolean flag=false;
		for (int i=min+1;i<max && !flag;i++) {
			flag|=isNavigational(tokens,min,i)
					&& tokens.get(i).equals(AND)
					&& tokens.get(i+1).equals(THEN)
					&& isDirectional(tokens,i+2,max);
		}
		return flag;
	}
	
	private static boolean isDirectional(ArrayList<String> tokens, int min, int max) {
		boolean flag=false;
		for (int i=min+1;i<max && !flag;i++) flag|=isHow(tokens,min,i) && isDirection(tokens,i,max);
		if (flag) return flag;
		
		for (int i=min+1;i<max && !flag;i++) {
			flag|=isHow(tokens,min,i) && isDirection(tokens,i,i+1) && isWhere(tokens,i+1,max);
		}
		return flag;
	}
	
	private static boolean isHow(ArrayList<String> tokens,int min, int max) {
		return isGo(tokens,min,max) || (isGo(tokens,min,min+1) && isWhen(tokens,min+1,max)) || isKeep(tokens,min,max);
	}
	
	private static boolean isDirection(ArrayList<String> tokens, int min, int max) {
		if (max-min!=1) return false;
		return tokens.get(min).equals("RIGHT") || tokens.get(min).equals("LEFT");
	}
	
	private static boolean isGo(ArrayList<String> tokens, int min, int max) {
		if (max-min!=1) return false;
		return tokens.get(min).equals("GO");
	}
	
	private static boolean isWhen(ArrayList<String> tokens, int min, int max) {
		if (max-min!=1) return false;
		return WHEN.contains(tokens.get(min));
	}
	
	private static boolean isKeep(ArrayList<String> tokens, int min, int max) {
		if (max-min!=1) return false;
		return tokens.get(min).equals("KEEP");
	}
	
	private static boolean isWhere(ArrayList<String> tokens, int min, int max) {
		if (max-min!=2) return false;
		return tokens.get(min).equals("AT") && isSign(tokens,min+1,max);
	}
	
	private static boolean isSign(ArrayList<String> tokens, int min, int max) {
		if (max-min!=1) return false;
		String sign=tokens.get(min);
		if (sign.length()<=2) return false;
		if (sign.charAt(0)!='"') return false;
		if (sign.charAt(sign.length()-1)!='"') return false;

		ArrayList<String> subTokens=tokenize(sign.substring(1,sign.length()-1));
		return isSignWords(subTokens,0,subTokens.size());
	}

	private static boolean isSignWords(ArrayList<String> tokens, int min, int max) {
		if (max-min==1) return isSWord(tokens,min,max);
		if (max>min) return isSignWords(tokens,min,max-1) && isSWord(tokens,max-1,max);
		return false;
	}

	private static boolean isSWord(ArrayList<String> tokens, int min, int max) {
		if (max-min!=1) return false;
		String w=tokens.get(min);
		for (int i=0;i<w.length();i++) {
			char c=w.charAt(i);
			if (c>='A' && c<='Z') continue;
			if (c=='.') continue;
			return false;
		}
		return true;
	}

	private static boolean isTimeKeeping(ArrayList<String> tokens, int min, int max) {
		return isRecord(tokens,min,max) || isChange(tokens,min,max);
	}

	private static boolean isRecord(ArrayList<String> tokens, int min, int max) {
		if (max-min!=2) return false;
		return tokens.get(min).equals("RECORD") && tokens.get(max-1).equals("TIME");
	}
	
	private static boolean isChange(ArrayList<String> tokens, int min, int max) {
		if (max-min<4) return false;
		for (int i=min+1;i<max;i++) {
			if (!isCas(tokens,min,i)) continue;
			if (!tokens.get(i).equals("TO")) continue;
			if (!isNNN(tokens,i+1,i+2)) continue;
			if (max-(i+2)!=1) continue;
			if (!tokens.get(max-1).equals("KMH")) continue;
			return true;
		}
		return false;
	}

	private static boolean isCas(ArrayList<String> tokens, int min, int max) {
		if (max-min==1) return tokens.get(min).equals("CAS");
		if (max-min==3) return tokens.get(min).equals("CHANGE") && tokens.get(min+1).equals("AVERAGE") && tokens.get(min+2).equals("SPEED");
		return false;
	}

	private static boolean isNNN(ArrayList<String> tokens, int min, int max) {
		if (max-min!=1) return false;
		String w=tokens.get(min);
		for (int i=0;i<w.length();i++) if (!Character.isDigit(w.charAt(i))) return false;
		return true;
	}

	public static void main (String [] args) throws Exception {
		WHEN.add("FIRST");
		WHEN.add("SECOND");
		WHEN.add("THIRD");

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("#")) {
			ArrayList<String> tokens=tokenize(s);
			//System.out.println(tokens);
			boolean flag=isInstruction(tokens);

			StringBuilder sb=new StringBuilder();
			sb.append(String.format("%3d. ", tc++));
			if (flag) {
				for (int i=0;i<tokens.size();i++) {
					sb.append(tokens.get(i));
					sb.append(' ');
				}
				if (sb.length()>0) sb.setLength(sb.length()-1);
			} else sb.append("Trap!");
			System.out.println(sb);
		}
	}

}
