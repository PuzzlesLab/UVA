import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Main {

	private static class Tuple implements Comparable<Tuple> {
		char ch;
		int count;
		
		public Tuple(char c) {
			this.ch=c;
			this.count=0;
		}
		
		public int compareTo(Tuple t) {
			if (this.count==t.count) return this.ch-t.ch;
			return this.count-t.count;
		}
	}

	private static ArrayList<Tuple> getSet(String s) {
		char [] text=s.toCharArray();
		Tuple [] chMap=new Tuple[128];
		ArrayList<Tuple> chSet=new ArrayList<>();
		for (int i=0;i<text.length;i++) {
			if (!Character.isAlphabetic(text[i])) continue;

			if (chMap[text[i]]==null) {
				chMap[text[i]]=new Tuple(text[i]);
				chSet.add(chMap[text[i]]);
			}
			chMap[text[i]].count++;
		}
		return chSet;
	}

	private static int [] getRank(ArrayList<Tuple> set) {
		int [] rank=new int [128];
		Arrays.fill(rank,-1);
		for (int i=0;i<set.size();i++) rank[set.get(i).ch]=i;
		return rank;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();

			ArrayList<Tuple> set1=getSet(br.readLine());
			Collections.sort(set1);

			String enc=br.readLine();
			ArrayList<Tuple> set2=getSet(enc);
			Collections.sort(set2);
			int [] set2Rank=getRank(set2);

			StringBuilder sb=new StringBuilder();
			if (testCase>0) sb.append('\n');
			for (int i=0;i<enc.length();i++) {
				char c=enc.charAt(i);
				if (!Character.isAlphabetic(c)) sb.append(c);
				else sb.append(set1.get(set2Rank[c]).ch);
			}
			System.out.println(sb.toString());
		}
	}

}
