import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine().trim());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			
			int N=Integer.parseInt(br.readLine().trim());
			HashMap<String, String> candidateParty=new HashMap<>();
			for (int n=0;n<N;n++) candidateParty.put(br.readLine().trim(), br.readLine().trim());
			
			int M=Integer.parseInt(br.readLine().trim());
			HashMap<String, Integer> candidateCount=new HashMap<>();
			for (int m=0;m<M;m++) {
				String name=br.readLine().trim();
				if (candidateParty.containsKey(name)) candidateCount.put(name, candidateCount.getOrDefault(name, 0)+1);
			}
			
			int maxCount=0;
			for (int count : candidateCount.values()) maxCount=Math.max(count ,maxCount);
			
			String ans=null;
			int maxCountCount=0;
			for (String name : candidateCount.keySet()) {
				int count=candidateCount.get(name);
				if (count==maxCount) {
					maxCountCount++;
					ans=candidateParty.get(name);
				}
			}
			
			if (testCase>0) System.out.println();
			if (maxCountCount==1) System.out.println(ans);
			else System.out.println("tie");
		}
	}

}