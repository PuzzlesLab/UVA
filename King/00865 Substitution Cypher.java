import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine(); //empty
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			char [] convertMap=new char[128];
			for (int i=0;i<convertMap.length;i++) convertMap[i]=(char)i;
			
			ArrayList<String> inputLines=new ArrayList<>();
			String s;
			while (true) {
				s=br.readLine();
				if (s==null || s.length()==0) break;
				inputLines.add(s);
			}
			
			char [] source=inputLines.get(0).toCharArray();
			char [] dest=inputLines.get(1).toCharArray();
			for (int i=0;i<source.length;i++) convertMap[source[i]]=dest[i];
			
			StringBuilder sb=new StringBuilder();
			sb.append(inputLines.get(1));
			sb.append('\n');
			sb.append(inputLines.get(0));
			sb.append('\n');
			for (int i=2;i<inputLines.size();i++) {
				String line=inputLines.get(i);
				for (char c : line.toCharArray()) sb.append(convertMap[c]);
				sb.append('\n');
			}
			
			if (testCase>0) System.out.println();
			System.out.print(sb.toString());
		}
	}
}