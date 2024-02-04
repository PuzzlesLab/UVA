import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final boolean [] VOWELS=new boolean [128];

	private static int countSyllable(String s) {
		int count=0;
		int curr=0;
		for (int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			if (VOWELS[c]) curr++;
			else if (curr>0) {
				count++;
				curr=0;
			}
		}
		if (curr>0) count++;
		return count;
	}

	public static void main (String [] args) throws Exception {
		VOWELS['a']=true;
		VOWELS['e']=true;
		VOWELS['i']=true;
		VOWELS['o']=true;
		VOWELS['u']=true;
		VOWELS['y']=true;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("e/o/i")) {
			StringTokenizer st=new StringTokenizer(s,"/");
			int v1=countSyllable(st.nextToken());
			if (v1!=5) {
				System.out.println(1);
				continue;
			}
			int v2=countSyllable(st.nextToken());
			if (v2!=7) {
				System.out.println(2);
				continue;
			}
			int v3=countSyllable(st.nextToken());
			if (v3!=5) {
				System.out.println(3);
				continue;
			}

			System.out.println("Y");
		}
	}

}
