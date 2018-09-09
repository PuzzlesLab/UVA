import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			String s;
			
			ArrayList<String> list=new ArrayList<>();
			StringBuilder sb=new StringBuilder();
			while (!(s=br.readLine().trim()).equals("END")) {
				for (char c : s.toCharArray())
					if (c>='a' && c<='z') sb.append(c);
					else if (sb.length()>0) {
						list.add(sb.toString());
						sb.setLength(0);
					}
				
				 if (sb.length()>0) {
					list.add(sb.toString());
					sb.setLength(0);
				}
			}
			
			String [] words=list.toArray(new String[list.size()]);
			HashSet<String> uniqueWords=new HashSet<>();
			uniqueWords.addAll(list);
			
			int minDiff=Integer.MAX_VALUE;
			int minS = 0, minE = 0, currentMin=0, currentMax=0;
			int uniqueWordSize=uniqueWords.size();
			HashMap<String, Integer> counter=new HashMap<>();
			for (;currentMax<words.length;currentMax++) {
				String currWord = words[currentMax];
				counter.put(currWord, counter.getOrDefault(currWord,0)+1);
				
				while (counter.size() == uniqueWordSize) {
					String minWord=words[currentMin];
					if (counter.get(minWord)==1) {
						int diff=currentMax-currentMin;
						if (diff < minDiff) {
							minDiff = diff;
							minS = currentMin;
							minE = currentMax;
						}
						counter.remove(minWord);
					} else counter.put(minWord, counter.get(minWord)-1);
					currentMin++;
				}
			}
			
			sb=new StringBuilder();
			sb.append("Document ");
			sb.append(testCase);
			sb.append(": ");
			sb.append(minS+1);
			sb.append(" ");
			sb.append(minE+1);
			
			System.out.println(sb.toString());
		}
	}

}