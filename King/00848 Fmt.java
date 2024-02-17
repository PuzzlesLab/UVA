import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	private static final int MAX_LENGTH=72;

	private static void trimRight(StringBuilder sb) {
		while (sb.length()>0 && sb.charAt(sb.length()-1)==' ') sb.setLength(sb.length()-1);
	}

	private static String format(String paragraph) {
		StringBuilder full=new StringBuilder();

		StringBuilder line=new StringBuilder();
		int printLen=0;
		for (int i=0;i<paragraph.length();i++) {
			char c=paragraph.charAt(i);
			if (c==' ') {
				// Attempt to extend.
				boolean flag1=false;
				boolean flag2=false;
				int count=0;
				for (int i2=i+1;i2<paragraph.length() && printLen+count<MAX_LENGTH;i2++) {
					char c2=paragraph.charAt(i2);
					if (c2!=' ' && c2!='\n') flag1=true;
					else if (flag1) {
						flag2=true;
						break;
					}
					count++;
				}
				// Extend success.
				if (flag2) {
					line.append(c);
					for (int i2=1;i2<=count;i2++) line.append(paragraph.charAt(i+i2));
					i+=count;
					printLen+=(count+1);
				} else {
					trimRight(line);
					if (line.length()>0) {
						full.append(line);
						full.append('\n');
						line.setLength(0);
						printLen=0;
					}
					// Skip empty spaces on the next line.
					while (i+1<paragraph.length() && paragraph.charAt(i+1)==' ') i++;
				}
			} else if (c=='\n') {
				trimRight(line);
				full.append(line);
				full.append(c);
				line.setLength(0);
				printLen=0;
			} else {
				line.append(c);
				printLen++;
			}
		}
		trimRight(line);
		full.append(line);

		return full.toString();
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<String> paragraphs=new ArrayList<>();
		StringBuilder content=new StringBuilder();
		while (true) {
			String s=br.readLine();
			if (s==null || s.length()==0) {
				for (int i=1;i<content.length()-1;i++) {
					char c=content.charAt(i);
					char c2=content.charAt(i+1);
					if (c=='\n' && c2!='\n' && c2!=' ') content.setCharAt(i, ' '); // Greedily eliminate line breaks.
				}
				paragraphs.add(content.toString());
				content.setLength(0);
				if (s==null) break;
			}
			content.append(s);
			content.append('\n');
		}

		for (int i=0;i<paragraphs.size();i++) System.out.print(format(paragraphs.get(i)));
	}

}
