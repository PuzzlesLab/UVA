import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main {

	private static class Tuple implements Comparable<Tuple> {
		char ch;
		int count;
		char reverse;
		
		public Tuple(char c) {
			this.ch=c;
			this.count=0;
		}
		
		public int compareTo(Tuple t) {
			if (this.count!=t.count) return this.count-t.count;
			return t.ch-this.ch;
		}
	}

	private static ArrayList<Tuple> getSet(String s) {
		Tuple [] data=new Tuple[128];
		for (int i='a';i<='z';i++) data[i]=new Tuple((char)i);

		for (int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			if (!Character.isAlphabetic(c)) continue;
			c=Character.toLowerCase(c);
			data[c].count++;
		}
		ArrayList<Tuple> list=new ArrayList<>();
		for (int i=0;i<data.length;i++) if (data[i]!=null) list.add(data[i]);
		Collections.sort(list,Collections.reverseOrder());
		return list;
	}

	private static String readUntilEmptyLine(BufferedReader br) throws Exception {
		StringBuilder sb=new StringBuilder();
		String s;
		while ((s=br.readLine()).length()>0) {
			sb.append(s);
			sb.append('\n');
		}
		return sb.toString();
	}

	private static String readUntilEOF(BufferedReader br) throws Exception {
		StringBuilder sb=new StringBuilder();
		String s;
		while ((s=br.readLine())!=null) {
			sb.append(s);
			sb.append('\n');
		}
		return sb.toString();
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s=readUntilEmptyLine(br);
		String s2=readUntilEOF(br);

		ArrayList<Tuple> known=getSet(s);
		ArrayList<Tuple> encoded=getSet(s2);

		for (int i=0;i<known.size();i++) encoded.get(i).reverse=known.get(i).ch;

		Tuple [] map=new Tuple[128];
		for (int i=0;i<encoded.size();i++) map[encoded.get(i).ch]=encoded.get(i);

		StringBuilder sb=new StringBuilder();
		for (int i=0;i<s2.length();i++) {
			char c=s2.charAt(i);
			if (!Character.isAlphabetic(c)) sb.append(c);
			else if (Character.isUpperCase(c)) sb.append(Character.toUpperCase(map[Character.toLowerCase(c)].reverse));
			else sb.append(map[c].reverse);
		}
		System.out.print(sb.toString());
	}

}
