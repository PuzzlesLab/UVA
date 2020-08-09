import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {
	
	public static int wordLength(String s) {
		int count=0;
		for (char c : s.toCharArray()) if (c>='a' && c<='z') count++;
		return count;
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=0;
		while ((s=br.readLine())!=null) {
			StringBuilder sb=new StringBuilder();
			while (true) {
				s=s.trim();
				if (s.equals("#")) break;
				if (s.length()>0) {
					char lastC=s.charAt(s.length()-1);
					sb.append(s);
					if (lastC!='\'' && lastC!='-') sb.append(' ');
				}
				s=br.readLine();
				if (s==null) break;
			}
			
			s=sb.toString().replace('!', ' ').replace('.', ' ').replace('?', ' ').replace(',', ' ').toLowerCase();
			StringTokenizer st=new StringTokenizer(s);
			TreeMap<Integer, Integer> map=new TreeMap<>();
			while (st.hasMoreTokens()) {
				String ws=st.nextToken();
				int len=wordLength(ws);
				map.put(len,map.getOrDefault(len,0)+1);
			}
			
			sb=new StringBuilder();
			for (int len : map.keySet()) {
				sb.append(len);
				sb.append(' ');
				sb.append(map.get(len));
				sb.append('\n');
			}
			 //Question statement is wrong! Should be orint a blank line between consecutive, not after every output! 
			if (testCase++>0) System.out.println();
			System.out.print(sb.toString());
			
		}
	}

}