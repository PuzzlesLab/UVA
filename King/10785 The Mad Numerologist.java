import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

class Main {

	public static void main (String [] args) throws Exception {
		int [] values=new int [128];
		for (int i='A';i<='Z';i++) values[i]=((i-'A')%9)+1;
		
		boolean [] vowel=new boolean [128];
		vowel['A']=true; vowel['E']=true; vowel['I']=true; vowel['O']=true; vowel['U']=true;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			
			LinkedList<Character> vowels=new LinkedList<>();
			LinkedList<Character> consonants=new LinkedList<>();
			int [] chCount=new int [128];
			for (int n=0;n<N;n++) {
				if (n%2==0) { //vowel
					char lowestValueChar='$';
					for (int i='A';i<='Z';i++) if (vowel[i] && chCount[i]<21 && (lowestValueChar=='$' || values[i]<values[lowestValueChar])) lowestValueChar=(char)i;
					vowels.add(lowestValueChar);
					chCount[lowestValueChar]++;
				} else { //consonant
					char lowestValueChar='$';
					for (int i='A';i<='Z';i++) if (!vowel[i] && chCount[i]<5 && (lowestValueChar=='$' || values[i]<values[lowestValueChar])) lowestValueChar=(char)i;
					consonants.add(lowestValueChar);
					chCount[lowestValueChar]++;
				}
			}
			Collections.sort(vowels);
			Collections.sort(consonants);
			
			StringBuilder ans=new StringBuilder();
			for (int n=0;n<N;n++) {
				if (n%2==0) ans.append(vowels.removeFirst());
				else ans.append(consonants.removeFirst());
			}
			
			System.out.printf("Case %d: %s\n", testCase, ans);
		}
	}

}