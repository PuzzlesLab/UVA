import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			boolean ok=s.length()==0;
			if (!ok) {
				char [] ch=s.toCharArray();
				int needOperand=1;
				int end=0;
				for (int i=0;i<ch.length;i++) {
					char c=ch[i];
					if (c>='p' && c<='z') needOperand--;
					else if (c=='N') continue;
					else if (c=='C' || c=='D' || c=='E' || c=='I') needOperand++;
					else {
						needOperand=1;
						break;
					}
					
					if (needOperand==0) {
						end=i;
						break;
					}
				}
				ok=needOperand==0 && end==ch.length-1;
			}
			System.out.println(ok ? "YES" : "NO");
		}
	}
}