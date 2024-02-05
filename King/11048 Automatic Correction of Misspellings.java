import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main {

	private static String [] Words;
	private static HashSet<String> WordsSet=new HashSet<>();

	private static String find(String target) {
		if (WordsSet.contains(target)) return target;

		int tLen=target.length();
		for (int i=0;i<Words.length;i++) {
			String check=Words[i];
			int cLen=check.length();
			if (tLen<cLen-1 || tLen>cLen+1) continue;

			if (tLen==cLen) {
				if (target.equals(check)) return check;

				// 1 adjacent letter swap
				int match=0;
				for (int i2=0;i2<tLen;i2++) if (target.charAt(i2)==check.charAt(i2)) match++;

				boolean swapOK=false;
				for (int i2=0;i2<tLen-1;i2++) {
					if (target.charAt(i2)==check.charAt(i2)) continue;
					if (swapOK) {
						swapOK=false;
						break;
					}
					if (target.charAt(i2)==check.charAt(i2+1) && target.charAt(i2+1)==check.charAt(i2)) {;
						swapOK=true;
						i2++;
					}
				}
				if (match==tLen-2 && swapOK) return check;

				// One letter is wrong
				int mismatch=0;
				for (int i2=0;i2<tLen;i2++) {
					if (target.charAt(i2)==check.charAt(i2)) continue;
					mismatch++;
					if (mismatch>1) break;
				}
				if (mismatch==1) return check;
			}

			// letter written as leter
			if (tLen==cLen-1) {
				int cIdx=0;
				int match=0;
				for (int tIdx=0;tIdx<tLen && cIdx<cLen;tIdx++) {
					//System.out.println(target.substring(tIdx)+" vs "+check.substring(cIdx));
					if (target.charAt(tIdx)!=check.charAt(cIdx)) {
						cIdx++;
						tIdx--;
					} else {
						cIdx++;
						match++;
					}
				}
				if (match==tLen) return check;
			}
			
			// letter written as lettter
			if (tLen==cLen+1) {
				int tIdx=0;
				int match=0;
				for (int cIdx=0;tIdx<tLen && cIdx<cLen;cIdx++) {
					//System.out.println(target.substring(tIdx)+" vs "+check.substring(cIdx));
					if (target.charAt(tIdx)!=check.charAt(cIdx)) {
						tIdx++;
						cIdx--;
					} else {
						tIdx++;
						match++;
					}
				}
				if (match==cLen) return check;
			}
		}

		return null;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		Words=new String [N];

		for (int n=0;n<N;n++) {
			Words[n]=br.readLine();
			WordsSet.add(Words[n]);
		}

		int Q=Integer.parseInt(br.readLine());
		for (int q=0;q<Q;q++) {
			String s=br.readLine();
			StringBuilder sb=new StringBuilder();
			sb.append(s);
			String match=find(s);
			if (match!=null) {
				if (match.equals(s)) sb.append(" is correct");
				else {
					sb.append(" is a misspelling of ");
					sb.append(match);
				}
			} else {
				sb.append(" is unknown");
			}
			System.out.println(sb.toString());
		}
	}

}
