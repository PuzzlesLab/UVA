import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	private static Tuple getMatch(String s1, String s2) {
		int [] firstIdx=new int [128];
		Arrays.fill(firstIdx,-1);
		for (int i=0;i<s2.length();i++) {
			char c=s2.charAt(i);
			if (firstIdx[c]==-1) firstIdx[c]=i;
		}
		for (int i=0;i<s1.length();i++) {
			char c=s1.charAt(i);
			if (firstIdx[c]!=-1) return new Tuple(firstIdx[c],i);
		}
		return null;
	}

	public static void main (String [] args) throws Exception {
		final int GAP=3;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=0;
		while (!(s=br.readLine()).equals("#")) {
			StringTokenizer st=new StringTokenizer(s);
			String [] words=new String [4];
			for (int i=0;i<words.length;i++) words[i]=st.nextToken();
			
			Tuple t1=getMatch(words[0], words[1]);
			Tuple t2=getMatch(words[2], words[3]);
			if (t1==null || t2==null) {
				if (tc++>0) System.out.println();
				System.out.println("Unable to make two crosses");
				continue;
			}
			
			int h1=Math.max(t1.x,t2.x);
			int h2=Math.max(words[1].length()-t1.x,words[3].length()-t2.x);
			
			char [][] ans=new char [h1+h2][words[0].length()+words[2].length()+GAP];
			for (int i=0;i<ans.length;i++) Arrays.fill(ans[i],' ');
			
			// Fill horizontal
			for (int y=0;y<words[0].length();y++) ans[h1][y]=words[0].charAt(y);
			for (int y=0;y<words[2].length();y++) ans[h1][y+GAP+words[0].length()]=words[2].charAt(y);

			// Fill vertical
			int d1=h1-t1.x;
			int d2=h1-t2.x;
			for (int x=0;x<words[1].length();x++) ans[x+d1][t1.y]=words[1].charAt(x);
			for (int x=0;x<words[3].length();x++) ans[x+d2][words[0].length()+GAP+t2.y]=words[3].charAt(x);

			StringBuilder sb=new StringBuilder();
			if (tc++>0) sb.append('\n');
			for (int i=0;i<ans.length;i++) {
				StringBuilder temp=new StringBuilder();
				temp.append(new String(ans[i]));
				while (temp.length()>0 && temp.charAt(temp.length()-1)==' ') temp.setLength(temp.length()-1);
				sb.append(temp);
				sb.append('\n');
			}
			System.out.print(sb);
		}
	}

}