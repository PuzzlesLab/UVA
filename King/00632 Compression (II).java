import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			int N=Integer.parseInt(br.readLine());
			LinkedList<Character> ch=new LinkedList<>();
			while (ch.size()<N) for (char c : br.readLine().toCharArray()) ch.add(c);

			ArrayList<String> swaps=new ArrayList<>();
			for (int i=0;i<N;i++) {
				StringBuilder sb=new StringBuilder();
				for (char c : ch) sb.append(c);
				swaps.add(sb.toString());
				ch.addLast(ch.removeFirst());
			}
			String s1=swaps.get(1);
			Collections.sort(swaps);
			
			StringBuilder ans=new StringBuilder();
			for (int i=0;i<N;i++) ans.append(swaps.get(i).charAt(swaps.get(i).length()-1));
			String ansStr=ans.toString();
			
			if (testCase>0) System.out.println();
			System.out.println(swaps.indexOf(s1));
			for (int i=0;i<ansStr.length();i+=50) System.out.println(ans.substring(i,Math.min(i+50,ansStr.length())));
		}
	}
}