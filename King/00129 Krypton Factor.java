import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int CurrIdx=0;
	private static int MaxIdx=0;
	private static String Ans=null;
	
	private static boolean isEasy(StringBuilder sb) {
		for (int startIdx=sb.length()%2==0 ? 0: 1;startIdx<sb.length()-1;startIdx+=2) {
			int length=(sb.length()-startIdx)/2;
			int startIdx2=startIdx+length;
			boolean match=true;
			for (int i=0;i<length && match;i++) match &= sb.charAt(startIdx+i) == sb.charAt(startIdx2+i);
			if (match) return true;
		}
		return false;
	}

	private static void find(char [] possibles, StringBuilder curr) {
		if (CurrIdx>MaxIdx) return;

		if (curr.length()==0) {
			for (int i=0;i<possibles.length;i++) {
				curr.append(possibles[i]);
				find(possibles,curr);
				curr.setLength(curr.length()-1);
			}
		} else if (!isEasy(curr)) {
			CurrIdx++;
			if (CurrIdx==MaxIdx) {
				Ans=curr.toString();
				return;
			}
			for (int i=0;i<possibles.length;i++) {
				curr.append(possibles[i]);
				find(possibles,curr);
				curr.setLength(curr.length()-1);
			}
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int L=Integer.parseInt(st.nextToken());
			
			char [] possibles=new char [L];
			for (int i=0;i<possibles.length;i++) possibles[i]=(char)('A'+i);
			
			CurrIdx=0;
			MaxIdx=N;
			Ans=null;
			find(possibles, new StringBuilder());
			
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<Ans.length();i++) {
				if (i>0 && i%4==0) sb.append(i%64==0 ? '\n' : ' ');
				sb.append(Ans.charAt(i));
			}
			sb.append('\n');
			sb.append(Ans.length());
			System.out.println(sb.toString());
		}
	}

}
