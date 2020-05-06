import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		char [] ch=br.readLine().toCharArray();
		TreeSet<Integer> [] chIndexes=new TreeSet [128];
		for (int i=0;i<ch.length;i++) {
			if (chIndexes[ch[i]]==null) chIndexes[ch[i]]=new TreeSet<>();
			chIndexes[ch[i]].add(i);
		}
		
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			char [] toMatch=br.readLine().toCharArray();
			int ansStart=0;
			int ansEnd=0;
			
			boolean flag=true;
			int currStart=-1;
			for (int i=0;i<toMatch.length && flag;i++) {
				char c=toMatch[i];
				if (chIndexes[c]==null) {
					flag=false;
					break;
				}
				
				Integer next=chIndexes[c].ceiling(currStart);
				if (next == null) flag=false;
				else {
					if (i==0) ansStart=next;
					ansEnd=next;
					currStart=next+1;
				}
			}
			
			if (flag) System.out.printf("Matched %d %d\n",ansStart, ansEnd);
			else System.out.println("Not matched");
		}

	}

}