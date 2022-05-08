import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class Main {

	private static ArrayList<String> Passwords;
	
	private static void generate(HashSet<Character> [] colChars, StringBuilder curr) {
		if (curr.length()==colChars.length) {
			Passwords.add(curr.toString());
		} else {
			for (char c: colChars[curr.length()]) {
				curr.append(c);
				generate(colChars,curr);
				curr.setLength(curr.length()-1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int K=Integer.parseInt(br.readLine())-1;
			
			char [][] set1=new char [6][];
			for (int i=0;i<set1.length;i++) set1[i]=br.readLine().toCharArray();
			char [][] set2=new char [6][];
			for (int i=0;i<set2.length;i++) set2[i]=br.readLine().toCharArray();
			
			boolean allColHasChar=true;
			HashSet<Character> [] colChars=new HashSet [set1[0].length];
			for (int col=0;col<colChars.length;col++) {
				HashSet<Character> charInColSet1=new HashSet<>();
				for (int row=0;row<set1.length;row++) charInColSet1.add(set1[row][col]);
				HashSet<Character> charInColSet2=new HashSet<>();
				for (int row=0;row<set2.length;row++) charInColSet2.add(set2[row][col]);
				
				charInColSet1.retainAll(charInColSet2);
				colChars[col]=charInColSet1;
				allColHasChar&=!colChars[col].isEmpty();
			}
			
			Passwords=new ArrayList<>();
			if (allColHasChar) {
				generate(colChars,new StringBuilder());
				Collections.sort(Passwords);
			}
			System.out.println(K<Passwords.size()?Passwords.get(K):"NO");
		}
	}

}
