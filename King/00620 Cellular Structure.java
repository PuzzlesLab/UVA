import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static boolean validate(String s, int min, int max) {
		int len=max-min;

		if (len==1) return s.charAt(min)=='A';
		if ((len&1)==0) return false;

		char c0=s.charAt(min);
		char cN=s.charAt(max-1);
		if (c0=='B' && cN=='A') return validate(s,min+1,max-1);
		else if (cN=='B' && s.charAt(max-2)=='A') return validate(s,min,max-2);
		return false;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			String s=br.readLine();
			int len=s.length();
			
			if (len==1) {
				System.out.println(s.charAt(0)=='A'?"SIMPLE":"MUTANT");
				continue;
			} else if (len==0) {
				System.out.println("MUTANT");
				continue;
			}

			char c0=s.charAt(0);
			char cN=s.charAt(len-1);
			if (c0=='B' && cN=='A') {
				System.out.println(validate(s,1,len-1)?"MUTAGENIC":"MUTANT");
			} else if (cN=='B' && s.charAt(len-2)=='A') {
				System.out.println(validate(s,0,len-2)?"FULLY-GROWN":"MUTANT");
			} else {
				System.out.println("MUTANT");
			}
		}
	}

}
