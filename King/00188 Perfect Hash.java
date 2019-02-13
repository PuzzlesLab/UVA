import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			ArrayList<String> words=new ArrayList<>();
			ArrayList<Integer> wordNums=new ArrayList<>();
			while (st.hasMoreTokens()) {
				String word=st.nextToken();
				words.add(word);
				
				int wordNum=0;
				for (char c : word.toCharArray()) wordNum=(wordNum<<5)+(c-'a'+1);
				wordNums.add(wordNum);
			}

			int N=words.size();
			int C=1;
			boolean unique=false;
			while (!unique) {
				unique=true;
				for (int i=0;i<N && unique;i++) {
					int mod1=(C/wordNums.get(i))%N;
					
					for (int i2=i+1;i2<N && unique;i2++) {
						int mod2=(C/wordNums.get(i2))%N;
						unique&= mod1!=mod2;
						if (!unique) C=Math.min(((C/wordNums.get(i))+1)*wordNums.get(i), ((C/wordNums.get(i2))+1)*wordNums.get(i2));
					}
				}
			}
			
			System.out.println(String.format("%s\n%d\n", s, C));
		}
	}

}