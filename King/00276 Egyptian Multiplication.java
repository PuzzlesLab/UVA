import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final char [] CharSet={'|','n','9','8','r'};
	private static final long [] CharValue={1,10,100,1000,10000,100000};
	private static long [] CharValueMap;
	private static final int MOD=100000;
	
	private static long toNum(char [] ch) {
		long num=0;
		for (int i=0;i<ch.length;i++) num+=CharValueMap[ch[i]];
		return num;
	}
	
	private static String toChar(long num) {
		if (num==0) return "";

		StringBuilder sb=new StringBuilder();
		for (int i=0;i<CharSet.length;i++) {
			boolean first=true;
			while (num%CharValue[i+1]!=0) {
				if (first && sb.length()>0) sb.append(' ');
				first=false;
				sb.append(CharSet[i]);
				num-=CharValue[i];
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		CharValueMap=new long [128];
		for (int i=0;i<CharSet.length;i++) CharValueMap[CharSet[i]]=CharValue[i];

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			if (s.trim().isEmpty()) break;

			char [] ch1=s.toCharArray();
			char [] ch2=br.readLine().toCharArray();
			
			long n1=toNum(ch1);
			long n2=toNum(ch2);
			
			StringBuilder sb=new StringBuilder();
			long left=1;
			long right=n1;
			long ans=0;
			while (left<=n2) {
				StringBuilder line=new StringBuilder();
				line.append(toChar(left));
				if ((left&n2)!=0) {
					line.append(" *");
					ans=(ans+right)%MOD;
				}
				while (line.length()<34) line.append(' ');
				line.append(toChar(right));
				line.append('\n');

				sb.append(line);
				
				right<<=1;
				left<<=1;
			}
			sb.append("The solution is:");
			if (ans>0) {
				sb.append(' ');
				sb.append(toChar(ans));
			}
			System.out.println(sb.toString());
		}
	}

}
