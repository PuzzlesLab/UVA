import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		int [] charToCode=new int[128];
		for (int i='a';i<='z';i++) charToCode[i]=i-'a'+1;
		charToCode['_']=0;
		charToCode['.']=27;
		
		char [] codeToChar=new char[28];
		codeToChar[0]='_';
		for (int i='a';i<='z';i++) codeToChar[i-'a'+1]=(char)i;
		codeToChar[27]='.';
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int K=Integer.parseInt(st.nextToken());
			String ciphertext=st.nextToken();
			int [] ciphercode=new int [ciphertext.length()];
			for (int i=0;i<ciphertext.length();i++) ciphercode[i]=charToCode[ciphertext.charAt(i)];
			int [] plaincode=new int [ciphercode.length];
			for (int i=0;i<ciphertext.length();i++) plaincode[(K*i)%ciphertext.length()]=(ciphercode[i]+i)%28;
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<ciphertext.length();i++) sb.append(codeToChar[plaincode[i]]);
			System.out.println(sb.toString());
		}
	}
}