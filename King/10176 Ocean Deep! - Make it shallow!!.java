import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int MOD=131071;
	
	private static void process(String s) {
		char [] ch=s.toCharArray();
		long curr=0;
		for (int i=0;i<ch.length;i++) curr=((curr<<1)|(ch[i]-'0'))%MOD;
		System.out.println(curr==0 ? "YES" : "NO");
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb=new StringBuilder();
		while ((s=br.readLine())!=null) {
			char [] ch=s.toCharArray();
			for (int i=0;i<ch.length;i++) {
				if (ch[i]=='#') {
					process(sb.toString());
					sb=new StringBuilder();
				} else sb.append(ch[i]);
			}
		}
	}

}
