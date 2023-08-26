import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Main {

	public static class Data implements Comparable<Data> {
		char c;
		int freq;
		
		public Data (char c) {
			this.c=c;
		}
		
		public void addFreq() {
			freq++;
		}
		
		public int compareTo(Data d) {
			return this.c-d.c;
		}
	}
	
	public static void main (String[]args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			String s=br.readLine();
			HashMap<Character,Data> map=new HashMap<>();
			for (char c : s.toCharArray()) {
				if (!map.containsKey(c)) map.put(c,new Data(c));
				map.get(c).addFreq();
			}
			
			ArrayList<Data> ans=new ArrayList<>();
			for (Data d : map.values()) if (BigInteger.valueOf(d.freq).isProbablePrime(12)) ans.add(d);
			Collections.sort(ans);
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(": ");
			if (ans.size()>0) for (Data d : ans) sb.append(d.c);
			else sb.append("empty");
			
			System.out.println(sb.toString());
		}
	}
}