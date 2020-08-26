import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		String [] shiftDownChars=new String[32];
		String shiftDownStr=br.readLine();
		for (int i=0;i<32;i++) shiftDownChars[i]=shiftDownStr.charAt(i)+"";
		shiftDownChars[27]="SD";
		shiftDownChars[31]="SU";

		String [] shiftUpChars=new String[32];
		String shiftUpStr=br.readLine();
		for (int i=0;i<32;i++) shiftUpChars[i]=shiftUpStr.charAt(i)+"";		
		shiftUpChars[27]="SD";
		shiftUpChars[31]="SU";
		
		String s;
		while ((s=br.readLine())!=null) {
			boolean shiftDown=true;
			StringBuilder ans=new StringBuilder();
			for (int i=0;i<s.length();i+=5) {
				int num=Integer.parseInt(s.substring(i,i+5),2);
				if (num==27) shiftDown=true;
				else if (num==31) shiftDown=false;
				else {
					if (shiftDown) ans.append(shiftDownChars[num]);
					else ans.append(shiftUpChars[num]);
				}
			}
			System.out.println(ans.toString());
		}
	}
}