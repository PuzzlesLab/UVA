import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final char [] CharSet= {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

	private static void addHex(StringBuilder sb, int n) {
		sb.append(CharSet[n>>4]);
		sb.append(CharSet[n&15]);
	}

	public static void main(String[] args) throws Exception {
		final int MOD=34943;
		StringBuilder ans=new StringBuilder();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("#")) {
			char [] ch=s.toCharArray();

			long curr=0;
			for (int i=0;i<ch.length;i++) curr=((curr<<8)+ch[i])%MOD;
			curr=(curr<<8)%MOD;
			curr=(curr<<8)%MOD;
			int crc=(int)((MOD-curr)%MOD);
			addHex(ans,crc>>8);
			ans.append(' ');
			addHex(ans,crc&255);
			ans.append('\n');
			// IO intensive question. Print on every line will have TLE.
		}
		System.out.print(ans);
	}

}
