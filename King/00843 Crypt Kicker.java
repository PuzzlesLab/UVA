import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class Word {
		String s;
		int len;
		
		public Word(String s) {
			this.s=s;
			this.len=this.s.length();
		}
		
		public boolean isEquivalent(Word w) {
			if (this.len!=w.len) return false;

			char [] temp=new char [26];
			for (int i=0;i<this.len;i++) {
				char c1=this.s.charAt(i);
				char c2=w.s.charAt(i);
				if (temp[c1-'a']==0) temp[c1-'a']=c2;
				else if (temp[c1-'a']!=c2) return false;
			}
			return true;
		}
	}

	private static HashMap<Integer, ArrayList<Word>> wLenMap;
	private static char [] Sol;

	private static void search(ArrayList<Word> tokens, char [] cMap, char [] cMapR, int idx) {
		if (Sol!=null) return;
		if (idx==tokens.size()) {
			Sol=new char [cMap.length];
			for (int i=0;i<cMap.length;i++) Sol[i]=cMap[i];
			return;
		}

		Word token=tokens.get(idx);
		if (!wLenMap.containsKey(token.len)) return;
		ArrayList<Word> candidateWords=wLenMap.get(token.len);
		for (int i=0;i<candidateWords.size();i++) {
			Word replacement=candidateWords.get(i);
			// Doesn't match frequency table.
			if (!token.isEquivalent(replacement)) continue;

			// Check if align with existing replaced chars.
			boolean flag=true;
			for (int i2=0;i2<token.len && flag;i2++) {
				char c1=token.s.charAt(i2);
				char c2=replacement.s.charAt(i2);
				if (cMap[c1]==0) {
					if (cMapR[c2]==0) continue;
					else {
						flag=false;
						break;
					}
				}
				flag &= cMap[c1]==c2 && cMapR[c2]==c1;
			}
			if (!flag) continue;

			// Find and replace unchanged chars
			ArrayList<Integer> cr=new ArrayList<>();
			for (int i2=0;i2<token.len;i2++) {
				char c1=token.s.charAt(i2);
				if (cMap[c1]!=0) continue;
				char c2=replacement.s.charAt(i2);
				cMap[c1]=c2;
				cMapR[c2]=c1;
				cr.add((int)c1);
			}
			search(tokens,cMap,cMapR,idx+1);
			// Restore last changed chars
			for (int i2=0;i2<cr.size();i2++) {
				cMapR[cMap[cr.get(i2)]]=0;
				cMap[cr.get(i2)]=0;
			}
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		Word [] words=new Word [N];
		wLenMap=new HashMap<>();
		for (int n=0;n<N;n++) {
			words[n]=new Word(br.readLine().trim());
			if (!wLenMap.containsKey(words[n].len)) wLenMap.put(words[n].len, new ArrayList<>());
			wLenMap.get(words[n].len).add(words[n]);
		}

		String s;
		while ((s=br.readLine())!=null) {
			s=s.trim();
			StringTokenizer st=new StringTokenizer(s);
			ArrayList<Word> tokens=new ArrayList<>();
			while (st.hasMoreTokens()) tokens.add(new Word(st.nextToken()));

			Sol=null;
			search(tokens,new char [128],new char [128],0);
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if (c>='a' && c<='z') sb.append(Sol!=null ? Sol[c] : '*');
				else sb.append(c);
			}
			System.out.println(sb);
		}
	}

}
