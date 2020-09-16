import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static boolean isValidDouble(StringBuilder sb) {
		boolean hasDot=false;
		for (int i=0;i<sb.length();i++) {
			char c=sb.charAt(i);
			if (c=='.') {
				if (hasDot) return false;
				else hasDot=true;
			} else if (c<'0' || c>'9') return false;
		}
		return true;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		StringBuilder inputSB=new StringBuilder();
		String s;
		while ((s=br.readLine())!=null) {
		//while (!(s=br.readLine()).equals("ZZZ")) {
			inputSB.append(s);
			inputSB.append('\n');
		}
		char [] ch=inputSB.toString().toCharArray();
		
		StringBuilder output=new StringBuilder();
		boolean processing=true;
		boolean openSlash=false;
		for (int idx=0;idx<ch.length;idx++) {
			char c=ch[idx];
			boolean append=!processing;
			
			if (processing) {
				if (c=='\\') {
					if (openSlash) append=true;
					openSlash=!openSlash;
				} else if (openSlash) {
					openSlash=!openSlash;
					if (c=='s') {
						int tempIdx=idx;
						StringBuilder num=new StringBuilder();
						while (tempIdx+1<ch.length) {
							tempIdx++;
							num.append(ch[tempIdx]);
							if (!isValidDouble(num)) break;
						}
						idx=tempIdx-1;
					} else if (c=='*') processing=!processing;
					else append=c!='b' && c!='i';
				} else append=true;
			} else {
				if (c=='\\' && idx+1<ch.length && ch[idx+1]=='*') {
					idx++;
					append=false;
					processing=!processing;
				}
			}
			if (append) output.append(c);
		}
		System.out.print(output.toString());
	}
}