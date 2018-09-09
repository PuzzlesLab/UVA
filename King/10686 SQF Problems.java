import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int C=Integer.parseInt(br.readLine());
			
			HashMap<String, HashSet<String>> catsKw=new HashMap<>();
			HashMap<String, Integer> kwCount=new HashMap<>();
			ArrayList<String> cats=new ArrayList<>();
			for (int c=0;c<C;c++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				String name=st.nextToken();
				cats.add(name);
				
				int kw=Integer.parseInt(st.nextToken());
				kwCount.put(name, Integer.parseInt(st.nextToken()));
				
				catsKw.put(name, new HashSet<>());
				for (int i=0;i<kw;i++) catsKw.get(name).add(br.readLine().trim());
			}

			HashSet<String> pdWords=new HashSet<>();
			StringBuilder sb=new StringBuilder();
			String s;
			while ((s=br.readLine())!=null) {
				if (s.trim().length()==0) break;
				for (char c : s.toCharArray()) {
					if ((c>='A' && c<='Z') || (c>='a' && c<='z')) sb.append(c);
					else if (sb.length()>0) {
						pdWords.add(sb.toString());
						sb.setLength(0);
					}
				}
				if (sb.length() > 0) {
					pdWords.add(sb.toString());
					sb.setLength(0);
				}
			}
			
			sb=new StringBuilder();
			for (String cat : cats) {
				int count=0;
				int targ=kwCount.get(cat);
				for (String kw : catsKw.get(cat)) if (pdWords.contains(kw)) {
					count++;
					if (count>=targ) break;
				}
				
				if (count>=targ) {
					sb.append(cat);
					sb.append(',');
				}
			}
			
			if (sb.length()>0) sb.setLength(sb.length()-1);
			else sb.append("SQF Problem.");
			
			System.out.println(sb.toString());
		}
	}

}