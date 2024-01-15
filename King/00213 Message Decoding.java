import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {

	private static String parse(String text, HashMap<String,Character> map) {
		int idx=0;

		StringBuilder result=new StringBuilder();
		while (idx<text.length()) {
			StringBuilder sb=new StringBuilder();
			sb.append(text.charAt(idx++));
			sb.append(text.charAt(idx++));
			sb.append(text.charAt(idx++));
			int len=Integer.parseInt(sb.toString(),2);
			if (len==0) break;
			
			sb=new StringBuilder();
			for (int i=0;i<len;i++) sb.append("1");
			String endText=sb.toString();
			
			sb=new StringBuilder();
			while (true) {
				for (int i=0;i<len;i++) sb.append(text.charAt(idx++));
				if (sb.toString().equals(endText)) break;

				result.append(map.get(sb.toString()));
				sb=new StringBuilder();
			}
		}
		return result.toString();
	}

	private static boolean allOnes(int i) {
		if (i==0) return false;

		while (i>0) {
			if ((i&1)==0) return false;
			i>>=1;
		}
		return true;
	}

	private static boolean isStart(String s) {
		for (int i=0;i<s.length();i++) if (s.charAt(i)!='0' && s.charAt(i)!='1') return true;
		return false;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean cont=true;
		while (cont) {
			StringBuilder sb=null;
			HashMap<String,Character> map=null;
			while (true) {
				s=br.readLine();
				if (s==null) {
					cont=false;
					break;
				}
				if (isStart(s)) {
					if (map!=null) System.out.println(parse(sb.toString(), map));
					sb=new StringBuilder();
					map=new HashMap<>();

					char [] chSet=s.toCharArray();
					int idx=2;
					for (int i=0;i<chSet.length;i++) {
						map.put(Integer.toBinaryString(idx).substring(1),chSet[i]);
						idx++;
						if (allOnes(idx)) idx++;
					}
				} else sb.append(s);
			}
			if (sb!=null) System.out.println(parse(sb.toString(), map));
		}
	}

}
