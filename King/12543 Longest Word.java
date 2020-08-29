import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		boolean [] valid=new boolean [256];
		valid['-']=true;
		for (int i='A';i<='Z';i++) valid[i]=true;
		for (int i='a';i<='z';i++) valid[i]=true;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean end=false;
		String maxWordLenWord="";
		while (!end) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			while (st.hasMoreTokens() && !end) {
				String word=st.nextToken();
				StringBuilder wordValid=new StringBuilder();
				for (char c : word.toCharArray()) if (valid[c]) wordValid.append(c);
				if (wordValid.length()==5 && word.equals("E-N-D")) end=true;
				else if (wordValid.length()>maxWordLenWord.length()) maxWordLenWord=wordValid.toString();
			}
		}
		System.out.println(maxWordLenWord.toLowerCase());
	}
}