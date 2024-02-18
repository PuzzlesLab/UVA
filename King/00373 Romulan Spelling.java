import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static boolean isPGUK(char [] ch, int idx) {
		if (idx<0) return false;
		if (idx+3>=ch.length) return false;
		char c0=Character.toLowerCase(ch[idx]);
		char c1=Character.toLowerCase(ch[idx+1]);
		char c2=Character.toLowerCase(ch[idx+2]);
		char c3=Character.toLowerCase(ch[idx+3]);
		return c0=='p' && c1=='g' && c2=='u' && c3=='k';
	}

	private static boolean isEGP(char [] ch, int idx) {
		if (idx-1<0) return false;
		if (idx+1>=ch.length) return false;
		char c0=Character.toLowerCase(ch[idx-1]);
		char c1=Character.toLowerCase(ch[idx]);
		char c2=Character.toLowerCase(ch[idx+1]);
		
		return c0=='e' && c1=='g' && c2=='p';
	}

	private static boolean isGPUK(char [] ch, int idx) {
		if (idx+3>=ch.length) return false;
		char c0=Character.toLowerCase(ch[idx]);
		char c1=Character.toLowerCase(ch[idx+1]);
		char c2=Character.toLowerCase(ch[idx+2]);
		char c3=Character.toLowerCase(ch[idx+3]);

		return (c0=='g' && c1=='p' && c2=='u' && c3=='k');
	}

	private static void swapRight(char [] ch, int i) {
		char c2=ch[i+1];
		ch[i+1]=ch[i];
		ch[i]=c2;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;

		while ((s=br.readLine())!=null) {
			char [] ch=s.toCharArray();
			
			for (int i=0;i<ch.length;i++) {
				boolean flag=false;

				char chLow=Character.toLowerCase(ch[i]);
				if (chLow=='p') {
					if (i+1>=ch.length) continue;
					if (isPGUK(ch,i)) continue;

					int i2=i;
					while (i2>=0) {
						char c1=Character.toLowerCase(ch[i2]);
						char c2=Character.toLowerCase(ch[i2+1]);
						if (c1!='p' || c2!='g') break;
						if (i2>0 && Character.toLowerCase(ch[i2-1])=='e') break;
						swapRight(ch,i2);
						i2--;
						flag=true;
					}
				}

				chLow=Character.toLowerCase(ch[i]);
				if (chLow=='g' && (isEGP(ch,i) || isGPUK(ch,i))) {
					swapRight(ch,i);
					flag=true;
				}

				if (flag) i=-1;
			}

			System.out.println(new String(ch));
		}
	}

}
