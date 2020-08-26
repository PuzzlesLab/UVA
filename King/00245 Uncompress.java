import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		LinkedList<String> word=new LinkedList<>();
		while (!(s=br.readLine()).equals("0")) {
			StringBuilder toPrint=new StringBuilder();
			
			StringBuilder lastWord=new StringBuilder();
			int index=0;
			s=s+" ";
			for (char c : s.toCharArray()) {
				if (Character.isAlphabetic(c)) lastWord.append(c);
				else if (Character.isDigit(c)) index=index*10+(c-'0');
				else {
					if (lastWord.length()>0) {
						String str=lastWord.toString();
						toPrint.append(str);
						word.addFirst(str);
						lastWord.setLength(0);
					}
					if (index>0) {
						String str=word.remove(index-1);
						word.addFirst(str);
						toPrint.append(str);
						index=0;
					}
					toPrint.append(c);
				}
			}
			toPrint.setLength(toPrint.length()-1);
			System.out.println(toPrint.toString());
		}
	}
}