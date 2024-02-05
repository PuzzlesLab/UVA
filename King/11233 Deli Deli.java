import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		final boolean [] VOWELS=new boolean [128];
		VOWELS['a']=true;
		VOWELS['e']=true;
		VOWELS['i']=true;
		VOWELS['o']=true;
		VOWELS['u']=false;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int L=Integer.parseInt(st.nextToken());
		int N=Integer.parseInt(st.nextToken());
		
		HashMap<String,String> irregular=new HashMap<>();
		for (int l=0;l<L;l++) {
			st=new StringTokenizer(br.readLine());
			irregular.put(st.nextToken(), st.nextToken());
		}
		
		for (int n=0;n<N;n++) {
			String s=br.readLine();
			char lastC=s.charAt(s.length()-1);
			StringBuilder sb=new StringBuilder();
			if (irregular.containsKey(s)) sb.append(irregular.get(s));
			else if (s.length()>1 && !VOWELS[s.charAt(s.length()-2)] && lastC=='y') {
				for (int i=0;i<s.length()-1;i++) sb.append(s.charAt(i));
				sb.append("ies");
			} else if (lastC=='o' || lastC=='s' || lastC=='x' || s.endsWith("ch") || s.endsWith("sh")) {
				sb.append(s);
				sb.append("es");
			} else {
				sb.append(s);
				sb.append("s");
			}
			System.out.println(sb.toString());
		}
	}

}
