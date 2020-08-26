import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		int [] codeMap=new int[128];
		String [] codeMapStr= {"BPFV","CSKGJQXZ","DT","L","MN","R"};
		for (int i=0;i<codeMapStr.length;i++) for (char c : codeMapStr[i].toCharArray()) codeMap[c]=i+1;
		
		boolean [] skipEncode=new boolean [128];
		String skipEncodeStr="AEIOUYWH";
		for (char c : skipEncodeStr.toCharArray()) skipEncode[c]=true;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb=new StringBuilder();
		sb.append("         NAME                     SOUNDEX CODE\n");
		while ((s=br.readLine())!=null) {
			StringBuilder code=new StringBuilder();
			char [] ch=s.toCharArray();
			code.append(ch[0]);
			for (int i=1;i<ch.length && code.length()<4;i++) if (!skipEncode[ch[i]]) {
				if (codeMap[ch[i-1]] != codeMap[ch[i]]) code.append(codeMap[ch[i]]);
			}
			while (code.length()<4) code.append('0');
			
			sb.append("         ");
			sb.append(s);
			for (int i=s.length()+9;i<34;i++) sb.append(' ');
			sb.append(code.toString());
			sb.append('\n');
		}
		sb.append("                   END OF OUTPUT");
		System.out.println(sb.toString());
	}
}